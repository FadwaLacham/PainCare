package com.JAVA.DAO;

import java.util.List;

import com.JAVA.Beans.User;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);
    boolean checkLogin(String identifier, String password);
    User getUserByEmail(String email);
}
