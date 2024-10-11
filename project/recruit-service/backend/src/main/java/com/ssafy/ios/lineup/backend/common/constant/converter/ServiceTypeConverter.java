package com.ssafy.ios.lineup.backend.common.constant.converter;

import com.ssafy.ios.lineup.backend.common.constant.ServiceType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

/**
 * packageName    : com.ssafy.ios.lineup.backend.common.constant.converter
 * fileName       : ServiceTypeConverter
 * author         : moongi
 * date           : 10/2/24
 * description    :
 */
@Converter(autoApply = true)
public class ServiceTypeConverter implements AttributeConverter<ServiceType, String> {

    @Override
    public String convertToDatabaseColumn(ServiceType attribute) {
        return attribute.getDbValue();
    }

    @Override
    public ServiceType convertToEntityAttribute(String dbData) {
        return ServiceType.ofCode(dbData);
    }
}
