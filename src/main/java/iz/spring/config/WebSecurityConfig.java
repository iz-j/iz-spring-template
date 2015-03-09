package iz.spring.config;

import iz.spring.config.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackages = { "iz.spring" })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityService securityService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		securityService.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		securityService.configure(http);
	}
}
