package ru.ftptpf.dto;

import lombok.Value;
import ru.ftptpf.database.entity.Role;

import java.time.LocalDate;

@Value
public final class UserCreateEditDto {
    private final String username;
    private final LocalDate birthDate;
    private final String firstname;
    private final String lastname;
    private final Role role;
    private final Integer companyId;

    public static final class Fields {
        public static final String username = "username";
        public static final String birthDate = "birthDate";
        public static final String firstname = "firstname";
        public static final String lastname = "lastname";
        public static final String role = "role";
        public static final String companyId = "companyId";
    }
}
