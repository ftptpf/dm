package ru.ftptpf.dao;

import ru.ftptpf.dto.TicketFilter;
import ru.ftptpf.entity.Flight;
import ru.ftptpf.entity.Ticket;
import ru.ftptpf.exeption.DaoException;
import ru.ftptpf.util.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class TicketDao implements Dao<Long, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();
    private static final String DELETE_SQL = "DELETE FROM ticket WHERE id = ?";
    private static final String SAVE_SQL = """
            INSERT INTO ticket (passenger_no, passenger_name, flight_id, siat_no, cost)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String UPDATE_SQL = """
            UPDATE ticket
            SET passenger_no = ?, passenger_name = ?, flight_id = ?, siat_no = ?, cost = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
             SELECT
             ticket.id,
             passenger_no,
             passenger_name,
             flight_id,
             siat_no,
             cost,
             f.flight_no,
             f.departure_date,
             f.departure_airport_code,
             f.arrivar_date,
             f.arrivar_airport_code,
             f.aircraft_id,
             f.status
             FROM ticket
             JOIN flight f ON ticket.flight_id = f.id
             """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE ticket.id = ?
            """;
    private final FlightDao flightDao = FlightDao.getInstance();

    private TicketDao() {
    }

    public List<Ticket> findAll(TicketFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.seatNo() != null) {
            whereSql.add("siat_no LIKE ?");
            parameters.add("%" + filter.seatNo() + "%");
        }
        if (filter.passengerName() != null) {
            whereSql.add("passenger_name = ?");
            parameters.add(filter.passengerName());
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());

        String where = whereSql.stream()
                .collect(Collectors.joining(" AND ", "  WHERE ", " LIMIT ? OFFSET ?"));
        String sql = FIND_ALL_SQL + where;

        try (Connection connection = ConnectionPoolManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            List<Ticket> tickets = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    public List<Ticket> findAll() {
        try (Connection connection = ConnectionPoolManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Ticket> tickets = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Ticket> findById(Long id) {
        try (Connection connection = ConnectionPoolManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Ticket ticket = null;
            if (resultSet.next()) {
                ticket = buildTicket(resultSet);
            }
            return Optional.ofNullable(ticket);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void update(Ticket ticket) {
        try (Connection connection = ConnectionPoolManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlight().id());
            preparedStatement.setString(4, ticket.getSeatNo());
            preparedStatement.setBigDecimal(5, ticket.getCost());
            preparedStatement.setLong(6, ticket.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Ticket save(Ticket ticket) {
        try (Connection connection = ConnectionPoolManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, ticket.getPassengerNo());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlight().id());
            preparedStatement.setString(4, ticket.getSeatNo());
            preparedStatement.setBigDecimal(5, ticket.getCost());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ticket.setId(generatedKeys.getLong("id"));
            }
            return ticket;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id) {
        try (Connection connection = ConnectionPoolManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight(
                resultSet.getLong("flight_id"),
                resultSet.getString("flight_no"),
                resultSet.getTimestamp("departure_date").toLocalDateTime(),
                resultSet.getString("departure_airport_code"),
                resultSet.getTimestamp("arrivar_date").toLocalDateTime(),
                resultSet.getString("arrivar_airport_code"),
                resultSet.getInt("aircraft_id"),
                resultSet.getString("status")
        );
        return new Ticket(
                resultSet.getLong("id"),
                resultSet.getString("passenger_no"),
                resultSet.getString("passenger_name"),
//                flight,
                flightDao.findById(resultSet.getLong("flight_id")).orElse(null),
                resultSet.getString("siat_no"),
                resultSet.getBigDecimal("cost"));
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }
}
