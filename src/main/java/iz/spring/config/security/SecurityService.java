package iz.spring.config.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

	void configure(AuthenticationManagerBuilder auth) throws Exception;

	void configure(HttpSecurity http) throws Exception;
}
