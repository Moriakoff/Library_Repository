package moriakoff.book.configuration;

import moriakoff.book.entity.Country;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CountryStringConverter implements AttributeConverter<Country,String> {
    @Override
    public String convertToDatabaseColumn(Country country) {
        return country == null ? null : country.getCountryName();
    }

    @Override
    public Country convertToEntityAttribute(String s) {
        return s == null ? null : new Country(s);
    }
}
