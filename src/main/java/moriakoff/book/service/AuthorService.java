package moriakoff.book.service;

import moriakoff.book.dto.AuthorDto;
import moriakoff.book.entity.Author;

import java.util.List;

public interface AuthorService {

    boolean add(AuthorDto author);

    boolean delete(AuthorDto author);

    boolean delete(String fName, String lName);

    boolean update(AuthorDto authorDto);

    AuthorDto getAuthor(String fName, String lName);

    List<Author> getAuthors ();

}
