package ru.kpfu.itis.servlet;

import lombok.SneakyThrows;
import ru.kpfu.itis.entity.Toy;
import ru.kpfu.itis.service.ToyService;

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
@WebServlet("/toy/update")
public class UpdateToyServlet extends HttpServlet {
    private final ToyService toyService = new ToyService();

    private Integer postID;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer postID = Integer.parseInt(request.getParameter("postID"));
        this.postID = postID;
            Toy toy = toyService.findToy(postID);


        request.setAttribute("toy", toy);
        request.getRequestDispatcher("/WEB-INF/view/update_post.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Toy toy = toyService.findToy(postID);

        toy.setToyName(request.getParameter("title"));
        toy.setDiscription(request.getParameter("text"));
        toy.setPrice(Integer.valueOf(request.getParameter("price")));


        toyService.updatePost(toy);
        response.sendRedirect("/toys/toy/list");
    }
}

