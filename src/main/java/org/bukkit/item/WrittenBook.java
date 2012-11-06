package org.bukkit.item;

public interface WrittenBook extends ItemState {
    
    /**
     * Sets author of book
     * 
     * @param author Author
     */
    void setAuthor(String author);
    
    /**
     * Sets title of book
     * 
     * @param title Title
     */
    void setTitle(String title);
    
    /**
     * Sets pages of book
     * 
     * @param pages Pages
     */
    void setPages(String... pages);
    
    /**
     * Adds page to book
     * 
     * @param page Page to add
     */
    void addPage(String page);
    
    /**
     * Gets author of book
     * 
     * @return Author
     */
    String getAuthor();
    
    /**
     * Gets title of book
     * 
     * @return Title
     */
    String getTitle();
    
    /**
     * Gets pages of book
     * 
     * @return Pages
     */
    String[] getPages();
    
    /**
     * Gets page of book
     * 
     * @param page Page number
     * @return Page
     */
    String getPage(int page);
}
