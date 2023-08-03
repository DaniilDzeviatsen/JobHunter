package com.example.jobhunter.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.net.URI;

@Converter(autoApply = true)
public class UriJpaConverter implements AttributeConverter<URI, String> {
    @Override
    public String convertToDatabaseColumn(URI attribute) {
        return attribute==null?null:attribute.toString();
    }

    @Override
    public URI convertToEntityAttribute(String dbData) {
        return dbData==null?null:URI.create(dbData);
    }
}
