package it.academy.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.JSPConstant.LOGIN_PAGE_PATH;

@WebFilter(urlPatterns = {"/main"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();

        if ((session != null) && (session.getAttribute(ACCOUNT) != null)) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(contextPath + LOGIN_PAGE_PATH);
        }
    }

    @Override
    public void destroy() {

    }
}
