package org.example.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String data) {
        Rating[] ratings = Rating.values();
        for (Rating rating : ratings) {
            if(rating.getValue().equals(data)) return rating;
        }
        return null;
    }
}
