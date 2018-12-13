package moriakoff.book;

import moriakoff.book.dto.AuthorDto;
import moriakoff.book.dto.BookDto;
import moriakoff.book.dto.PublisherDto;
import moriakoff.book.entity.*;
import moriakoff.book.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
// FIXME: 12/12/2018 
class BookServiceImplTest {


    private BookDto book1;
    private BookDto book2;
    private BookDto book3;
    private BookDto book4;
    private List <BookDto> books;
    private Set<AuthorDto> authors;
    private AuthorDto author1;
    private AuthorDto author2;
    private PublisherDto publisher;
    private List <Long> isbnsList;


    @Autowired
    private BookService model;


    @BeforeEach
    void setUp() {
        authors = new HashSet <>();
        author1 = new AuthorDto("TestAuthor1","Testovich1");
        author2 = new AuthorDto("TestAuthor2","Tectovich2");
        authors.add(author1);
        authors.add(author2);
        String country = "TestCountry";
        publisher = new PublisherDto("TestPublisher",country);

        book1 = new BookDto(1L,authors,"TestTitle1",publisher,
                LocalDate.of(1900,1,31),10.);

        book2 = new BookDto(2L,authors,"TestTitle2",publisher,
                LocalDate.of(1900,2,11),20.);

        book3 = new BookDto(3L,authors,"TestTitle3",publisher,
                LocalDate.of(1900,3,15),30.);

        book4 = new BookDto(4L,authors,"TestTitle4",publisher,
                LocalDate.of(1900,12,31),40.);

        books = new ArrayList <>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        isbnsList = Stream.of(1L,2L,3L,4L)
                .collect(Collectors.toList());


    }

    @AfterEach
    void tearDown() {
        model.deleteBooks(isbnsList);
    }

    @Test
    void getBook() {

    }

    @Test
    void add() {
        assertTrue(model.add(book1));
        assertFalse(model.add(book1));
    }

    @Test
    void delete() {
        assertTrue(model.add(book1));
        assertEquals(book1, model.delete(book1.getIsbn()));
    }

    @Test
    void update() {
        assertTrue(model.add(book1));
        BookDto testBook = new BookDto(book1.getIsbn(),book1.getAuthors(),book1.getTitle(),book1.getPublisher(),
                LocalDate.now(), 100.);
        assertNotEquals(book1, model.update(testBook));
    }

    @Test
    void getAll() {
        assertTrue(model.addBooks(books).containsAll(books));
    }

    @Test
    void getAllBooksByPublisher() {
        model.addBooks(books);
        assertEquals(4, model.getAllBooksByPublisher(publisher).size());
    }

    @Test
    void getAllBooksByAuthor() {
        model.addBooks(books);
        assertEquals(4, model.getAllBooksByAuthor(author1).size());
    }

    @Test
    void getAllBooksBetweenEdition() {
        model.addBooks(books);
        assertEquals(3, model.getAllBooksBetweenEdition(LocalDate.of(1900, 1, 1),
                LocalDate.of(1900, 11, 11)).size());

    }

    @Test
    void getAllBooksBetweenPrice() {
        model.addBooks(books);
        assertEquals(3, model.getAllBooksBetweenPrice(9., 31.).size());
    }
}