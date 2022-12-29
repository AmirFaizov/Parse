package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.ToyDao;
import ru.kpfu.itis.entity.Toy;
import ru.kpfu.itis.service.ToyService;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class ToyCreateServlet extends HttpServlet {
    private ToyDao toyDao;
    private ToyService toyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        toyDao = (ToyDao) getServletContext().getAttribute("toyDao");
        toyService = (ToyService) getServletContext().getAttribute("toyService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/toys/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toyName = req.getParameter("toyName");
        Integer price = Integer.valueOf(req.getParameter("price"));
        String discription = req.getParameter("discription");
        ToyService toyService = new ToyService();
        try {
            Toy toy = new Toy(price, toyName, discription);
            toyService.save(toy);
        } catch (SQLException | DbException e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/toys/create.jsp").forward(req, resp);
        resp.sendRedirect("/");

    }
}

