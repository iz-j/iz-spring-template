package iz.spring.sample.jpa;

import iz.spring.base.jpa.JodaTimeConverters.JodaDateTimeConverter;

import java.math.BigDecimal;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Sample {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	public String str;

	public BigDecimal bd;

	@CreatedDate
	@Convert(converter = JodaDateTimeConverter.class)
	public DateTime createdAt;

	@CreatedBy
	public String createdBy;

	@Override
	public String toString() {
		return "Sample [id="
				+ id
				+ ", str="
				+ str
				+ ", bd="
				+ bd
				+ ", createdAt="
				+ createdAt
				+ ", createdBy="
				+ createdBy
				+ "]";
	}
}
