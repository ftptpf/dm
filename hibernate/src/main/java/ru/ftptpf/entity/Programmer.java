package ru.ftptpf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Programmer extends User {

    @Builder
    public Programmer(Long id, String username, PersonalInfo personalInfo, Role role, String info, Company company, Profile profile, List<UserChat> userChats, Language language) {
        super(id, username, personalInfo, role, info, company, profile, userChats);
        this.language = language;
    }

    @Enumerated(EnumType.STRING)
    private Language language;
}
