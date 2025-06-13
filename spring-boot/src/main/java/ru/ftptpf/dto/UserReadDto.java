package ru.ftptpf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ftptpf.database.entity.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserReadDto {
    private Long id;
    private String username;
    private LocalDate birthDate;
    private String firstname;
    private String lastname;
    private String image;
    private Role role;
    private CompanyReadDto company;
}
