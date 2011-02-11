package org.bukkit.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Stores all PluginDescriptions in a graph linked by dependencies.
 *
 * PluginDescriptions are stored in a directed graph, where edges are plugin
 * dependencies. The graph can be walked when enabling and disabling plugins
 * to ensure related plugins are correctly enabled or disabled as well.
 *
 * The graph contains nodes for all plugins available, and all enabled
 * plugins. It's possible for the graph to contain two nodes that both
 * describe the same plugin in practice, because a different version is
 * currently enabled than what's been indexed.
 */
public final class PluginDependencyGraph {
    /**
     * State values used for various purposes.
     */
    private enum State {
        /**
         * The node has not been encountered yet.
         *
         * Used while walking the graph.
         */
        NEW,
        /**
         * We've looped over the node already, looping over it a second time
         * would indicate a circular dependency.
         *
         * Used while walking the graph.
         */
        ITERATED,
        /**
         * We've seen this node, and it's been visited already. We can safely
         * ignore it. An example of this would be a diamond-like structure.
         *
         * Used while walking the graph.
         */
        VISITED,
        /**
         * The node no longer exists in the graph.
         *
         * Used from {@link PluginDependencyGraph#clear()}.
         */
        DESTROYED
    };

    /**
     * A node in the dependency graph.
     */
    private final class Node {
        public final PluginDescription description;
        public Plugin plugin = null;

        public final HashSet<Node> dependencies;
        public final HashSet<Node> dependents;
        public final HashSet<String> unresolved;

        public State state = State.NEW;

        public Node(PluginDescription description) {
            this.description = description;

            dependencies = new HashSet<Node>();
            dependents = new HashSet<Node>();
            unresolved = new HashSet<String>();

            ArrayList<String> namedDeps = description.getDependencies();
            if (namedDeps != null) {
                unresolved.addAll(namedDeps);
            }
        }

        /**
         * Fix-up the graph edges to account for this new node.
         *
         * This is called shortly after construction.
         */
        public void resolveDependencies() {
            // Walk our named dependencies and resolve them to nodes where we can.
            Iterator<String> i = unresolved.iterator();
            while (i.hasNext()) {
                String name = i.next();
                Node dependency = nodesByName.get(name);
                if (dependency != null) {
                    dependencies.add(dependency);
                    dependency.dependents.add(this);
                    i.remove();
                }
            }

            // Register ourselves as dependent on the remaining named dependencies.
            registerUnresolved();

            // Update other nodes depending on this node, which is now available.
            HashSet<Node> others = unresolvedNames.remove(description.getName());
            if (others != null) {
                for (Node node : others) {
                    node.unresolved.remove(description.getName());
                    node.dependencies.add(this);
                    dependents.add(node);
                }
            }
        }

        /**
         * Fix-up the graph edges for nodes that no longer exist.
         *
         * This is called during {@link PluginDependencyGraph#clear()}.
         */
        public void clearLostDependencies() {
            Iterator<Node> i = dependencies.iterator();
            while (i.hasNext()) {
                Node node = i.next();
                if (node.state == State.DESTROYED) {
                    unresolved.add(node.description.getName());
                    i.remove();
                }
            }

            i = dependents.iterator();
            while (i.hasNext()) {
                Node node = i.next();
                if (node.state == State.DESTROYED) {
                    unresolved.add(node.description.getName());
                    i.remove();
                }
            }

            registerUnresolved();
        }

        /**
         * Register unresolved named dependencies with the index.
         *
         * Helper used from {@link #resolveDependencies()} and
         * {@link #clearLostDependencies()}.
         */
        private void registerUnresolved() {
            for (String name : unresolved) {
                HashSet<Node> set = unresolvedNames.get(name);
                if (set == null) {
                    set = new HashSet<Node>();
                    unresolvedNames.put(name, set);
                }
                set.add(this);
            }
        }
    }

    /**
     * An interface used to walk the graph.
     */
    public interface Visitor {
        /**
         * Called for each node visited.
         *
         * The returned Plugin will be stored with to node, to indicate that
         * the node is use. Therefore, when enabling a plugin, a valid Plugin
         * instance should be returned, but when disabling a plugin, null
         * should always be returned.
         *
         * @param description The PluginDescription object for the visited node.
         * @return The current Plugin instance.
         * @throws Exception Any exception caught will be wrapped in a GraphIterationAborted
         */
        public Plugin visit(PluginDescription description) throws Exception;
    }

    /**
     * An index of nodes by plugin name.
     *
     * This index reflects the filesystem. When a conflict occurs, the last
     * insert prevails. This is because the last insert is assumed to be the
     * most up-to-date with the filesystem.
     */
    private final HashMap<String, Node> nodesByName = new HashMap<String, Node>();

    /**
     * An index of nodes by PluginDescription instance.
     *
     * This doubles as the complete list of nodes, because the description
     * object is unique. A name may conflict while an update is occurring,
     * and a name may not even exist for a plugin that is enabled, but no
     * longer on the filesystem.
     */
    private final HashMap<PluginDescription, Node> nodesByDescription = new HashMap<PluginDescription, Node>();

