package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.ToyDao;
import ru.kpfu.itis.entity.Toy;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/toy/detail")
public class ToyDetailServlet extends HttpServlet {
    private ToyDao toyDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        toyDao = (ToyDao) getServletContext().getAttribute("toyDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            if(id == null){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Bad request. No id has been provided.");
            }
            Toy toy = toyDao.getDetail(Integer.parseInt(id));
            if(toy == null){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Toy not found");
            }
            req.setAttribute("toy", toy);
        } catch (DbException e) {
            throw new ServletException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/toys/_detail.jsp").forward(req,resp);
    }
}
