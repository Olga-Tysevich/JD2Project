package it.academy.servlets;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.managers.ActionFactory;
import it.academy.utils.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.academy.utils.Constants.*;

public class AppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;

        ActionFactory actionFactory = new ActionFactory();
        ActionCommand actionCommand = actionFactory.defineCommand(req);

        page = actionCommand.execute(req, resp);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
//            req.getSession().setAttribute(PAGE_NUMBER, FIRST_PAGE);
            resp.sendRedirect(req.getContextPath() + page);
        }
    }
}
