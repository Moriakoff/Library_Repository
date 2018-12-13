package moriakoff.book.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Entity
@Table(name = "books")

@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book implements Serializable {
    @Id
    Long isbn;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "Book_Author",
            joinColumns = {@JoinColumn(name = "isbn")},
            inverseJoinColumns = {@JoinColumn(name = "author_first_name"),
                                  @JoinColumn(name = "author_last_name")})
    @JsonBackReference
    Set <Author> authors = new ConcurrentSkipListSet<>();

    String title;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    Publisher publisher;

    LocalDate edition;

    double price;


}
