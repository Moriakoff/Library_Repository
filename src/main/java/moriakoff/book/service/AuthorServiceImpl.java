package moriakoff.book.service;

import moriakoff.book.entity.Author;
import moriakoff.book.exception.AuthorIdException;
import moriakoff.book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    @Transactional
    public Author add(Author author) {
        if (authorRepository.existsById(author.getId())) {
            return null;
        }
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author delete(Integer id) {
        Author authorEntity = authorRepository.findById(id)
                .orElse(null);
        if (authorEntity == null) {
            throw new AuthorIdException();
        }
        authorRepository.delete(authorEntity);
        return authorEntity;
    }

    @Override
    @Transactional
    public Author delete(Author author) {
        Author authorEntity = authorRepository.findById(author.getId())
                .orElse(null);
        if (authorEntity == null) {
            throw new AuthorIdException();
        }
        authorRepository.delete(authorEntity);
        return authorEntity;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        if (authorRepository.existsById(author.getId())) {
            return authorRepository.save(author);
        }
        throw new AuthorIdException();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthor(String fName, String lName) {
        return authorRepository.findAuthorByFirstNameAndLastName(fName, lName);
    }

    @Override
    @Transactional(readOnly = true)
    public List <Author> getAuthors() {
        return authorRepository.findAll();
    }
}
