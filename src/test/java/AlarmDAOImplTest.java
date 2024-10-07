import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.JAVA.Beans.Alarm;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.AlarmDAOImpl;

public class AlarmDAOImplTest {

    private DAOFactory mockDAOFactory;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private AlarmDAOImpl alarmDAO;

    @Before
    public void setUp() throws SQLException {
        // Mock the DAOFactory and database-related objects
        mockDAOFactory = mock(DAOFactory.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Simulate the behavior of the getConnection method
        when(mockDAOFactory.getConnection()).thenReturn(mockConnection);

        // Mock the prepared statement to be returned when prepareStatement is called
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);

        // Mock executeUpdate and getGeneratedKeys behavior
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);  // Simulate successful insert
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);  // Mock ResultSet for generated keys
        when(mockResultSet.next()).thenReturn(true);  // Simulate that a key is returned
        when(mockResultSet.getInt(1)).thenReturn(1);  // Return the generated ID

        // Initialize the AlarmDAOImpl with the mocked DAOFactory
        alarmDAO = new AlarmDAOImpl(mockDAOFactory);
    }

    @Test
    public void testInsertAlarm() throws SQLException {
        // Define the alarm object and set up the mock behavior for inserting an alarm
        Alarm alarm = new Alarm();
        alarm.setTitle("Test Alarm");
        alarm.setTime(Time.valueOf("12:00:00"));
        alarm.setRepeatDays(Set.of("Monday", "Tuesday"));  // Ensure this is non-null

        // Execute the DAO method
        alarmDAO.insertAlarm(alarm, 1);

        // Verify that the insert operation was called
        verify(mockPreparedStatement, times(1)).executeUpdate();

        // Verify that the alarm ID was set correctly from the generated keys
        assertEquals(1, alarm.getAlarmId());  // Check if the ID is set correctly
    }
}
