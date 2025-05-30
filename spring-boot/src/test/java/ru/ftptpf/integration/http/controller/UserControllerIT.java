package ru.ftptpf.integration.http.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.ftptpf.dto.UserCreateEditDto;
import ru.ftptpf.integration.IntegrationTestBase;

@AutoConfigureMockMvc
class UserControllerIT extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/users"));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
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