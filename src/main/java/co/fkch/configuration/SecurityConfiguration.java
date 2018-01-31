package co.fkch.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/built/**", "/main.css",
                        "/", "/api", "/api/login", "/api/register", "/api/profile",
                        "/api/confirm", "/api/resendEmail").permitAll()
                .antMatchers(HttpMethod.GET, "/api/challenges", "/api/challenges/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/challenges/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/challenges/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/challenges/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder configurePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
}
