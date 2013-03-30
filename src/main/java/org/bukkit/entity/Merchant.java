package org.bukkit.entity;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a Entity that can trade with other HumanEntities
 */
public interface Merchant extends Entity {


    /**
     * Get a list of trades that the villages trades
     * 
     * @return all the trades the villager can perform
     */
    public List<MerchantTrade> getTrades();

    /**
     * Get a list of trades that the villages trades for a specific player
     * 
     * @return all the trades the villager can perform to a specific player
     */
    public List<MerchantTrade> getTrades(Player player);

    /**
     * Add a trade to the merchants trades
     * 
     * @param trade a new trade for the villager to perform
     */
    public void addTrade(MerchantTrade trade);
    
    /**
     * Remove a trade from the merchants trades
     * 
     * @param trade the trade to remove
     */
    public void removeTrade(MerchantTrade trade);
    
    /**
     * Set the list of trades for this merchant
     * 
     * @param trades the new trade list
     */
    public void setTrades(List<MerchantTrade> trades);
    
    /**
     * Represents a trade a villager can make
     */
    public class MerchantTrade {

    	private ItemStack product;
    	private ItemStack[] price;
		private int tradesLeft;
    	
    	/**
    	 * Create a new villager trade defining the product and price for that product
    	 * 
    	 * @param product the item that the villager sells
    	 * @param price the item(s) that the villager defines as the price for the product
    	 */
    	public MerchantTrade(ItemStack product, ItemStack...  price) {
    		Validate.isTrue(price.length <= 2, "The price can only be a maximum of two items");
    		
    		this.product = product;
    		this.price = price;
    	}
    	
    	/**
    	 * Get the price of this trade
    	 * 
    	 * @return the price of this trade, either one or two item stacks in array
    	 */
    	public ItemStack[] getPrice() {
    		return price;
    	}
    	
    	/**
    	 * Get the product this villager trades for
    	 * 
    	 * @return the product that this villager trades
    	 */
    	public ItemStack getProduct() {
    		return product;
    	}
    	
    	/**
    	 * Set the price of this trade
    	 * 
    	 * @param price the price of the trade, a maximum of two items
    	 */
    	public void setPrice(ItemStack... price) {
    		Validate.isTrue(price.length <= 2, "The price can only be a maximum of two items");
    		
    		this.price = price;
    	}
    	
    	/**
    	 * Set the product that this villager trades
    	 * 
    	 * @param product the product the villager trades
    	 */
    	public void setProduct(ItemStack product) {
    		this.product = product;
    	}
    	
    	/**
    	 * Gets the amount of trades this specific trade has left
    	 * 
    	 * @return the amount of trades left
    	 */
    	public int getTradesLeft() {
    		return tradesLeft;
    	}
    	
    	/**
    	 * Sets the number of trades left for this trade
    	 * 
    	 * @param left the number of trades left
    	 */
    	public void setTradesLeft(int left) { 
    		this.tradesLeft = left;
    	}
    }
}
