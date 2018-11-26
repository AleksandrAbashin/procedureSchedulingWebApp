package com.firstline.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZoneOffset;
import java.util.Date;
import java.time.LocalDateTime;


@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime locDate) {
        return (locDate == null ? null : Date.from(locDate.toInstant(ZoneOffset.UTC)));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : LocalDateTime.ofInstant(sqlDate.toInstant(), ZoneOffset.UTC));
    }
}