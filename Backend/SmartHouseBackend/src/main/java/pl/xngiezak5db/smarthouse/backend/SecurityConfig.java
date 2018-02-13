package pl.xngiezak5db.smarthouse.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/", "/dashboard", "/room-weather", "/city-weather").hasRole("USER")
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.successForwardUrl("/")
				.failureUrl("/login?error")
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.and()
			.csrf();
		
		//h2 console
		//http.csrf().ignoringAntMatchers("/h2/**");
		
		//http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Please comment this line under and define your own auth
		//Don't look for such class because its 'gitignored'
		MyAuthentication.getAuthConfig(auth);
		
		//You can uncomment this line and define your username and password
		//auth.inMemoryAuthentication().withUser("YOUR_USERNAME").password("YOUR_PASSWORD").roles("USER");
	}
}
