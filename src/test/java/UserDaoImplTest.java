import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOFactory;
import com.JAVA.DAO.UserDaoImpl;

public class UserDaoImplTest {

    private DAOFactory mockDAOFactory;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private UserDaoImpl userDao;

    @Before
    public void setUp() throws SQLException {
        // Mock the DAOFactory and database-related objects
        mockDAOFactory = mock(DAOFactory.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Simulate the behavior of the getConnection method
        when(mockDAOFactory.getConnection()).thenReturn(mockConnection);

        // Initialize the UserDaoImpl with the mocked DAOFactory
        userDao = new UserDaoImpl(mockDAOFactory);
    }

    @Test
    public void testGetUserById() throws SQLException {
        // Mock the behavior for the ResultSet
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simulate that a user was found
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("John Doe");
        when(mockResultSet.getString("password")).thenReturn("password123");
        when(mockResultSet.getString("email")).thenReturn("john@example.com");

        // Call the method
        User user = userDao.getUserById(1);

        // Verify that the method executed the query
        verify(mockPreparedStatement, times(1)).executeQuery();

        // Assert that the returned user has the expected values
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        // Mock the behavior for the ResultSet
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false); // Simulate one user found
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("John Doe");
        when(mockResultSet.getString("password")).thenReturn("password123");
        when(mockResultSet.getString("email")).thenReturn("john@example.com");

        // Call the method
        List<User> users = userDao.getAllUsers();

        // Verify that the method executed the query
        verify(mockPreparedStatement, times(1)).executeQuery();

        // Assert that the returned list contains one user with the expected values
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("password123", users.get(0).getPassword());
        assertEquals("john@example.com", users.get(0).getEmail());
    }

    @Test
    public void testAddUser() throws SQLException {
        // Create a user object
        User user = new User();
        user.setName("John Doe");
        user.setPassword("password123");
        user.setEmail("john@example.com");

        // Mock the behavior for PreparedStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Call the method
        userDao.addUser(user);

        // Verify that the executeUpdate() was called
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateUser() throws SQLException {
        // Create a user object
        User user = new User();
        user.setId(1);
        user.setName("Updated Name");
        user.setPassword("newpassword");
        user.setEmail("updated@example.com");

        // Mock the behavior for PreparedStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Call the method
        userDao.updateUser(user);

        // Verify that the executeUpdate() was called
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws SQLException {
        // Mock the behavior for PreparedStatement
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Call the method
        userDao.deleteUser(1);

        // Verify that the executeUpdate() was called
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testCheckLogin() throws SQLException {
        // Mock the behavior for the ResultSet
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simulate a valid login

        // Call the method
        boolean result = userDao.checkLogin("John Doe", "password123");

        // Verify that the method executed the query
        verify(mockPreparedStatement, times(1)).executeQuery();

        // Assert the result
        assertTrue(result);
    }

    @Test
    public void testGetUserByEmail() throws SQLException {
        // Mock the behavior for the ResultSet
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simulate a user was found
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("John Doe");
        when(mockResultSet.getString("password")).thenReturn("password123");
        when(mockResultSet.getString("email")).thenReturn("john@example.com");

        // Call the method
        User user = userDao.getUserByEmail("john@example.com");

        // Verify that the method executed the query
        verify(mockPreparedStatement, times(1)).executeQuery();

        // Assert that the returned user has the expected values
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
    }
}