package net.minecraft.server;

import java.util.Random;
//CraftBukkit start
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.block.BlockUpdateCallEvent;
// CraftBukkit end
public class BlockIce extends BlockBreakable {

    public BlockIce(int i, int j) {
        super(i, j, Material.ICE, false);
        this.frictionFactor = 0.98F;
        this.a(true);
    }

    public boolean a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return super.a(iblockaccess, i, j, k, 1 - l);
    }

    public void remove(World world, int i, int j, int k) {
        Material material = world.getMaterial(i, j - 1, k);

        if (material.isSolid() || material.isLiquid()) {
            world.setTypeId(i, j, k, Block.WATER.id);
        }
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, int i, int j, int k, Random random) 
    {
        if (world.a(EnumSkyBlock.BLOCK, i, j, k) > 11 - Block.q[this.id]) 
        {
        	 // CraftBukkit start
            CraftServer server = ((WorldServer) world).getServer();
            CraftWorld cworld = ((WorldServer) world).getWorld();
            BlockUpdateCallEvent event = new BlockUpdateCallEvent(cworld.getBlockAt(i, j, k));
            server.getPluginManager().callEvent(event);

            if (event.isCancelled()) return;
            // CraftBukkit end
            this.a_(world, i, j, k, world.getData(i, j, k));
            world.setTypeId(i, j, k, Block.STATIONARY_WATER.id);
        }
    }
}
