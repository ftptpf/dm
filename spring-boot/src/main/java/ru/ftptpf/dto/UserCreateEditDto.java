package ru.ftptpf.dto;

import lombok.Value;
import ru.ftptpf.database.entity.Role;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    Integer companyId;
}
