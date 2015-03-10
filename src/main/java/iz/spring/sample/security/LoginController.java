package iz.spring.sample.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ModelAndView
	page(HttpServletRequest request, @RequestParam(value = "error", required = false) boolean error) {
		logger.debug("#page error={}", error);
		request.getUserPrincipal();
		final ModelAndView mv = new ModelAndView("login/login");
		mv.addObject("error", error);
		return mv;
	}

	@RequestMapping("/error")
	public String error() {
		logger.debug("#error");
		return "login/error";
	}
}
