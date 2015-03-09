package iz.spring.sample.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public String page(HttpServletRequest request) {
		request.getUserPrincipal();
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "login";
	}

	@RequestMapping("/error")
	public String error() {
		return "login-error";
	}
}
