package ru.ftptpf.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"company", "profile"})
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Profile profile;

}
