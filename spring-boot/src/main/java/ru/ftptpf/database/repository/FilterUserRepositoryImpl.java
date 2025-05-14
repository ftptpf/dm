package ru.ftptpf.database.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.ftptpf.database.entity.Role;
import ru.ftptpf.database.entity.User;
import ru.ftptpf.dto.PersonalInfo;
import ru.ftptpf.dto.UserFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_ALL_BY_COMPANY_ID_AND_ROLE = """
            SELECT firstname, lastname, birth_date
            FROM users
            WHERE company_id = ? AND role = ?
            """;
    private static final String UPDATE_COMPANY_AND_ROLE = """
            UPDATE users
            SET company_id = ?, role = ?
            WHERE id = ?
            """;
    private static final String UPDATE_COMPANY_AND_ROLE_NAMED = """
            UPDATE users
            SET company_id = :companyId, role = :role
            WHERE id = :id
            """;

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.firstname() != null) {
            predicates.add(criteriaBuilder.like(userRoot.get("firstname"), filter.firstname()));
        }
        if (filter.lastname() != null) {
            predicates.add(criteriaBuilder.like(userRoot.get("lastname"), filter.lastname()));
        }
        if (filter.birthDate() != null) {
            predicates.add(criteriaBuilder.lessThan(userRoot.get("birthDate"), filter.birthDate()));
        }

        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_ALL_BY_COMPANY_ID_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate()),
                companyId, role.name());
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        List<Object[]> args = users.stream()
                .map(user -> new Object[]{user.getCompany().getId(), user.getRole().name(), user.getId()})
                .toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, args);
    }

    @Override
    public void updateCompanyAndRoleNamed(List<User> users) {
        MapSqlParameterSource[] args = users.stream().map(user -> Map.of(
                        "companyId", user.getCompany().getId(),
                        "role", user.getRole().name(),
                        "id", user.getId()))
                .map(MapSqlParameterSource::new)
                .toArray(MapSqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE_NAMED, args);
    }
}
