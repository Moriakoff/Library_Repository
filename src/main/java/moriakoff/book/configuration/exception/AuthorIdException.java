package moriakoff.book.configuration.exception;

public class AuthorIdException extends RuntimeException {

    public AuthorIdException() {
        super("Author doesn't exist by id");
    }

    public AuthorIdException(String message) {
        super(message);
    }
}
