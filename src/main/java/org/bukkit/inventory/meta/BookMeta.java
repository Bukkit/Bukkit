package org.bukkit.inventory.meta;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.chat.Message;

/**
 * Represents a book ({@link Material#BOOK_AND_QUILL} or {@link
 * Material#WRITTEN_BOOK}) that can have a title, an author, and pages.
 */
public interface BookMeta extends ItemMeta {

    /**
     * Checks for the existence of a title in the book.
     *
     * @return true if the book has a title
     */
    boolean hasTitle();

    /**
     * Gets the title of the book.
     * <p>
     * Plugins should check that hasTitle() returns true before calling this
     * method.
     *
     * @return the title of the book
     */
    String getTitle();

    /**
     * Sets the title of the book.
     * <p>
     * Limited to 16 characters. Removes title when given null.
     *
     * @param title the title to set
     * @return true if the title was successfully set
     */
    boolean setTitle(String title);

    /**
     * Checks for the existence of an author in the book.
     *
     * @return the author of the book
     */
    boolean hasAuthor();

    /**
     * Gets the author of the book.
     * <p>
     * Plugins should check that hasAuthor() returns true before calling this
     * method.
     *
     * @return the author of the book
     */
    String getAuthor();

    /**
     * Sets the author of the book. Removes author when given null.
     *
     * @param author the author of the book
     */
    void setAuthor(String author);

    /**
     * Checks for the existence of pages in the book.
     *
     * @return true if the book has pages
     */
    boolean hasPages();

    /**
     * Gets the specified page in the book. The given page must exist.
     *
     * @param page the page number to get
     * @return the page from the book
     * @deprecated This method now uses {@link Message} to return the page. Use
     *     {@link #getPageMessage(int)} instead.
     */
    @Deprecated
    String getPage(int page);

    /**
     * Gets the specified page in the book. The given page must exist.
     *
     * @param page the page number to get
     * @return the page from the book
     */
    Message getPageMessage(int page);

    /**
     * Sets the specified page in the book. Pages of the book must be
     * contiguous.
     * <p>
     * The data can be up to 256 characters in length, additional characters
     * are truncated.
     *
     * @param page the page number to set
     * @param data the data to set for that page
     * @deprecated This method now uses {@link Message} to set the page. Use
     *     {@link #setPage(int, Message)} instead.
     */
    void setPage(int page, String data);

    /**
     * Sets the specified page in the book. Pages of the book must be
     * contiguous. If the given page exists already it will be overwritten.
     * <p>
     * The page will be truncated automatically if it does not fit on the page
     * entirely.
     *
     * @param page the page number to set
     * @param data the data to set for that page
     */
    void setPage(int page, Message data);

    /**
     * Gets all the pages in the book.
     *
     * @return list of all the pages in the book
     * @param data the data to set for that page
     * @deprecated This method now uses {@link Message} to return the pages. Use
     *     {@link #getPageMessages()} instead.
     */
    @Deprecated
    List<String> getPages();

    /**
     * Gets all the pages in the book. Changing the list or its contents does
     * not have any impact on the book itself.
     *
     * @return list of all the pages in the book
     */
    List<Message> getPageMessages();

    /**
     * Clears the existing book pages, and sets the book to use the provided
     * pages. Maximum 50 pages with 256 characters per page.
     *
     * @param pages A list of pages to set the book to use
     * @deprecated This method now uses {@link Message} to set the pages. Use
     *     {@link #setPageMessages(List)} instead.
     */
    @Deprecated
    void setPages(List<String> pages);

    /**
     * Clears the existing book pages, and sets the book to use the provided
     * pages. Maximum 50 pages. Any additional pages or character that won't fit
     * on a page will be truncated automatically.
     *
     * @param pages A list of pages to set the book to use
     */
    void setPageMessages(List<Message> pages);

    /**
     * Clears the existing book pages, and sets the book to use the provided
     * pages. Maximum 50 pages with 256 characters per page.
     *
     * @param pages A list of strings, each being a page
     * @deprecated This method now uses {@link Message} to set the pages. Use
     *     {@link #setPages(Message)} instead.
     */
    @Deprecated
    void setPages(String... pages);

    /**
     * Clears the existing book pages, and sets the book to use the provided
     * pages. Maximum 50 pages. Any additional pages or character that won't fit
     * on a page will be truncated automatically.
     *
     * @param pages A list of {@link Message}, each being a page
     */
    void setPages(Message... pages);

    /**
     * Adds new pages to the end of the book. Up to a maximum of 50 pages with
     * 256 characters per page.
     *
     * @param pages A list of strings, each being a page
     * @deprecated This method now uses {@link Message} to add the pages. Use
     *     {@link #addPage(Message)} instead.
     */
    @Deprecated
    void addPage(String... pages);

    /**
     * Adds new pages to the end of the book. Maximum 50 pages. Any additional
     * pages or character that won't fit on a page will be truncated
     * automatically.
     *
     * @param pages A list of {@link Message}s, each being a page
     */
    void addPage(Message... pages);

    /**
     * Gets the number of pages in the book.
     *
     * @return the number of pages in the book
     */
    int getPageCount();

    BookMeta clone();
}
