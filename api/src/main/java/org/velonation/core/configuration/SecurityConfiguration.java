package org.velonation.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:security.properties")
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authorization -> authorization
						.requestMatchers("/api/v1/admin/**").authenticated()
						.anyRequest().permitAll()
				)
				.httpBasic()
				.and().csrf().disable()
				.build();
	}

	@Bean
	public UserDetailsService inMemoryUserDetailsService(
			@Value("${admin.username}") String username,
			@Value("${admin.password}") String password
	) {
		UserDetails userDetails = User.builder()
				.username(username)
				.password(password)
				.authorities(List.of())
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
}
