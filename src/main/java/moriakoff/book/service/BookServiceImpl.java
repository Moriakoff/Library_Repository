package moriakoff.book.service;

import moriakoff.book.configuration.RandomConfig;
import moriakoff.book.entity.Author;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Country;
import moriakoff.book.entity.Publisher;
import moriakoff.book.repository.AuthorRepository;
import moriakoff.book.repository.BookRepository;
import moriakoff.book.repository.CountryRepository;
import moriakoff.book.repository.PublisherRepository;
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
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    PublisherRepository publisherRepository;


    @Override
    public Book getBook(long id) {
        return repository.findById(id).orElse(new Book());
    }

    @Override
    @Transactional
    public boolean add(Book book) {
        if (repository.existsById(book.getIsbn())) return false;
        repository.save(book);
        return true;
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
    public Book update(Book book) {
        if (repository.existsById(book.getIsbn())) return null;
        saveCountry(book);
        savePublisher(book);
        return repository.save(book);
    }

    @Override
    public boolean addRandomBook() {
        return add(RandomConfig.randomBook());
    }

    @Override
    public List <Book> addBooks(List <Book> books) {
        for (Book book:books) {
            saveCountry(book);
            savePublisher(book);
            repository.save(book);
        }
        return books;
    }

    private void savePublisher(Book book) {
        publisherRepository.save(book.getPublisher());
    }

    private void saveCountry(Book book) {
        countryRepository.save(book.getPublisher().getCountryName());
    }

    @Override
    @Transactional(readOnly = true)
    public List <Book> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List <Book> getAllBooksByPublisher(Publisher publisher) {
        return repository.findBooksByPublisher(publisher);
    }

    @Override
    @Transactional(readOnly = true)
    public List <Book> getAllBooksByAuthor(Author author) {
        return repository.findBooksByAuthors(author);
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
        return addBooks(books);
    }

    @Override
    /*test method*/
    public void deleteBooks(List <Book> books) {
        repository.deleteAll(books);
    }

    @Override
    public List <Publisher> getAllPublishersByCountry(String country) {
        return publisherRepository.findPublishersByCountryName(new Country(country));
    }
}
