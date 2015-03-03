package iz.springtest.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestSampleController {
	private static final Logger logger = LoggerFactory.getLogger(TestSampleController.class);

	@RequestMapping(value = "/hello")
	public @ResponseBody String hello() {
		logger.info("#hello");
		return "Hello!";
	}

	@RequestMapping(value = "/dto")
	public @ResponseBody TestSampleDto dto() {
		logger.info("#dto");
		return new TestSampleDto();
	}
}
