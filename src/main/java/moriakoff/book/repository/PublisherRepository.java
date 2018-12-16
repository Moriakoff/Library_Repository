package moriakoff.book.repository;

import moriakoff.book.entity.Country;
import moriakoff.book.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository <Publisher, String> {

    List <Publisher> findPublishersByCountryName(Country country);
}
