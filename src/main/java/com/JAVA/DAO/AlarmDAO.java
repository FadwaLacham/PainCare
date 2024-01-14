package com.JAVA.DAO;

import java.sql.SQLException;
import java.util.List;

import com.JAVA.Beans.Alarm;

public interface AlarmDAO {
    void insertAlarm(Alarm alarm, int userId) throws SQLException;
    List<Alarm> getAlarmsByUserId(int userId) throws SQLException;
    void deleteAlarm(int alarmId) throws SQLException; 

}
