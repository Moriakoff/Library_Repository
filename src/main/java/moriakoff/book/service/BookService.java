package moriakoff.book.service;

import moriakoff.book.entity.Author;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Publisher;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    Book getBook(long isbn);

    boolean add(Book book);

    Book delete(long isbn);

    Book update(Book book);

    Book addRandomBook();

    List <Book> addBooks(List <Book> books);

    List <Book> getAll();

    List <Book> getAllBooksByPublisher(Publisher publisher);

    List <Book> getAllBooksByAuthor(Author author);

    List <Book> getAllBooksBetweenEdition(LocalDate from, LocalDate to);

    List <Book> getAllBooksBetweenPrice(double from, double to);

    List <Book> fillRepository(int amount);

    void deleteBooks(List <Long> isbns);
}
