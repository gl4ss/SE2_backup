package project.ecom.se2_backup.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import project.ecom.se2_backup.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/home", "/auth/login", "/auth/register", "/category/list/**", "/product/list/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/category/update/**", "/category/create", "/category/delete/**", "/product/add", "/product/update/**", "/product/delete/**", "/user/**")
                .hasAuthority(String.valueOf(new SimpleGrantedAuthority("ADMIN")))
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/cart/**", "/order/**")
                .hasAuthority(String.valueOf(new SimpleGrantedAuthority("USER")))
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .authenticationEntryPoint(customAuthenticationEntryPoint())
                .and()
                .build();

    }



}
