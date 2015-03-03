package iz.springtest.sample;

import static org.junit.Assert.*;
import iz.springtest.JettyTestServer;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSample {
	@Rule
	public JettyTestServer testServer = new JettyTestServer();

	@Test
	public void test_hello() {
		String expected = "Hello!";
		RestTemplate rest = new RestTemplate();
		String actual = rest.getForObject("http://localhost:8888/hello", String.class);

		assertEquals(expected, actual);
	}

	@Test
	public void test_dto() throws JsonParseException, JsonMappingException, IOException {
		String expected = "Hello!";
		RestTemplate rest = new RestTemplate();
		String actual = rest.getForObject("http://localhost:8888/dto", String.class);
		TestSampleDto dto = new ObjectMapper().readValue(actual, TestSampleDto.class);
		System.out.println(dto);
		assertEquals(expected, actual);
	}
}
