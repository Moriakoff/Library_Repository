package moriakoff.book.entity;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"firstName", "lastName"})
@Getter
@Setter

@Entity
@Table(name = "authors")
@Builder
public class Author implements Serializable, Comparable <Author> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A lot of repeating authors that differ only by ID;
    Integer id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "authors", fetch = FetchType.LAZY)
    private Set <Book> books = new ConcurrentSkipListSet <>();

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Author author) {
        return (firstName + lastName).compareTo(author.getFirstName() + author.getLastName());
    }
}
