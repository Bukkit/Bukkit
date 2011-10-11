   package org.bukkit.event.block;

   import org.bukkit.block.Block;
   import org.bukkit.event.Event;

/**
 * This event is called whenever a block tries to grow (just Crops for now.)
 */
   public class BlockGrowEvent extends BlockEvent {
      protected int oldData;
      protected int newData;
      protected boolean cancelled = false;
    
      public BlockGrowEvent(Block block, int dataOld, int dataNew) {
         super(Event.BLOCK_GROW, block);
         oldData = dataOld;
         newData = dataNew;
      }
    
      public int getOldData() {
         return oldData;
      }
    
      public int getNewData() {
         return newData;
      }
      
   	public void setNewData(int dataNew) {
   		newData = dataNew;
   	}
   	
   	public void setOldData(int dataOld) {
   		oldData = dataOld;
   	}
    
      public boolean isCancelled() {
         return cancelled;
      }
   
      public void setCancelled(boolean cancel) {
         cancelled = cancel;
      }
   }