package ru.kpfu.itis;

import ru.kpfu.itis.dao.ToyDao;
import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.Optional;

@WebListener
public class InitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private static HttpSession session;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            sce.getServletContext().setAttribute("userDao", new UserDao(connectionProvider));
            sce.getServletContext().setAttribute("userService", new UserService());
            sce.getServletContext().setAttribute("toyDao", new ToyDao(connectionProvider));
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        session = se.getSession();
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    }


        public static Optional<User> getAuthUser() {
        User user = (User) session.getAttribute("authUser");
        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

}