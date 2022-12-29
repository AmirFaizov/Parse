package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.UserDao;
import ru.kpfu.itis.entity.User;
import ru.kpfu.itis.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserDao userDao;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = (UserDao) getServletContext().getAttribute("userDao");
        userService = (UserService) getServletContext().getAttribute("userService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/security/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserService();
        List<User> allUsers;
        try {
            allUsers = userService.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean alreadyExists = allUsers.stream()
                .anyMatch(user -> user.getUsername().equals(username));

        if (!alreadyExists) {
            User user = new User(username,password,"admin");
            try {
                userService.save(user);
                resp.sendRedirect("toy/list");
                userService.auth(user, req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.sendRedirect("signin");
        }
    }
}