package iz.springtest.sample;

import org.joda.time.DateTime;

public class TestSampleDto {
	public Long id = 123L;
	public String name = "hoge";
	public DateTime date = new DateTime(0);

	@Override
	public String toString() {
		return "TestSampleDto [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
}
