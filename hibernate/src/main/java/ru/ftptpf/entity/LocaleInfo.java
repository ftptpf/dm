package ru.ftptpf.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Embeddable
@Table(name = "company_locale")
public class LocaleInfo {

    private String lang;
    private String description;
}
