package it.academy.servlets;

import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.ActionFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.academy.utils.Constants.*;

@Slf4j
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
        try {
            String page;
            log.info(String.format(CURRENT_CLASS, this.getClass().getSimpleName()));

            ActionFactory actionFactory = new ActionFactory();
            ActionCommand actionCommand = actionFactory.defineCommand(req);
            log.info(String.format(CURRENT_COMMAND, actionCommand));

            page = actionCommand.execute(req);
            log.info(String.format(CURRENT_PAGE, page));

            if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), this));
            req.setAttribute(ERROR, ERROR_MESSAGE);
            resp.sendRedirect(req.getContextPath() + ERROR_PAGE_PATH);
        }

    }
}
