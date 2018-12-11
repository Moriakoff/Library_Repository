package moriakoff.book.repository;

import moriakoff.book.entity.Author;
import moriakoff.book.entity.AuthorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, AuthorId> {
}
