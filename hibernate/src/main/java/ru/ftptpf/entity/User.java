package ru.ftptpf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class User {

    @Id
    private String username;
    private String firstname;
    private String lastname;
    private LocalDate birthDay;
    private Integer age;
/*
    public User() {
    }

    public User(String username, String firstname, String lastname, LocalDate birthDay, Integer age) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDay = birthDay;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", firstname='" + firstname + '\''
                + ", lastname='" + lastname + '\''
                + ", birthDay=" + birthDay
                + ", age=" + age
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(username, user.username)
                && Objects.equals(firstname, user.firstname)
                && Objects.equals(lastname, user.lastname)
                && Objects.equals(birthDay, user.birthDay)
                && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstname, lastname, birthDay, age);
    }*/
}
