package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import response.Book;

@RestController
public class BookController {

    //@RequestMapping(value = "/books/", method = RequestMethod.GET)
    @GetMapping("/books/")
    public List<Book> list() {
        return StorageBook.getAllBooks();
    }

    //@RequestMapping(value = "/books/", method = RequestMethod.POST)
    @PostMapping("/books/")
    public int add(Book book) {
        return StorageBook.addBook(book);
    }

    @DeleteMapping("/books/{id}")
    public void delete (@PathVariable int id) {
        StorageBook.deleteBook(id);
    }

    @DeleteMapping("/books/")
    public void deleteAll () {
        StorageBook.deleteAllBook();
    }

    @PutMapping("/books/")
    public void put(int id, Book book) {
        StorageBook.modifyBook(id, book);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity get(@PathVariable int id) {

        Book book = StorageBook.getBook(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
        }
        return new ResponseEntity(book, HttpStatus.OK);
    }
}