package iz.spring.base.jpa;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;

/**
 * JPA Converters for Joda-time.<br>
 * Annotate the field of Entity like bellow:
 * 
 * <pre>
 * &#064;Convert(converter = JodaDateTimeConverter.class)
 * private DateTime someDate;
 * </pre>
 *
 * @author izumi_j
 *
 */
public final class JodaTimeConverters {
	private JodaTimeConverters() {
	}

	@Converter(autoApply = true)
	public static class JodaDateTimeConverter implements AttributeConverter<DateTime, java.util.Date> {

		@Override
		public Date convertToDatabaseColumn(DateTime attribute) {
			return attribute.toDate();
		}

		@Override
		public DateTime convertToEntityAttribute(Date dbData) {
			return new DateTime(dbData);
		}
	}
}
