package ru.kpfu.itis.servlet;

import ru.kpfu.itis.service.ToyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/toy/delete")
public class DeleteToyServlete extends HttpServlet {

    private final ToyService postService = new ToyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer postID = Integer.parseInt(request.getParameter("postID"));

        postService.deletePost(postID);

        response.sendRedirect("/toys/toy/list");
    }
}


