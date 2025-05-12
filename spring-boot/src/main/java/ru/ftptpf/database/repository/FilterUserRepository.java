package ru.ftptpf.database.repository;

import ru.ftptpf.database.entity.User;
import ru.ftptpf.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

}
