package ru.ftptpf.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface PersonalInfo2 {

    String getFirstName();
    String getLastName();
    String getBirthDate();
    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();

}
