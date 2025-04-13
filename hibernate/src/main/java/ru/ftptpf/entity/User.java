package ru.ftptpf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    private String username;
/*    private String firstname;
    private String lastname;
    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_day")
    private Birthday birthDay;*/

    @Embedded
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;
    @JdbcTypeCode(SqlTypes.JSON)
    private String info;

}
