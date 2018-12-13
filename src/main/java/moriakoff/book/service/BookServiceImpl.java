package moriakoff.book.service;

import moriakoff.book.configuration.RandomConfig;
import moriakoff.book.dto.AuthorDto;
import moriakoff.book.dto.BookDto;
import moriakoff.book.dto.PublisherDto;
import moriakoff.book.entity.Author;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Country;
import moriakoff.book.entity.Publisher;
import moriakoff.book.repository.AuthorRepository;
import moriakoff.book.repository.BookRepository;
import moriakoff.book.repository.CountryRepository;
import moriakoff.book.repository.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository repository;


    @Override
    public Book getBook(long id) {
        return repository.findById(id).orElse(new Book());
    }

    @Override
    @Transactional
    public boolean add(BookDto book) {
        if (repository.existsById(book.getIsbn())) return false;
        repository.save(bookDtoToBook(book));
        return true;
    }

    private Book bookDtoToBook(BookDto book) {
        Book bookEntity = new Book();
        BeanUtils.copyProperties(book,bookEntity);
        return bookEntity;
    }


    @Override
    @Transactional
    public Book delete(long id) {
        Book book = getBook(id);
        if (book != null) repository.deleteById(id);
        return book;
    }

    @Override
    @Transactional
    public Book update(BookDto book) {
        if (repository.existsById(book.getIsbn())) return null;
        return repository.save(bookDtoToBook(book));
    }

    @Override
    public Book addRandomBook() {
        return repository.save(RandomConfig.randomBook());
    }

    @Override
    public List <BookDto> addBooks(List <BookDto> books) {
        for (BookDto book:books) {
            repository.save(bookDtoToBook(book));
        }
        return books;
    }
    @Override
    @Transactional(readOnly = true)
    public List <Book> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List <Book> getAllBooksByPublisher(PublisherDto publisher) {
        return repository.findBooksByPublisher(publisherDtoToPublisher(publisher));
    }

    private Publisher publisherDtoToPublisher(PublisherDto publisher) {
        Publisher publisherEntity = new Publisher();
        BeanUtils.copyProperties(publisher,publisherEntity);
        return publisherEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List <Book> getAllBooksByAuthor(AuthorDto author) {
        return repository.findBooksByAuthors(authorDtoToAuthor(author));
    }

    private Author authorDtoToAuthor(AuthorDto author) {
        Author authorEntity = new Author();
        BeanUtils.copyProperties(author,authorEntity);
        return authorEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public List <Book> getAllBooksBetweenEdition(LocalDate from, LocalDate to) {
        return repository.findBooksByEditionBetween(from, to);
    }

    @Override
    @Transactional(readOnly = true)
    public List <Book> getAllBooksBetweenPrice(double from, double to) {
        return repository.findBooksByPriceBetween(from, to);
    }

    @Override
    public List <Book> fillRepository(int amount) {
        List <Book> books = new ArrayList <>();
        for (int i = 0; i < amount; i++) {
            books.add(RandomConfig.randomBook());
        }
        return repository.saveAll(books);
    }

    @Override
    /*test method*/
    public void deleteBooks(List <Long> isbns) {
        for (Long isbn : isbns) {
            repository.deleteById(isbn);
        }
    }
}
