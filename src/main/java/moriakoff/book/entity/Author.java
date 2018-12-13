package moriakoff.book.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter

@Entity
@Table(name = "authors")
@Builder
@IdClass(AuthorId.class)
public class Author implements Serializable {

    @Id
   String firstName;

    @Id
    String lastName;

    @ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
    Set<Book> books = new ConcurrentSkipListSet<>();

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
