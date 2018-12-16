package moriakoff.book.repository;

import moriakoff.book.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository <Country, String> {
}
