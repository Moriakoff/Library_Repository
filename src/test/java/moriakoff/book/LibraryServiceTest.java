package moriakoff.book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import moriakoff.book.entity.Author;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Country;
import moriakoff.book.entity.Publisher;
import moriakoff.book.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryServiceTest {

	public LibraryServiceTest() {
	}

	static {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}

	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private List<Book> books;
	private Set<Author> authors;
	private Author author1;
	private Author author2;
	private Publisher publisher1Country1;

	@Autowired
	private BookService model;

	@Before 
	public void setUp() {
		authors = new HashSet<>();
		author1 = new Author("TestAuthor1", "Testovich1");
		author2 = new Author("TestAuthor2", "Tectovich2");
		authors.add(author1);
		authors.add(author2);
		

        book1 = new Book(1l,authors,"TestTitle1",publisher1Country1,
                LocalDate.of(1900,1,31),10.);

        book2 = new Book(2l,authors,"TestTitle2",publisher1Country1,
                LocalDate.of(1900,2,11),20.);

        book3 = new Book(3l,authors,"TestTitle3",publisher1Country1,
                LocalDate.of(1900,3,15),30.);

        book4 = new Book(4l,authors,"TestTitle4",publisher1Country1,
                LocalDate.of(1900,12,31),40.);

		books = new ArrayList<>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);

		
	}

	/*
	 * Doesn't work probably due to unknown test framework (JUnit? or Maven POJO?)
	 * https://stackoverflow.com/questions/49441049/junit-5-does-not-execute-method-annotated-with-beforeeach
	 * 
	 */
	@AfterEach
	public void tearDown() {
		System.err.println("Checking that afterEach was called");
		model.delete(book1.getIsbn());
	}

	@Test
	public void testGetBook() {
		model.add(book1);
		assertEquals(model.getBook(book1.getIsbn()), book1);
	}

	@Test
	public void testAdd() { 
		model.add(book1);
		assertEquals(null, model.add(book1));
	}

	@Test
	public void testClear() {
		model.add(book1);
		model.delete(book1.getIsbn());
		assertEquals(book1, model.add(book1));
	}
	
	@Test
	public void testDelete() {
		model.add(book1);
		Book deletedBook = model.delete(book1.getIsbn());
		assertEquals(book1, deletedBook);
	}

	@Test
	public void testGetAll() {
		assertTrue(model.addBooks(books).containsAll(books));
	}

}
