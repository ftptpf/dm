package ru.ftptpf.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.ftptpf.entity.Company;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"company"})
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Embedded
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;
    @JdbcTypeCode(SqlTypes.JSON)
    private String info;

    // лучше не делать здесь каскадное сохранение, так как когда сохраняем юзера, компания уже должна существовать
    // компания является главной сущностью над юзером
    // CascadeType лучше ставить над ассоциацией OneToMane
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "company_id")
    private Company company;

}
