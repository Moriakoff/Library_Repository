package moriakoff.book.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")

@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@NoArgsConstructor
@AllArgsConstructor
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
    //@JsonManagedReference
    Set <Author> authors = new HashSet <>();

    String title;


    @ManyToOne(fetch = FetchType.LAZY)
    Publisher publisher;

    LocalDate edition;

    double price;


}
