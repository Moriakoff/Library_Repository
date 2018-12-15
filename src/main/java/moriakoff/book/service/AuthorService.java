package moriakoff.book.service;

import moriakoff.book.entity.Author;

import java.util.List;

public interface AuthorService {

    boolean add(Author author);

    boolean delete(Author author);

    boolean delete(String fName, String lName);

    boolean update(Author authorDto);

    Author getAuthor(String fName, String lName);

    List<Author> getAuthors ();

}
