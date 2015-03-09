package iz.spring.sample.mvc;

import java.util.concurrent.atomic.AtomicInteger;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample")
public class SampleController {

	private static final AtomicInteger ACCESS_COUNT = new AtomicInteger(0);

	@RequestMapping(value = "first", method = RequestMethod.GET)
	public ModelAndView first() {
		ModelAndView mv = new ModelAndView("sample1");
		mv.getModelMap().addAttribute("time", new DateTime());
		mv.getModelMap().addAttribute("count", ACCESS_COUNT.addAndGet(1));
		return mv;
	}

}
