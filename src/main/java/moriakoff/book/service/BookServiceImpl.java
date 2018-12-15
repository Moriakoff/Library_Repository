package moriakoff.book.service;

import moriakoff.book.configuration.RandomConfig;
import moriakoff.book.entity.Author;
import moriakoff.book.entity.Book;
import moriakoff.book.entity.Publisher;
import moriakoff.book.repository.BookRepository;
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
        return repository.save(book);
    }

    @Override
    public Book addRandomBook() {
        return repository.save(RandomConfig.randomBook());
    }

    @Override
    public List <Book> addBooks(List <Book> books) {
        return repository.saveAll(books);
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
        return repository.saveAll(books);
    }

    @Override
    /*test method*/
    public void deleteBooks(List <Long> isbns) {
        for (int i = 0; i < isbns.size(); i++) {
            repository.deleteById(isbns.get(i));
        }
    }
}
