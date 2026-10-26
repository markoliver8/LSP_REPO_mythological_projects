
package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages books and lending operations for a library.
 *
 * @author Student
 */
public class Library {
    private static final int MAX_BORROWED_BOOKS = 3;

    private final Map<String, Book> books = new HashMap<>();
    private int borrowedBookCount = 0;

    /**
     * Adds a book to the library.
     *
     * @param book the book to add
     * @throws IllegalArgumentException if the book is null or its ISBN
     *                                  already exists
     */
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }

        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Duplicate ISBN.");
        }

        books.put(book.getIsbn(), book);
    }

    /**
     * Finds a book by ISBN.
     *
     * @param isbn the ISBN to search for
     * @return the matching book, or null if not found
     */
    public Book findBook(String isbn) {
        return books.get(isbn);
    }

    /**
     * Borrows a book from the library.
     *
     * @param isbn the ISBN of the book to borrow
     * @throws IllegalArgumentException if the ISBN is not in the library
     * @throws IllegalStateException if the book is already borrowed or
     *                               the borrowing limit has been reached
     */
    public void borrowBook(String isbn) {
        Book book = findBook(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is already borrowed.");
        }

        if (borrowedBookCount >= MAX_BORROWED_BOOKS) {
            throw new IllegalStateException("Borrowing limit reached.");
        }

        // Change the book's state only after all checks pass.
        book.borrowBook();
        borrowedBookCount++;
    }

    /**
     * Returns a borrowed book to the library.
     *
     * @param isbn the ISBN of the book to return
     * @throws IllegalArgumentException if the ISBN is not in the library
     * @throws IllegalStateException if the book is already available
     */
    public void returnBook(String isbn) {
        Book book = findBook(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        if (book.isAvailable()) {
            throw new IllegalStateException("Book is not borrowed.");
        }

        // Change the book's state only after validation.
        book.returnBook();
        borrowedBookCount--;
    }

    /**
     * Returns all currently available books.
     *
     * @return a list of available books
     */
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Finds books by an exact, case-sensitive author name.
     *
     * @param author the author to search for
     * @return a list of matching books, including borrowed books
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();

        for (Book book : books.values()) {
            if (author == null ? book.getAuthor() == null
                    : author.equals(book.getAuthor())) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Returns all books sorted by ISBN in ascending lexicographical order.
     *
     * @return a sorted list of all books
     */
    public List<Book> getBooksSortedByIsbn() {
        List<Book> result = new ArrayList<>(books.values());
        result.sort(Comparator.comparing(Book::getIsbn));
        return result;
    }

    /**
     * Returns the current number of borrowed books.
     *
     * @return the number of borrowed books
     */
    public int getBorrowedBookCount() {
        return borrowedBookCount;
    }
}
