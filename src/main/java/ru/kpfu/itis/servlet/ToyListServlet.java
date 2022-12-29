package ru.kpfu.itis.servlet;

import ru.kpfu.itis.dao.ToyDao;
import ru.kpfu.itis.util.DbException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/toy/list")
public class ToyListServlet extends HttpServlet {

    private ToyDao toyDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        toyDao = (ToyDao) getServletContext().getAttribute("toyDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("toyCount",toyDao.getCount());
            req.setAttribute("toys",toyDao.getPage());
        } catch (DbException e) {
            throw new ServletException(e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/toys/list.jsp").forward(req,resp);
    }
}

