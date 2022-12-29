package ru.kpfu.itis.service;

import ru.kpfu.itis.dao.ToyDao;
import ru.kpfu.itis.entity.Post;
import ru.kpfu.itis.entity.Toy;
import ru.kpfu.itis.util.DbException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ToyService {
    private static final ToyDao toyDao = new ToyDao();

    public void save(Toy toy) throws SQLException, DbException {
        toyDao.save(toy);
    }
    public List<Toy> getAllUsers() throws SQLException {
        return toyDao.findAll();
    }

    public void deletePost(Integer id) {
        toyDao.delete(id);
    }

    public void updatePost(Toy toy) {
        toyDao.update(toy);
    }

    public Toy findToy(Integer id) throws SQLException {
        Optional<Toy> toy = toyDao.findById(id);
        if (toy.isPresent()) {
            return toy.get();
        } else {
            throw new IllegalArgumentException("No such post");
        }
    }
}
