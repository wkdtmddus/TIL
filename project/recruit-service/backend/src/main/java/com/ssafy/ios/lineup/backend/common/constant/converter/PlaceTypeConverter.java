package com.ssafy.ios.lineup.backend.common.constant.converter;

import com.ssafy.ios.lineup.backend.common.constant.PlaceType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlaceTypeConverter implements AttributeConverter<PlaceType, String> {


    @Override
    public String convertToDatabaseColumn(PlaceType placeType) {
        return placeType.getDbValue();
    }

    @Override
    public PlaceType convertToEntityAttribute(String dbData) {
        return PlaceType.ofCode(dbData);
    }
}
