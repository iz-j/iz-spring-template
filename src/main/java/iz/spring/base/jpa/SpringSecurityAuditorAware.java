package iz.spring.base.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Spring-Data-JPA Auditing.<br>
 * For <code>@CreatedBy</code> and <code>@LastModifiedBy</code>.
 * 
 * @author izumi_j
 *
 */
public final class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null || !auth.isAuthenticated()) {
			return "anonymous";
		}

		return auth.getName();
	}

}
