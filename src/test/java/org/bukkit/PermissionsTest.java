package org.bukkit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.TestPlugin;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class PermissionsTest {

    // Run only once, as the TestServer persists between tests
    @BeforeClass
    public static void registerPermissions() {
        PluginManager pluginManager;
        pluginManager = TestServer.getInstance().getPluginManager();
        pluginManager.addPermission(new Permission("perm1", PermissionDefault.FALSE));
        pluginManager.addPermission(new Permission("perm2", PermissionDefault.FALSE));
        pluginManager.addPermission(new Permission("perm3", PermissionDefault.TRUE));  // Note true not false

        Permission c1 = new Permission("child1", PermissionDefault.FALSE);
        pluginManager.addPermission(c1);
        Permission c2 = new Permission("child2", PermissionDefault.FALSE);
        pluginManager.addPermission(c2);
        Permission p1 = new Permission("parent1", PermissionDefault.FALSE);
        pluginManager.addPermission(p1);
        c1.addParent(p1, true);
        c2.addParent("parent1", false);  // Note false not true
    }

    public Permissible getPermissible() {
        return new PermissibleBase(null);
    }

    @Test
    public void testPermissionDefaults() {
        Permissible p = getPermissible();

        // Check default values
        assertFalse(p.hasPermission("perm1"));
        assertFalse(p.hasPermission("perm2"));
        assertTrue(p.hasPermission("perm3"));

        assertFalse(p.hasPermission("parent1"));
        assertFalse(p.hasPermission("child1"));
        assertFalse(p.hasPermission("child2"));

        // Check not listed as set
        assertFalse(p.isPermissionSet("perm1"));
        assertFalse(p.isPermissionSet("perm2"));
        // TODO: Permissions defaulted to true erroneously always report as set
        //assertFalse(p.isPermissionSet("perm3"));
        assertFalse(p.isPermissionSet("parent1"));
        assertFalse(p.isPermissionSet("child1"));
        assertFalse(p.isPermissionSet("child2"));

    }

    @Test
    public void testIndividualAddRemove() {
        Permissible p = getPermissible();
        Plugin plugin = new TestPlugin("permissionsTest");
        PermissionAttachment pa = p.addAttachment(plugin);

        // Add
        pa.setPermission("perm1", true);
        pa.setPermission("perm2", false);
        pa.setPermission("perm3", true);

        assertTrue(p.hasPermission("perm1"));
        assertFalse(p.hasPermission("perm2"));
        assertTrue(p.hasPermission("perm3"));
        assertTrue(p.isPermissionSet("perm1"));
        assertTrue(p.isPermissionSet("perm2"));
        assertTrue(p.isPermissionSet("perm3"));

        // Remove
        pa.unsetPermission("perm1");
        pa.unsetPermission("perm2");
        pa.unsetPermission("perm3");

        assertFalse(p.hasPermission("perm1"));
        assertFalse(p.hasPermission("perm2"));
        assertTrue(p.hasPermission("perm3"));
        assertFalse(p.isPermissionSet("perm1"));
        assertFalse(p.isPermissionSet("perm2"));
        // TODO: Permissions defaulted to true erroneously always report as set
        //assertFalse(p.isPermissionSet("perm3"));

    }

    @Test
    public void testChildPermissions() {

        /* The order-specific interaction between child2 and parent1 in these 
         tests is not specified in the documentation, but changing this may 
         impact existing plugins */

        Permissible p = getPermissible();
        Plugin plugin = new TestPlugin("permissionsTest");
        PermissionAttachment pa = p.addAttachment(plugin);

        // Set child2 to true.  Setting the parent subsequently will override this
        pa.setPermission("child2", true);
        // Set parent
        pa.setPermission("parent1", true);
        assertTrue(p.hasPermission("parent1"));
        assertTrue(p.hasPermission("child1"));
        assertFalse(p.hasPermission("child2"));  // child2 is defined false
        assertTrue(p.isPermissionSet("parent1"));
        assertTrue(p.isPermissionSet("child1"));
        assertTrue(p.isPermissionSet("child2"));

        // Try to set child2 to true
        // As it was already set before the parent, this should have no effect
        pa.setPermission("child2", true);
        assertFalse(p.hasPermission("child2"));

        // Unset it, then set to true
        pa.unsetPermission("child2");
        pa.setPermission("child2", true);
        assertTrue(p.hasPermission("child2"));

        // Set child then unset parent
        pa.setPermission("child1", true);
        pa.unsetPermission("parent1");
        pa.unsetPermission("child2");

        assertTrue(p.isPermissionSet("child1"));
        assertTrue(p.hasPermission("child1"));

        assertFalse(p.hasPermission("parent1"));
        assertFalse(p.hasPermission("child2"));
        assertFalse(p.isPermissionSet("parent1"));
        assertFalse(p.isPermissionSet("child2"));

    }

    @Test
    public void testBulkAdd() {

        Permissible p = getPermissible();
        Plugin plugin = new TestPlugin("permissionsTest");
        PermissionAttachment pa = p.addAttachment(plugin);

        Map<String, Boolean> m1 = new HashMap<String, Boolean>();
        m1.put("perm1", Boolean.TRUE);
        m1.put("parent1", Boolean.TRUE);
        pa.setPermissions(m1);

        assertTrue(p.hasPermission("perm1"));
        assertTrue(p.hasPermission("parent1"));
        assertTrue(p.hasPermission("child1"));
        assertFalse(p.hasPermission("child2"));  // child2 is defined false
        assertTrue(p.isPermissionSet("perm1"));
        assertTrue(p.isPermissionSet("parent1"));
        assertTrue(p.isPermissionSet("child1"));
        assertTrue(p.isPermissionSet("child2"));

        // Test adding additional
        m1.clear();
        m1.put("perm2", Boolean.TRUE);
        m1.put("perm1", Boolean.FALSE);
        pa.setPermissions(m1);

        assertFalse(p.hasPermission("perm1"));
        assertTrue(p.hasPermission("perm2"));
        assertTrue(p.hasPermission("parent1"));  // Check this is still set
        assertTrue(p.isPermissionSet("perm1"));
        assertTrue(p.isPermissionSet("perm2"));
        assertTrue(p.isPermissionSet("parent1"));

    }

    @Test
    public void testBulkSet() {
        Permissible p = getPermissible();
        Plugin plugin = new TestPlugin("permissionsTest");
        PermissionAttachment pa = p.addAttachment(plugin);

        Map<String, Boolean> m = new HashMap<String, Boolean>();
        m.put("perm1", Boolean.TRUE);
        m.put("parent1", Boolean.TRUE);
        pa.resetPermissions(m);

        assertTrue(p.hasPermission("perm1"));
        assertTrue(p.hasPermission("parent1"));
        assertTrue(p.hasPermission("child1"));
        assertFalse(p.hasPermission("child2"));  // child2 is defined false
        assertTrue(p.isPermissionSet("perm1"));
        assertTrue(p.isPermissionSet("parent1"));
        assertTrue(p.isPermissionSet("child1"));
        assertTrue(p.isPermissionSet("child2"));

        // Test resetting to different values
        m.clear();
        m.put("perm2", Boolean.TRUE);
        m.put("perm1", Boolean.FALSE);
        pa.resetPermissions(m);

        assertFalse(p.hasPermission("perm1"));
        assertTrue(p.hasPermission("perm2"));
        assertFalse(p.hasPermission("parent1"));  // Check this is not still set
        assertTrue(p.isPermissionSet("perm1"));
        assertTrue(p.isPermissionSet("perm2"));
        assertFalse(p.isPermissionSet("parent1"));
    }

    @Test
    public void testBulkRemove() {

        Permissible p = getPermissible();
        Plugin plugin = new TestPlugin("permissionsTest");
        PermissionAttachment pa = p.addAttachment(plugin);

        pa.setPermission("perm1", true);
        pa.setPermission("perm2", false);
        pa.setPermission("parent1", true);

        List<String> l = new LinkedList<String>();
        l.add("perm1");
        l.add("perm2");
        l.add("child1");    // Should still be true since not explicitly set
        pa.unsetPermissions(l);

        assertFalse(p.hasPermission("perm1"));
        assertFalse(p.hasPermission("perm2"));
        assertTrue(p.hasPermission("parent1"));  // Still set
        assertTrue(p.hasPermission("child1"));   // True as per parent
        assertFalse(p.isPermissionSet("perm1"));
        assertFalse(p.isPermissionSet("perm2"));
        assertTrue(p.isPermissionSet("parent1")); // Still set
        assertTrue(p.isPermissionSet("child1"));  // True as per parent

    }
}
