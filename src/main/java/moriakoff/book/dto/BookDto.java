package moriakoff.book.dto;

import lombok.*;


import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "isbn")
public class BookDto {

    @NonNull
    Long isbn;

    Set<AuthorDto> authors = new ConcurrentSkipListSet<>();

    @NonNull
    String title;

    @NonNull
    PublisherDto publisher;

    @NonNull
    LocalDate edition;

    @NonNull
    double price;

    // FIXME: 12/12/2018
    public boolean addAuthor (AuthorDto author){
        return authors.add(author);
    }
}
