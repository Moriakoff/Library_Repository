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
public class BookDto implements Comparable<BookDto>{

    @NonNull
    private Long isbn;

    private Set<AuthorDto> authors = new ConcurrentSkipListSet<>();

    @NonNull
    private String title;

    @NonNull
    private PublisherDto publisher;

    @NonNull
    private LocalDate edition;

    @NonNull
    private double price;

    // FIXME: 12/12/2018
    public boolean addAuthor (AuthorDto author){
        return authors.add(author);
    }

    @Override
    public int compareTo(BookDto o) {
        return Long.compare(isbn,o.getIsbn());
    }
}
