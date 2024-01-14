package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.JAVA.Beans.User;

public class UserDaoImpl implements UserDAO {
    private DAOFactory daoFactory;

    // Constructor
    public UserDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setPicture(resultSet.getBytes("picture"));
        user.setEmail(resultSet.getString("email")); // Added line
        return user;
    }

    private static PreparedStatement initRequestPrepare(Connection connection, String sql, Object... objects)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    @Override
    public User getUserById(int userId) throws DAOException {
        final String SQL_SELECT_BY_ID = "SELECT id, name, password, picture, email FROM users WHERE id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_SELECT_BY_ID, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        final String SQL_SELECT_ALL = "SELECT id, name, password, picture, email FROM users";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = map(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, resultSet);
        }

        return userList;
    }

    @Override
    public void addUser(User user) throws DAOException {
        final String SQL_INSERT = "INSERT INTO users (name, password, picture, email) VALUES (?, ?, ?, ?)";

        // Validation des paramètres avant la création du PreparedStatement
        if (user == null || user.getName() == null || user.getPassword() == null || user.getEmail() == null) {
            throw new DAOException("Les paramètres de l'utilisateur ne peuvent pas être null");
        }

        try (Connection connection = daoFactory.getConnection()) {
            // Désactiver l'auto-commit
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = initRequestPrepare(connection, SQL_INSERT, user.getName(), user.getPassword(),
                     user.getPicture(), user.getEmail())) {

                preparedStatement.executeUpdate();

                // Utilisation de transactions
                connection.commit();
            } catch (SQLException e) {
                // En cas d'erreur, effectuer un rollback pour annuler les modifications
                connection.rollback();
                throw new DAOException("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'obtention de la connexion : " + e.getMessage(), e);
        }
    }



    @Override
    public void updateUser(User user) throws DAOException {
        final String SQL_UPDATE = "UPDATE users SET name = ?, password = ?, picture = ?, email = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_UPDATE, user.getName(), user.getPassword(),
                    user.getPicture(), user.getEmail(), user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public void deleteUser(int userId) throws DAOException {
        final String SQL_DELETE = "DELETE FROM users WHERE id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_DELETE, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, null);
        }
    }

    @Override
    public boolean checkLogin(String identifier, String password) throws DAOException {
        final String SQL_CHECK_LOGIN = "SELECT * FROM users WHERE (name = ? OR email = ?) AND password = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_CHECK_LOGIN, identifier, identifier, password);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Returns true if there is a matching user
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, resultSet);
        }
    }

    @Override
    public User getUserByEmail(String email) throws DAOException {
        final String SQL_SELECT_BY_EMAIL = "SELECT id, name, password, picture, email FROM users WHERE email = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initRequestPrepare(connection, SQL_SELECT_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, resultSet);
        }

        return user;
    }

    private void closeResources(PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            // Handle exception or log it
        }
    }
}
