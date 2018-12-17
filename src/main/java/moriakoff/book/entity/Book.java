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
public class Book implements Serializable, Comparable <Book> {
    @Id
    @Column(name = "isbn")
    private Long isbn;

    /*
     * Can't include CascadeType.REMOVE because one author can have other books;
     */
    @ManyToMany(cascade = 
        {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Books_Authors",
            joinColumns = {@JoinColumn(name = "isbn")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    @JsonBackReference
    private Set <Author> authors = new ConcurrentSkipListSet <>();

    private String title;

    /*
     * Can't include CascadeType.REMOVE because this is not OneToOne: one publisher can have more than one book;
     * Consider @ManyToOne
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "publisher_name")
    @JsonBackReference
    private Publisher publisher;

    @Column(name = "edition_date")
    private LocalDate edition;

    @Column(name = "price")
    private double price;


    @Override
    public int compareTo(Book book) {
        return Long.compare(isbn, book.getIsbn());
    }

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", edition=" + edition + ", price=" + price + "]";
	}
}
