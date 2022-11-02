package main;

import response.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static int bookId = 1;
    private static final HashMap<Integer, Book> books = new HashMap<>();

    public static List<Book> getAllBooks () {
        return new ArrayList<>(books.values());
    }

    public static int addBook(Book book) {
        int id = bookId++;
        book.setId(id);
        books.put(id, book);
        return id;
    }

    public static Book getBook(int id) {
        if (books.containsKey(id))
            return books.get(id);
        return null;
    }

    public static void deleteBook (int id) {
        books.remove(id);
    }

    public static void deleteAllBook () {
        bookId = 1;
        books.clear();
    }

    public static void modifyBook (int id, Book book) {
        if (books.containsKey(id)) {
            books.put(id, book);
        }
    }
}
