package moriakoff.book.service;

import moriakoff.book.configuration.exception.AuthorIdException;
import moriakoff.book.entity.Author;
import moriakoff.book.entity.AuthorId;
import moriakoff.book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public boolean add(Author author) {
        if(authorRepository.existsById(author.getId())){
            return false;
        }
        authorRepository.save(author);
        return false;
    }

    @Override
    public boolean delete(Author author) {
        if(authorRepository.existsById(author.getId())){
            throw new AuthorIdException();
        }
        authorRepository.delete(author);
        return true;
    }

    @Override
    public boolean delete(String fName, String lName) {
        Author authorEntity = authorRepository.findById(new AuthorId(fName, lName))
                .orElse(null);
        if(authorEntity == null){
            throw new AuthorIdException();
        }
        authorRepository.delete(authorEntity);
        return true;
    }

    @Override
    public boolean update(Author author) {
        if (authorRepository.existsById(author.getId())) {
            authorRepository.save(author);
            return true;
        }
        throw new AuthorIdException();
    }

    @Override
    public Author getAuthor(String fName, String lName) {
        return authorRepository.getOne(new AuthorId(fName,lName));
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
