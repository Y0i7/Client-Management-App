package com.Client.Managment.Persistence.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
/*
 * @Author: Orlando Yepes Espitia
 * @Date: 2025/26/04
 * @Description: This class is used to convert LocalDateTime to Timestamp and vice versa.
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime){
        if (localDateTime == null){
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp){
        if (timestamp == null){
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
