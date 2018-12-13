package moriakoff.book.service;

import moriakoff.book.dto.AuthorDto;
import moriakoff.book.dto.BookDto;
import moriakoff.book.dto.PublisherDto;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Publisher;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    Book getBook(long isbn);

    boolean add(BookDto book);

    Book delete(long isbn);

    Book update(BookDto book);

    Book addRandomBook();

    List <BookDto> addBooks(List <BookDto> books);

    List <Book> getAll();

    List <Book> getAllBooksByPublisher(PublisherDto publisher);

    List <Book> getAllBooksByAuthor(AuthorDto author);

    List <Book> getAllBooksBetweenEdition(LocalDate from, LocalDate to);

    List <Book> getAllBooksBetweenPrice(double from, double to);

    List <Book> fillRepository(int amount);

    void deleteBooks(List <Long> isbns);
}
