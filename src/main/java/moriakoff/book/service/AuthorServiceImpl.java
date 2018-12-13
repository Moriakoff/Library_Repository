package moriakoff.book.service;

import moriakoff.book.dto.AuthorDto;
import moriakoff.book.dto.BookDto;
import moriakoff.book.entity.Author;
import moriakoff.book.entity.AuthorId;
import moriakoff.book.entity.Book;
import moriakoff.book.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public boolean add(AuthorDto author) {
        if(authorRepository.existsById(new AuthorId(author.getFirstName(),author.getLastName()))) return false;
        authorRepository.save(authorDtoToAuthor(author));
        return false;
    }

    private Author authorDtoToAuthor(AuthorDto author) {
        Author authorEntity = new Author();
        BeanUtils.copyProperties(author,authorEntity);
        return authorEntity;
    }

    @Override
    public boolean delete(AuthorDto author) {
        Author authorEntity = authorRepository.findById(new AuthorId(author.getFirstName(), author.getLastName()))
                .orElse(null);
        if(authorEntity == null){
            return false;
        }
        authorRepository.delete(authorEntity);
        return true;
    }

    @Override
    public boolean delete(String fName, String lName) {
        Author authorEntity = authorRepository.findById(new AuthorId(fName, lName))
                .orElse(null);
        if(authorEntity == null){
            return false;
        }
        authorRepository.delete(authorEntity);
        return true;
    }

    @Override
    public boolean update(AuthorDto authorDto) {
        if (authorRepository.existsById(new AuthorId(authorDto.getFirstName(),authorDto.getLastName()))) {
            authorRepository.save(authorDtoToAuthor(authorDto));
            return true;
        }
        return false;
    }

    @Override
    public AuthorDto getAuthor(String fName, String lName) {
        return authorToAuthorDto(authorRepository.findById(new AuthorId(fName, lName)).orElse(new Author()));
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    private AuthorDto authorToAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        BeanUtils.copyProperties(author,authorDto);
        return authorDto;
    }
}
