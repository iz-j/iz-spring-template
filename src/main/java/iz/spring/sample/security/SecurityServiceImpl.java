package iz.spring.sample.security;

import iz.spring.config.security.SecurityService;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UserDetails() {

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public String getUsername() {
				return "test";
			}

			@Override
			public String getPassword() {
				return new Md5PasswordEncoder().encodePassword("test", null);
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
			}
		};
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this).passwordEncoder(new Md5PasswordEncoder());
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
				.loginPage("/login/page")
				.loginProcessingUrl("/login/try")
				.defaultSuccessUrl("/sample/first")
				.failureUrl("/login/page?error=true")
				.and()
				.logout()
				.permitAll()
				.and()

				// その他
				.csrf()
				.disable();
	}

}
