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
public class Book implements Serializable, Comparable<Book> {
    @Id
    @Column(name = "ISBN")
    private Long isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Book_Author", joinColumns = {@JoinColumn(name = "isbn")}, inverseJoinColumns = {@JoinColumn(name = "author_first_name"), @JoinColumn(name = "author_last_name")})
    @JsonBackReference
    private Set<Author> authors = new ConcurrentSkipListSet<>();

    private String title;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Publisher_name")
    @JsonBackReference
    private Publisher publisher;

    @Column(name = "Edition_date")
    private LocalDate edition;

    @Column(name = "Price")
    private double price;


    @Override
    public int compareTo(Book book) {
        return Long.compare(isbn, book.getIsbn());
    }
}
