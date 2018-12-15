package moriakoff.book.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
@IdClass(AuthorId.class)
public class Author implements Serializable, Comparable<Author> {

    @Id
    @Column(name = "First_name")
    private String firstName;

    @Id
    @Column(name = "Last_name")
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<Book> books = new ConcurrentSkipListSet<>();

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Author author) {
        return (firstName + lastName).compareTo(author.getFirstName() + author.getLastName());
    }

    public AuthorId getId(){
        return new AuthorId(firstName, lastName);
    }
}
