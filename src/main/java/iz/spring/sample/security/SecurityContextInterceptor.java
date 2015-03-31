package iz.spring.sample.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * Interceptor to set the user information to the response.
 *
 * @author izumi_j
 *
 */
public final class SecurityContextInterceptor implements WebRequestInterceptor {

	@Override
	public void preHandle(WebRequest request) throws Exception {
		// NOOP
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
			model.addAttribute("_username", auth.getName());
		} else {
			model.addAttribute("_username", StringUtils.EMPTY);
		}
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		// NOOP
	}

}
