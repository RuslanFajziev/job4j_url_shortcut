package ru.job4j.urlshortcut;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import ru.job4j.urlshortcut.filter.JWTAuthenticationFilter;
import ru.job4j.urlshortcut.filter.JWTAuthorizationFilter;
import ru.job4j.urlshortcut.service.UserDetailsServiceImpl;

import static ru.job4j.urlshortcut.filter.JWTAuthenticationFilter.REGISTRATION_URL;
import static ru.job4j.urlshortcut.filter.JWTAuthenticationFilter.REDIRECT_URL;
import static ru.job4j.urlshortcut.filter.JWTAuthenticationFilter.STATISTIC_URL;
import static ru.job4j.urlshortcut.filter.JWTAuthenticationFilter.STATISTIC_ALL_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, REGISTRATION_URL).permitAll()
                .antMatchers(HttpMethod.GET, REDIRECT_URL).permitAll()
                .antMatchers(HttpMethod.GET, STATISTIC_URL).permitAll()
                .antMatchers(HttpMethod.GET, STATISTIC_ALL_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                /* this disables session creation on Spring Security */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}