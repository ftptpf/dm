package ru.ftptpf.integration.database.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.database.entity.User;
import ru.ftptpf.database.repository.UserRepository;
import ru.ftptpf.dto.PersonalInfo;
import ru.ftptpf.dto.PersonalInfo2;
import ru.ftptpf.dto.UserFilter;
import ru.ftptpf.integration.IntegrationTestBase;
import ru.ftptpf.integration.annotation.IT;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@IT
//@Sql({
//        "classpath:sql/data.sql"
//})
@Rollback
class UserRepositoryIT extends IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    void checkBatch() {
        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {
        List<PersonalInfo> users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        Assertions.assertThat(users).hasSize(1);
    }

    @Test
    void checkAuditing() {
        User ivan = userRepository.getReferenceById(1L);
        ivan.setBirthDate(ivan.getBirthDate().plusDays(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(null, "%ov%", LocalDate.now());
        List<User> users = userRepository.findAllByFilter(filter);
        Assertions.assertThat(users).hasSize(4);
    }

    @Test
    void checkProjections() {
        List<PersonalInfo2> personalInfoList = userRepository.findAllByCompanyId(1);
        Assertions.assertThat(personalInfoList).hasSize(2);
    }

    @Test
    void checkPageable() {
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by("id"));
        Slice<User> resultSlices = userRepository.findAllBy(pageRequest);
        resultSlices.forEach(user -> System.out.println(user.getCompany().getName()));
        while (resultSlices.hasNext()) {
            resultSlices = userRepository.findAllBy(resultSlices.nextPageable());
            resultSlices.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkSort() {
        Sort sortById = Sort.by("id").descending();
        List<User> users = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortById);
        Assertions.assertThat(users).hasSize(3);
    }

    @Test
    void CheckFirst() {
        Optional<User> firstUser = userRepository.findFirstByOrderByIdDesc();
        Assertions.assertThat(firstUser).isPresent();
        firstUser.ifPresent(user -> Assertions.assertThat(user.getId()).isEqualTo(5L));
    }

    @Test
    void checkUpdate() {
        User ivan = userRepository.getReferenceById(1L);
        Assertions.assertThat(ivan.getRole()).isEqualTo(Role.ADMIN);

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        Assertions.assertThat(resultCount).isEqualTo(2);

        User theSameIvan = userRepository.getReferenceById(1L);
        Assertions.assertThat(theSameIvan.getRole()).isEqualTo(Role.USER);
    }

    @Test
    void checkQueries() {
        List<User> users = userRepository.findAllBy("a", "ov");
        Assertions.assertThat(users).hasSize(3);
    }

}