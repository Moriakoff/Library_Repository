package moriakoff.book.dto;

import lombok.*;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"firstName","lastName"})
@Builder
public class AuthorDto implements Comparable<AuthorDto>{

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    Set<BookDto> books = new ConcurrentSkipListSet <>();


    // FIXME: 12/12/2018
    public boolean addBook (BookDto book){
        return books.add(book);
    }


    @Override
    public int compareTo(AuthorDto author) {
        return (firstName + lastName).compareTo(author.firstName + author.lastName);
    }
}
