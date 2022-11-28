package birt.eus.getyourroutebackend.converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class LocalDateTimeReadConverter implements Converter<Date, LocalDateTime> {
    @Override
    public LocalDateTime convert(Date date) {        
    	return date.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
    }

}
