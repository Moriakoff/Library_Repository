package moriakoff.book.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter

@Entity
@Table(name = "authors")
public class Author implements Serializable {

    @EmbeddedId
    AuthorId id;

    @ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
            //@JsonBackReference
    Set<Book> books = new HashSet <>();

    public Author(AuthorId id) {
        this.id = id;
    }
}
