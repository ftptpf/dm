package ru.ftptpf.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ftptpf.database.querydsl.QPredicates;
import ru.ftptpf.database.repository.UserRepository;
import ru.ftptpf.dto.UserCreateEditDto;
import ru.ftptpf.dto.UserFilter;
import ru.ftptpf.dto.UserReadDto;
import ru.ftptpf.mapper.UserCreateEditMapper;
import ru.ftptpf.mapper.UserReadMapper;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       UserReadMapper userReadMapper, UserCreateEditMapper userCreateEditMapper) {
        this.userRepository = userRepository;
        this.userReadMapper = userReadMapper;
        this.userCreateEditMapper = userCreateEditMapper;
    }

/*
    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), QUser.user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }
*/

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
