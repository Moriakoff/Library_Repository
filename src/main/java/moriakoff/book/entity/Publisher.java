package moriakoff.book.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "publishers")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher implements Serializable {

    @Id
    String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    Country countryName;

    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    Set<Book> books = new HashSet<>();

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(String name, Country countryName) {
        this.name = name;
        this.countryName = countryName;


    }
}
