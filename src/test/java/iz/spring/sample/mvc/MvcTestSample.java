package iz.spring.sample.mvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import iz.spring.base.config.AppConfig;
import iz.spring.base.config.web.WebMvcConfig;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

// TODO 共通化！
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppConfig.class, WebMvcConfig.class })
public class MvcTestSample {
	private static final Logger logger = LoggerFactory.getLogger(MvcTestSample.class);

	private MockMvc mockMvc;

	@Mock
	private TestSampleService service;
	@InjectMocks
	private TestSampleController controller;

	@Before
	public void setup() {
		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void test_hello() throws Exception {
		final MvcResult result = mockMvc
				.perform(get("http://localhost:8888/hello"))
				.andExpect(status().isOk())
				.andReturn();

		final String actual = result.getResponse().getContentAsString();
		logger.info("actual = {}", actual);
		assertEquals("Hello!", actual);
	}

	@Test
	public void test_dto() throws Exception {
		TestSampleDto expected = new TestSampleDto();
		final MvcResult result = mockMvc
				.perform(get("http://localhost:8888/dto"))
				.andExpect(status().isOk())
				.andReturn();

		final TestSampleDto actual = new ObjectMapper().readValue(result.getResponse().getContentAsByteArray(),
				TestSampleDto.class);
		logger.info("actual = {}", actual);
		assertEquals(expected, actual);
	}

	@Test
	public void test_mock() throws Exception {
		when(service.hoge()).thenReturn("fuga");

		final MvcResult result = mockMvc
				.perform(get("http://localhost:8888/hoge"))
				.andExpect(status().isOk())
				.andReturn();

		final String actual = result.getResponse().getContentAsString();
		logger.info("actual = {}", actual);
		assertEquals("fuga", actual);
	}

	@RestController
	public static class TestSampleController {

		@Autowired
		private TestSampleService service;

		@RequestMapping(value = "/hello")
		public String hello() {
			logger.info("#hello");
			return "Hello!";
		}

		@RequestMapping(value = "/dto")
		public TestSampleDto dto() {
			logger.info("#dto");
			return new TestSampleDto();
		}

		@RequestMapping(value = "/hoge")
		public String hoge() {
			logger.info("#hoge");
			return service.hoge();
		}
	}

	public static class TestSampleDto {
		public Long id = 123L;
		public String name = "hoge";
		public DateTime date = new DateTime(0);

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}

		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this);
		}

		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj);
		}
	}

	public static interface TestSampleService {
		String hoge();
	}

	@Service
	public static class TestSampleServiceImpl implements TestSampleService {
		@Override
		public String hoge() {
			return "hoge";
		}
	}
}
