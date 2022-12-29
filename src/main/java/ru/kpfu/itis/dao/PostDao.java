package ru.kpfu.itis.dao;


import ru.kpfu.itis.entity.Post;
import ru.kpfu.itis.util.ConnectionProvider;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PostDao {
    private ConnectionProvider connectionProvider;
    public PostDao(){};
    public PostDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Optional<Post> findById(Integer id) throws SQLException {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("SELECT * FROM posts_table WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(
                        Post.builder()
                                .id(resultSet.getInt("id"))
                                .title(resultSet.getString("title"))
                                .text(resultSet.getString("text"))
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

    public List<Post> findAll() throws SQLException {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("SELECT * FROM posts_table");
            ResultSet resultSet = statement.executeQuery();
            List<Post> posts = new LinkedList<>();

            while (resultSet.next()) {
                Post post = Post.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .text(resultSet.getString("text"))
                        .price(resultSet.getInt("price"))
                        .userID(resultSet.getInt("user_id"))
                        .build();

                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public void save(Post post) throws SQLException {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("INSERT INTO posts_table (title, text, price, user_id) " +
                    "VALUES (?, ?, ?, ?)");
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setLong(3, post.getPrice());
            statement.setLong(4, post.getUserID());
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Integer id) {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("DELETE FROM posts_table WHERE id = ?");
            statement.setLong(1,id);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(Post post) {
        try {
            PreparedStatement statement = DriverManager.getConnection("jdbc:postgresql://localhost/1_books", "postgres", "postgres").prepareStatement("UPDATE posts_table SET (title, text, price) = (?, ?, ?) " +
                    "WHERE id = ?");
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getText());
            statement.setInt(3, post.getPrice());
            statement.setInt(4, post.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    }
