package iz.spring.base.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Spring security configration.
 *
 * @author izumi_j
 *
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		// ユーザーが保護ページにアクセスしたときに表示するページを指定
		.exceptionHandling()
				.accessDeniedPage("/login/error")
				.and()

				// どのロールがどのページにアクセス可能かを指定する
				.authorizeRequests()
				.antMatchers("/js/**", "/css/**", "/img/**", "/login/**")
				.permitAll()
				.antMatchers("/admin/**")
				.hasRole("ADMIN")
				.anyRequest()
				.authenticated()
				.and()

				// ログインフォームの設定：
				// loginPage: ログインビュー
				// loginProcessingUrl: ログインフォームがサブミットされる URL
				// defaultSuccessUrl: ログイン成功時にリダイレクトされる URL
				.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/login/page")
				.loginProcessingUrl("/login/try")
				.defaultSuccessUrl("/sample/first", true)
				.failureUrl("/login/page?error=true")
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login/page")
				.permitAll()
				.and()

				// CSRF
				.csrf();
	}
}
