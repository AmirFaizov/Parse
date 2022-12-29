package ru.kpfu.itis.dao;

import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDao {
    private ConnectionProvider connectionProvider;

    public UserDao(){}
    public UserDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public User getUserByUsernameAndPassword(String username, String password) throws DbException {
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("SELECT * FROM userу WHERE email = ? AND password = ?");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet result = st.executeQuery();
            boolean hasOne = result.next();
            if(hasOne) {
                return new User(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get user from db.", e);
        }
    }

    public List<User> findAll() throws SQLException {
        try {
            PreparedStatement preparedStatement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books","postgres","postgres").prepareStatement("select * from userу");
            ResultSet result = preparedStatement.executeQuery();

            List<User> list = new LinkedList<>();

            while (result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role")
                );
                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void save(User user) throws SQLException{
        try {
            PreparedStatement preparedStatement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books","postgres","postgres").prepareStatement("insert into userу (email, password) values (?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}