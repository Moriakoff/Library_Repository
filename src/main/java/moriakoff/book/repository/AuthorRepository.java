package moriakoff.book.repository;

import moriakoff.book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findAuthorByFirstNameAndLastName(String fName, String lName);
}
