package ru.ftptpf.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//исключаем users, чтобы не было зацикливания
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@Builder
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<User> users;
}
