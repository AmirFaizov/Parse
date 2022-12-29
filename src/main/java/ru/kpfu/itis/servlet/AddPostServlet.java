package ru.kpfu.itis.servlet;


import ru.kpfu.itis.entity.Post;
import ru.kpfu.itis.entity.Toy;
import ru.kpfu.itis.service.PostService;
import ru.kpfu.itis.service.ToyService;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@MultipartConfig
@WebServlet("/create")
public class AddPostServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Toy toy = Toy.builder()
                .toyName(request.getParameter("title"))
                .discription(request.getParameter("text"))
                .price(Integer.valueOf(request.getParameter("price")))
                .userID(UserService.getAuthUser().getId())
                .build();

        try {
            toyService.save(toy);
        } catch (SQLException | DbException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/toys/toy/list");
    }
}
