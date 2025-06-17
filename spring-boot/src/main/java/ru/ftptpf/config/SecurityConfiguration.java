package ru.ftptpf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .anyRequest()
                        .authenticated())
/*                .httpBasic(Customizer.withDefaults());*/
                .formLogin(formLogin -> formLogin
                        .loginPage("/api/v1/login")
                        .defaultSuccessUrl("/api/v1/users")
                        .permitAll());
        return http.build();
    }
}
