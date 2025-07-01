package ru.ftptpf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import ru.ftptpf.database.entity.Role;

@Configuration
//@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/api/v1/login", "/api/v1/users/registration", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(RegexRequestMatcher.regexMatcher("/api/v1/users/[1-9]/delete")).hasAuthority(Role.ADMIN.getAuthority())
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .logout(logout -> logout
                        .logoutUrl("/api/v1/logout")
                        .logoutSuccessUrl("/api/v1/login")
                        .deleteCookies("JSESSIONID"))
                .formLogin(formLogin -> formLogin
                        .loginPage("/api/v1/login")
                        .defaultSuccessUrl("/api/v1/users"))
                .oauth2Login(config -> config
                        .loginPage("/api/v1/login")
                        .defaultSuccessUrl("/api/v1/users"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
