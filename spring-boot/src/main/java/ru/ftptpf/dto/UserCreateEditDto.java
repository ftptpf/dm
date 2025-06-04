package ru.ftptpf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import ru.ftptpf.database.entity.Role;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class UserCreateEditDto {
    @Email
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull
    @Size(min = 3, max = 64)
    private String firstname;

    @NotNull
    private String lastname;
    private Role role;
    private Integer companyId;
}
