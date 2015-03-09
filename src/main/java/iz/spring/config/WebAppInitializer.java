package iz.spring.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(2)
public final class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		Class<?>[] configClasses = new Class[] { WebMvcConfig.class, WebSecurityConfig.class };
		final Class<?> externalConfigClass = ExternalProperties.getConfigClass();
		if (externalConfigClass != null) {
			configClasses = ArrayUtils.add(configClasses, externalConfigClass);
		}

		return configClasses;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("defaultHtmlEscape", "true");
		registration.setInitParameter("spring.profiles.active", "default");
	}
}
