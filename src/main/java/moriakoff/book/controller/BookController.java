package moriakoff.book.controller;


import moriakoff.book.dto.AuthorDto;
import moriakoff.book.dto.BookDto;
import moriakoff.book.dto.PublisherDto;
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
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;



    @GetMapping("/randomBook")
    public Book addRandomBook() {
        return bookService.addRandomBook();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public boolean addBook(@RequestBody BookDto book) {
        return bookService.add(book);
    }

    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable("id") long id) {
        return bookService.delete(id);
    }

    @PutMapping
    public Book updateBook(@RequestBody BookDto book) {
        return bookService.update(book);
    }

    @GetMapping("/get_all")
    public List <Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/get_all/publisher/{publisherName}")
    public List <Book> getAllBooksByPublisher(@PathVariable("publisherName") String publisherName) {
        return bookService.getAllBooksByPublisher(new PublisherDto(publisherName));
    }

    @GetMapping("get_all/author/")
    public List <Book> getAllBooksByAuthor(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName) {
        return bookService.getAllBooksByAuthor(new AuthorDto(firstName,lastName));
    }

    @GetMapping("get_all/edition")
    public List <Book> getAllBooksBetweenEditon(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return bookService.getAllBooksBetweenEdition(from, to);
    }

    @GetMapping("get_all/price")
    public List <Book> getAllBooksBetweenPrice(@RequestParam("from") double from,
                                               @RequestParam("to") double to) {
        return bookService.getAllBooksBetweenPrice(from, to);
    }

    @PostMapping("/fill_repo")
    public List <Book> fillRepository(@RequestParam("amount") int amount) {
        return bookService.fillRepository(amount);
    }

}
