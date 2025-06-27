package ru.ftptpf.integration.http.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.dto.UserCreateEditDto;
import ru.ftptpf.integration.IntegrationTestBase;

import java.util.Arrays;
import java.util.List;

@AutoConfigureMockMvc
class UserControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

/*    @BeforeEach
    void init() {
        List<GrantedAuthority> roles = Arrays.asList(Role.ADMIN, Role.USER);
        User testUser = new User("test@gmail.com", "test", roles);
        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(
                testUser, testUser.getPassword(), roles);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);
    }*/

    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                        .with(SecurityMockMvcRequestPostProcessors.user("test@gmail.com")
                                .authorities(Role.ADMIN)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/users"));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .param(UserCreateEditDto.Fields.username, "test@gmail.com")
                        .param(UserCreateEditDto.Fields.birthDate, "2000-01-01")
                        .param(UserCreateEditDto.Fields.firstname, "Test")
                        .param(UserCreateEditDto.Fields.lastname, "TestTest")
                        .param(UserCreateEditDto.Fields.role, "ADMIN")
                        .param(UserCreateEditDto.Fields.companyId, "1")
                )
                .andExpectAll(
                        MockMvcResultMatchers.status().is3xxRedirection(),
                        MockMvcResultMatchers.redirectedUrlPattern("/users/{\\d+}")
                );
    }
}