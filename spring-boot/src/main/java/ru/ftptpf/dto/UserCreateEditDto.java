package ru.ftptpf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.validation.UserInfo;
import ru.ftptpf.validation.group.CreateAction;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@UserInfo(groups = {CreateAction.class})
public class UserCreateEditDto {
    @Email
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Size(min = 3, max = 64)
    private String firstname;

    private String lastname;

    private Role role;

    private Integer companyId;

    MultipartFile image;
}
