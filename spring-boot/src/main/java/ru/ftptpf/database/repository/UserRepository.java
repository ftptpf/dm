package ru.ftptpf.database.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.database.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(@Param("firstname") String firstname, @Param("lastname") String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(@Param("firstname") String username);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(@Param("role") Role role, @Param("ids") Long... ids);

    Optional<User> findFirstByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "10"))
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

    //    Slice<User> findAllBy(Pageable pageable);
    //    @EntityGraph("User.company")
    @EntityGraph(attributePaths = {"company", "company.locales"})
    Page<User> findAllBy(Pageable pageable);
}
