//package birt.eus.getyourroutebackend.converter;
//
//import java.time.Instant;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.util.Date;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.convert.ReadingConverter;
//
//@ReadingConverter
//public class LocalDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
//    @Override
//    public ZonedDateTime convert(Date date) {            	
//    	
//    	Instant instant = date.toInstant();
//    	
//    	ZonedDateTime zonedDateTime = instant.atZone(ZoneOffset.UTC);
//    	return zonedDateTime;
//    	//return LocalDateTime.parse(date.toInstant().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)); //toLocalDateTime();
//    	// return LocalDateTime.ofInstant(date.toInstant(), ZoneOffset.UTC);
//    	//return date.toInstant().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT); //toLocalDateTime();
//    }
//
//}
