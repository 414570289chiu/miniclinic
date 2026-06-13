package tw.edu.fju.miniclinic.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convertToDatabaseColumn(LocalDate date) {
        return (date == null) ? null : date.format(FORMATTER);
    }

    @Override
    public LocalDate convertToEntityAttribute(String s) {
        return (s == null || s.isEmpty()) ? null : LocalDate.parse(s, FORMATTER);
    }
}