    /**
     * Named dependencies that are still to be resolved.
     *
     * Each name maps to a set of nodes that needs to know when the node
     * for the named dependency is created, so it may link to it.
     */
    private final HashMap<String, HashSet<Node>> unresolvedNames = new HashMap<String, HashSet<Node>>();

    /**
     * Walk dependencies of the given plugin in the order to enable.
     *
     * @param description The description of the plugin to enable.
     * @param visitor A Visitor implementation containing the callback method.
     * @throws GraphIterationAborted An exception was encountered while walking the graph
     * @return The return value of the call to {@link Visitor#visit(PluginDescription)} for the input description
     */
    public Plugin walkDependencies(PluginDescription description, Visitor visitor) throws GraphIterationAborted {
        Node node = nodesByDescription.get(description);
        if (node == null) {
            throw new IllegalArgumentException("Description does not belong to this graph");
        }
        clearVisitorState();
        return walk(node, visitor, true);
    }

    /**
     * Walk dependencies of the given plugin in the order to disable.
     *
     * @param description The description of the plugin to disable.
     * @param visitor A Visitor implementation containing the callback method.
     * @throws GraphIterationAborted An exception was encountered while walking the graph
     * @return The return value of the call to {@link Visitor#visit(PluginDescription)} for the input description
     */
    public void walkDependents(PluginDescription description, Visitor visitor) throws GraphIterationAborted {
        Node node = nodesByDescription.get(description);
        if (node == null) {
            throw new IllegalArgumentException("Description does not belong to this graph");
        }
        clearVisitorState();
        walk(node, visitor, false);
    }

    /**
     * Retrieve a plugin description by name.
     *
     * @param name The plugin's name.
     * @return The associated PluginDescription, or null.
     */
    public PluginDescription get(String name) {
        return nodesByName.get(name).description;
    }

    /**
     * Get a list of all known plugins
     *
     * @return A collection of PluginDescriptions.
     */
    public Collection<PluginDescription> values() {
        Collection<Node> nodes = nodesByName.values();
        ArrayList<PluginDescription> result = new ArrayList<PluginDescription>(nodes.size());
        for (Node node : nodes) {
            result.add(node.description);
        }
        return result;
    }

    /**
     * Inserts a new plugin in the graph.
     *
     * @param description The plugin's description.
     */
    public void insert(PluginDescription description) {
        Node node = new Node(description);
        nodesByName.put(description.getName(), node);
        nodesByDescription.put(description, node);
        node.resolveDependencies();
    }

    /**
     * Clears all but loaded plugin nodes from the graph.
     */
    public void clear() {
        // First, clean up the canonical list of nodes.
        Iterator<Node> i = nodesByDescription.values().iterator();
        while (i.hasNext()) {
            Node node = i.next();
            if (node.plugin == null) {
                node.state = State.DESTROYED;
                i.remove();
            }
        }

        // Ditch the name-based index, because we really don't want plugins
        // that have been deleted but are still enabled to be referenced
        // by their names. (disablePlugin doesn't need it.)
        nodesByName.clear();

        // Now, clean up destroyed nodes from the index of unresolved names.
        Iterator<HashSet<Node>> j = unresolvedNames.values().iterator();
        while (j.hasNext()) {
            HashSet<Node> set = j.next();
            i = set.iterator();
            while (i.hasNext()) {
                Node node = i.next();
                if (node.state == State.DESTROYED) {
                    i.remove();
                }
            }
            if (set.isEmpty()) {
                j.remove();
            }
        }

        // For the remaining nodes, sever links to nodes that no longer exist.
        for (Node node : nodesByDescription.values()) {
            node.clearLostDependencies();
        }
    }

    /**
     * Helper to clear all visitor state on nodes.
     */
    private void clearVisitorState() {
        for (Node node : nodesByName.values()) {
            node.state = State.NEW;
        }
    }

    /**
     * Workhorse that walks the graph.
     *
     * @param node The node we're at now.
     * @param visitor The visitor callback interface.
     * @param down The direction we're traveling in.
     * @throws GraphIterationAborted An exception was encountered while walking the graph
     * @return The return value of the call to {@link Visitor#visit(PluginDescription)} for the input node
     */
    // FIXME: Implement non-recursive algorithm.
    private Plugin walk(Node node, Visitor visitor, boolean down) throws GraphIterationAborted {
        if (node.state == State.VISITED) {
            // The return value in this situation is never used, so this is safe.
            return null;
        }
        else if (node.state == State.ITERATED) {
            // FIXME: Be more descriptive here.
            throw new CircularDependencyException();
        }
        node.state = State.ITERATED;

        HashSet<Node> toWalk = down ? node.dependencies : node.dependents;
        if (down && !node.unresolved.isEmpty()) {
            String oneOfTheDeps = node.unresolved.iterator().next(); 
            throw new MissingDependencyException(node.description.getName(), oneOfTheDeps);
        }
        for (Node dependency : toWalk) {
            walk(dependency, visitor, down);
        }

        node.state = State.VISITED;
        try {
            return node.plugin = visitor.visit(node.description);
        }
        catch (Exception ex) {
            throw new GraphIterationAborted(ex);
        }
    }
}
