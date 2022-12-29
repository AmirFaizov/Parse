package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.PostDao;
import ru.kpfu.itis.entity.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class PostService {
    private static final PostDao postDao = new PostDao();

    public void savePost(Post post) throws SQLException {
        postDao.save(post);
    }

    public void deletePost(Integer id) {
        postDao.delete(id);
    }

    public void updatePost(Post post) {
        postDao.update(post);
    }

    public Post findPost(Integer id) throws SQLException {
        Optional<Post> post = postDao.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new IllegalArgumentException("No such post");
        }
    }


}
