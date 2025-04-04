package ru.ftptpf.dao;

import ru.ftptpf.entity.Flight;
import ru.ftptpf.entity.Ticket;
import ru.ftptpf.exeption.DaoException;
import ru.ftptpf.util.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private static final FlightDao INSTANCE = new FlightDao();
    private static final String FIND_BY_ID_SQL = """
            SELECT
            id,
            flight_no,
            departure_date,
            departure_airport_code,
            arrivar_date,
            arrivar_airport_code,
            aircraft_id,
            status
            FROM flight
            WHERE id = ?
            """;

    private FlightDao() {
    }

    @Override
    public List<Flight> findAll() {
        return List.of();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        // В реальных приложениях connection нужно открывать на уровне сервисов и передавать в слой DAO как параметр.
        try (Connection connection = ConnectionPoolManager.get()) {
            return findById(id, connection);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Flight> findById(Long id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Flight flight = null;
            if (resultSet.next()) {
                flight = new Flight(
                        resultSet.getLong("id"),
                        resultSet.getString("flight_no"),
                        resultSet.getTimestamp("departure_date").toLocalDateTime(),
                        resultSet.getString("departure_airport_code"),
                        resultSet.getTimestamp("arrivar_date").toLocalDateTime(),
                        resultSet.getString("arrivar_airport_code"),
                        resultSet.getInt("aircraft_id"),
                        resultSet.getString("status"));
            }
            return Optional.ofNullable(flight);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Ticket save(Flight entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
