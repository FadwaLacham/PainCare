package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.JAVA.Beans.Alarm;

public class AlarmDAOImpl implements AlarmDAO {

    private final DAOFactory daoFactory;

    public AlarmDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void insertAlarm(Alarm alarm, int userId) throws SQLException {
        String sql = "INSERT INTO alarm (title, time, repeatDays, userId) VALUES (?, ?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, alarm.getTitle());
            preparedStatement.setTime(2, alarm.getTime());
            preparedStatement.setString(3, String.join(",", alarm.getRepeatDays()));
            preparedStatement.setInt(4, userId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating alarm failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alarm.setAlarmId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating alarm failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error adding alarm", e);
        }
    }

    @Override
    public List<Alarm> getAlarmsByUserId(int userId) throws SQLException {
        List<Alarm> alarms = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM alarm WHERE userId = ?")) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Alarm alarm = extractAlarmFromResultSet(resultSet);
                    alarms.add(alarm);
                }
            }

        } catch (SQLException e) {
            throw new SQLException("Error getting alarms", e);
        }

        return alarms;
    }

    @Override
    public void deleteAlarm(int alarmId) throws SQLException {
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM alarm WHERE alarmId = ?")) {

            preparedStatement.setInt(1, alarmId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting alarm failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new SQLException("Error deleting alarm", e);
        }
    }

    // Helper method to extract an Alarm from a ResultSet
    private Alarm extractAlarmFromResultSet(ResultSet resultSet) throws SQLException {
        Alarm alarm = new Alarm();
        alarm.setAlarmId(resultSet.getInt("alarmId"));
        alarm.setTitle(resultSet.getString("title"));
        alarm.setTime(resultSet.getTime("time"));

        // Conversion de la chaîne séparée par des virgules en ensemble
        String repeatDaysString = resultSet.getString("repeatDays");
        String[] repeatDaysArray = repeatDaysString.split(",");
        Set<String> repeatDays = new HashSet<>(Arrays.asList(repeatDaysArray));
        alarm.setRepeatDays(repeatDays);

        return alarm;
    }

    // Ajoutez d'autres méthodes en fonction de vos besoins
}
