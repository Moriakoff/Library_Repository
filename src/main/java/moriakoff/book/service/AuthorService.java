package moriakoff.book.service;

import moriakoff.book.entity.Author;

import java.util.List;

public interface AuthorService {

    Author add(Author author);

    Author delete(Author author);

    Author delete(Integer id);

    Author update(Author authorDto);

    Author getAuthor(String fName, String lName);

    List <Author> getAuthors();

}
