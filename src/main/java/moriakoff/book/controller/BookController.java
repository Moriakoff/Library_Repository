package moriakoff.book.controller;


import moriakoff.book.entity.Author;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Publisher;
import moriakoff.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/randomBook")
    public Book addRandomBook() {
        return bookService.addRandomBook();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.add(book);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable("id") long id) {
        return bookService.delete(id);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.update(book);
    }

    @GetMapping("/all")
    public List <Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/all/publisher/{publisherName}")
    public List <Book> getAllBooksByPublisher(@PathVariable("publisherName") String publisherName) {
        return bookService.getAllBooksByPublisher(new Publisher(publisherName));
    }

    @GetMapping("all/author/")
    public List <Book> getAllBooksByAuthor(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName) {
        return bookService.getAllBooksByAuthor(new Author(firstName, lastName));
    }

    @GetMapping("all/edition")
    public List <Book> getAllBooksBetweenEditon(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return bookService.getAllBooksBetweenEdition(from, to);
    }

    @GetMapping("all/price")
    public List <Book> getAllBooksBetweenPrice(@RequestParam("from") double from,
                                               @RequestParam("to") double to) {
        return bookService.getAllBooksBetweenPrice(from, to);
    }

    @PostMapping("/fill_repo")
    public List <Book> fillRepository(@RequestParam("amount") int amount) {
        return bookService.fillRepository(amount);
    }

}
