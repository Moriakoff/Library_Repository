package moriakoff.book.controller;


import moriakoff.book.entity.Author;
import moriakoff.book.entity.AuthorId;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Publisher;
import moriakoff.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;



    @GetMapping("/randomBook")
    public boolean addRandomBook() {
        return bookService.addRandomBook();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/book")
    public boolean addBook(@RequestBody Book book) {
        return bookService.add(book);
    }

    @DeleteMapping("/book/{id}")
    public Book deleteBook(@PathVariable long id) {
        return bookService.delete(id);
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return bookService.update(book);
    }

    @GetMapping("/get_all")
    public List <Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/get_all/publisher/{publishername}")
    public List <Book> getAllBooksByPublisher(@PathVariable String publisherName) {
        return bookService.getAllBooksByPublisher(new Publisher(publisherName));
    }

    @GetMapping("get_all/author/")
    public List <Book> getAllBooksByAuthor(@RequestParam String firstName,
                                           @RequestParam String lastName) {
        return bookService.getAllBooksByAuthor(new Author(new AuthorId(firstName, lastName)));
    }

    @GetMapping("get_all/edition")
    public List <Book> getAllBooksBetweenEditon(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return bookService.getAllBooksBetweenEdition(from, to);
    }

    @GetMapping("get_all/price")
    public List <Book> getAllBooksBetweenPrice(@RequestParam double from,
                                               @RequestParam double to) {
        return bookService.getAllBooksBetweenPrice(from, to);
    }

    @PostMapping("/fill_repo")
    public List <Book> fillRepository(@RequestParam int amount) {
        return bookService.fillRepository(amount);
    }


    @GetMapping("/publisher")
    public List<Publisher> getAllPublishersByCountry(@RequestParam String country){
        return bookService.getAllPublishersByCountry(country);
    }
}
