package moriakoff.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moriakoff.book.configuration.CountryStringConverter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "publishers")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher implements Serializable {

    @Id
    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    @Convert(converter = CountryStringConverter.class)
    private Country countryName;


    public Publisher(String name) {
        this.name = name;
    }

}
