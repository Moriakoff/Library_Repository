package moriakoff.book.repository;


import moriakoff.book.entity.Author;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository <Book, Long> {

    List <Book> findBooksByAuthors(Author author);

    List <Book> findBooksByPublisher(Publisher publisherName);

    List <Book> findBooksByEditionBetween(LocalDate from, LocalDate to);

    List <Book> findBooksByPriceBetween(double from, double to);

}
