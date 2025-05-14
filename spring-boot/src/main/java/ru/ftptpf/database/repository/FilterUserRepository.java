package ru.ftptpf.database.repository;

import ru.ftptpf.database.entity.Role;
import ru.ftptpf.database.entity.User;
import ru.ftptpf.dto.PersonalInfo;
import ru.ftptpf.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);

}
