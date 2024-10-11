package com.ssafy.ios.lineup.backend.common.constant.converter;

import com.ssafy.ios.lineup.backend.common.constant.RecruitStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * packageName    : com.ssafy.ios.lineup.backend.common.constant.converter
 * fileName       : RecruitStatusConverter
 * author         : moongi
 * date           : 10/2/24
 * description    :
 */
@Converter(autoApply = true)
public class RecruitStatusConverter implements AttributeConverter<RecruitStatus, String> {

    @Override
    public String convertToDatabaseColumn(RecruitStatus attribute) {
        return attribute.getDbValue();
    }

    @Override
    public RecruitStatus convertToEntityAttribute(String dbData) {
        return RecruitStatus.ofValue(dbData);
    }
}
