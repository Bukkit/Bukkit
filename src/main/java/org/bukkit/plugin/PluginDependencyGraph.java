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
 */
// FIXME: Better key for the index, probably name /and version/.
// Right now, this doesn't account for reindexing at runtime at all.
public final class PluginDependencyGraph {
    /**
     * State values used while walking the graph.
     */
    private enum VisitorState {
        /**
         * The node has not been encountered yet.
         */
        NEW,
        /**
         * We've looped over the node already, looping over it a second time
         * would indicate a circular dependency.
         */
        ITERATED,
        /**
         * We've seen this node, and it's been visited already. We can safely
         * ignore it. An example of this would be a diamond-like structure. 
         */
        VISITED
    };

    /**
     * A node in the dependency graph.
     */
    private final class Node {
        private final PluginDescription description;

        private final HashSet<Node> dependencies;
        private final HashSet<Node> dependents;
        private final HashSet<String> unresolved;

        private VisitorState state = VisitorState.NEW;

        private Node(PluginDescription description) {
            this.description = description;

            dependencies = new HashSet<Node>();
            dependents = new HashSet<Node>();
            unresolved = new HashSet<String>();

            nodesByName.put(description.name, this);
            nodesByDescription.put(description, this);

            if (description.dependencies != null) {
                unresolved.addAll(description.dependencies);
            }
            resolveDependencies();
        }

        /**
         * Fix-up the graph edges to account for this new node.
         */
        private void resolveDependencies() {
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
            for (String name : unresolved) {
                HashSet<Node> set = unresolvedNames.get(name);
                if (set == null) {
                    set = new HashSet<Node>();
                    unresolvedNames.put(name, set);
                }
                set.add(this);
            }

            // Update other nodes depending on this node, which is now available.
            HashSet<Node> others = unresolvedNames.remove(description.name);
            if (others != null) {
                for (Node node : others) {
                    node.unresolved.remove(description.name);
                    node.dependencies.add(this);
                    dependents.add(node);
                }
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
         * @param description The PluginDescription object for the visited node.
         * @return An object passed back to the user.
         * @throws Throwable Any throwables caught are wrapped in a GraphIterationAborted
         */
        public Object visit(PluginDescription description) throws Throwable;
    }

    private final HashMap<String, Node> nodesByName = new HashMap<String, Node>();
    private final HashMap<PluginDescription, Node> nodesByDescription = new HashMap<PluginDescription, Node>();
    private final HashMap<String, HashSet<Node>> unresolvedNames = new HashMap<String, HashSet<Node>>();

    /**
     * Walk dependencies of the given plugin in the order to enable.
     *
     * @param description The description of the plugin to enable.
     * @param visitor A Visitor implementation containing the callback method.
     * @throws GraphIterationAborted An exception was encountered while walking the graph
     * @return The return value of the call to {@link Visitor#visit(PluginDescription)} for the input description
     */
    public Object walkDependencies(PluginDescription description, Visitor visitor) throws GraphIterationAborted {
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
    public Object walkDependents(PluginDescription description, Visitor visitor) throws GraphIterationAborted {
        Node node = nodesByDescription.get(description);
        if (node == null) {
            throw new IllegalArgumentException("Description does not belong to this graph");
        }
        clearVisitorState();
        return walk(node, visitor, false);
    }

    /**
     * Empties the graph.
     */
    public void clear() {
        nodesByName.clear();
        nodesByDescription.clear();
        unresolvedNames.clear();
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
     * Checks whether a plugin is known.
     *
     * @param description The plugin's description.
     * @return Whether the plugin is known or not.
     */
    public boolean contains(PluginDescription description) {
        return nodesByDescription.containsKey(description);
    }

    /**
     * Inserts a new plugin in the graph.
     *
     * @param description The plugin's description.
     */
    public void insert(PluginDescription description) {
        new Node(description);
    }

    /**
     * Helper to clear all visitor state on nodes.
     */
    private void clearVisitorState() {
        for (Node node : nodesByName.values()) {
            node.state = VisitorState.NEW;
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
    private Object walk(Node node, Visitor visitor, boolean down) throws GraphIterationAborted {
        if (node.state == VisitorState.VISITED) {
            // The return value in this situation is never used, so this is safe.
            return null;
        }
        else if (node.state == VisitorState.ITERATED) {
            // FIXME: Be more descriptive here.
            throw new CircularDependencyException();
        }
        node.state = VisitorState.ITERATED;

        HashSet<Node> toWalk = down ? node.dependencies : node.dependents;
        if (down && !node.unresolved.isEmpty()) {
            String oneOfTheDeps = node.unresolved.iterator().next(); 
            throw new MissingDependencyException(node.description.name, oneOfTheDeps);
        }
        for (Node dependency : toWalk) {
            walk(dependency, visitor, down);
        }

        node.state = VisitorState.VISITED;
        try {
            return visitor.visit(node.description);
        }
        catch (Throwable ex) {
            throw new GraphIterationAborted(ex);
        }
    }
}
