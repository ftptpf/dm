package ru.ftptpf.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.service.UserService;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;

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
                        .defaultSuccessUrl("/api/v1/users")
                        .userInfoEndpoint(userINfo -> userINfo.oidcUserService(oidcUserService()))
                );
        return http.build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return userRequest -> {
            String email = userRequest.getIdToken().getEmail();
            // TODO: create user if not exists UserService.create
            UserDetails userDetails = userService.loadUserByUsername(email);
//            new OidcUserService().loadUser();
            DefaultOidcUser oidcUser = new DefaultOidcUser(userDetails.getAuthorities(), userRequest.getIdToken());
            Set<Method> userDetailsMethods = Set.of(UserDetails.class.getMethods());

            return (OidcUser) Proxy.newProxyInstance(SecurityConfiguration.class.getClassLoader(),
                    new Class[]{UserDetails.class, OidcUser.class},
                    (proxy, method, args) -> userDetailsMethods.contains(method)
                            ? method.invoke(userDetails, args)
                            : method.invoke(oidcUser, args));
        };
    }
}
