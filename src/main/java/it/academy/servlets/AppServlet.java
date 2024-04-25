package it.academy.servlets;

import it.academy.dto.account.AccountDTO;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.factory.ActionFactory;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.*;
import static it.academy.utils.constants.LoggerConstants.*;

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

            ActionFactory actionFactory = new ActionFactory();
            ActionCommand actionCommand = actionFactory.defineCommand(req);
            log.info(CURRENT_COMMAND, actionCommand);

            page = actionCommand.execute(req, resp);
            log.info(CURRENT_PAGE_PATTERN, page);

            if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            log.error(ERROR_PATTERN, e.getClass(), e.getMessage());
            AccountDTO accountDTO = (AccountDTO) req.getSession().getAttribute(ACCOUNT);
            RoleEnum role = accountDTO.getRole();
            String pagePath = RoleEnum.ADMIN.equals(role)? ADMIN_MAIN_PAGE_PATH : USER_MAIN_PAGE_PATH;
            req.setAttribute(PAGE, pagePath);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ERROR_PAGE_PATH);
            dispatcher.forward(req, resp);
        }

    }
}
