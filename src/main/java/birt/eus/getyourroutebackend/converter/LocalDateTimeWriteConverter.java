package birt.eus.getyourroutebackend.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class LocalDateTimeWriteConverter implements Converter<LocalDateTime, Date> {
    @Override
    public Date convert(LocalDateTime localDateTime) {        
    	Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
    	return Date.from(instant);
    }
}
