package ru.kpfu.itis.dao;

import ru.kpfu.itis.entity.Post;
import ru.kpfu.itis.entity.Toy;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ToyDao {
    private ConnectionProvider connectionProvider;

    public ToyDao(){};
    public ToyDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }
    public int getCount() throws DbException {
        try {
            Statement st = this.connectionProvider.getCon().createStatement();
            ResultSet result = st.executeQuery("SELECT COUNT(id) AS element FROM toyss");
            result.next();
            return result.getInt("element");
        } catch (SQLException e) {
            throw new DbException("Can't get count of toys in DB", e);
        }}

    public void save(Toy toy) throws DbException {
        try {
            PreparedStatement preparedStatement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books","postgres","postgres").prepareStatement("insert into toyss (name, price, discription, user_id) values (?,?,?,?)");
            preparedStatement.setString(1, toy.getToyName());
            preparedStatement.setInt(2, toy.getPrice());
            preparedStatement.setString(3, toy.getDiscription());
            preparedStatement.setInt(4, toy.getUserID());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException("failed to save the toy in DB",e);
        }}

    public void update(Toy toy) {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("UPDATE toyss SET (name, discription, price) = (?, ?, ?) " +
                    "WHERE id = ?");
            statement.setString(1, toy.getToyName());
            statement.setString(2, toy.getDiscription());
            statement.setInt(3, toy.getPrice());
            statement.setInt(4, toy.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public void delete(Integer id) {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("DELETE FROM toyss WHERE id = ?");
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public List<Toy> findAll() throws SQLException {
        try {
            PreparedStatement preparedStatement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books","postgres","postgres").prepareStatement("select * from toyss");
            ResultSet result = preparedStatement.executeQuery();

            List<Toy> list = new LinkedList<>();

            while (result.next()) {
                Toy toy = new Toy(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("discription"),
                        result.getInt("price"),
                        result.getInt("user_id")
                );
                list.add(toy);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Toy> getPage() throws DbException {
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("SELECT * FROM toyss");
            ResultSet result = st.executeQuery();
            List<Toy> toys = new ArrayList<>();
            while(result.next()){
                Toy toy = new Toy(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("discription"),
                        result.getInt("price"),
                        result.getInt("user_id")
                );
                toys.add(toy);
            }
            return toys;
        } catch (SQLException e) {
            throw new DbException("Can't get toy list from db", e);
        }
    }

    public Toy getDetail(int id) throws DbException{
        try {
            PreparedStatement st = this.connectionProvider.getCon().prepareStatement("SELECT * FROM toyss WHERE id=?");
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            boolean hasOne = result.next();
            if(hasOne){
                return new Toy(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("discription"),
                        result.getInt("price"),
                       result.getInt("user_id")

                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get toy list from db", e);
        }
    }
    public Optional<Toy> findById(Integer id) throws SQLException {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("SELECT * FROM toyss WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(
                        Toy.builder()
                                .id(resultSet.getInt("id"))
                                .toyName(resultSet.getString("name"))
                                .discription(resultSet.getString("discription"))
                                .price(resultSet.getInt("price"))
                                .userID(resultSet.getInt("user_id"))
                                .build()
                );
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
