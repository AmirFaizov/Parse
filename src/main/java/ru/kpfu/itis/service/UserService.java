package ru.kpfu.itis.service;

import ru.kpfu.itis.InitListener;
import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
        private static final UserDao userDao = new UserDao();

        public void auth(User user, HttpServletRequest req, HttpServletResponse resp) {
            req.getSession().setAttribute("user", user);
        }


    public static User getAuthUser() {
        Optional<User> user = InitListener.getAuthUser();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("Auth error");
        }
    }

        public boolean isNonAnonymous(HttpServletRequest req, HttpServletResponse resp) {
            return req.getSession().getAttribute("user") != null;
        }

    public List<User> getAllUsers() throws SQLException {
        return userDao.findAll();
    }

        public User getUser(HttpServletRequest req, HttpServletResponse res) {
            return (User) req.getSession().getAttribute("user");
        }
        public void save(User user) throws SQLException {
             userDao.save(user);
    }
    }
