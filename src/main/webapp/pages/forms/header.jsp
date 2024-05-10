<%@ page import="static it.academy.utils.constants.Constants.ACCOUNT" %>
<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_MAIN_PAGE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>

<div class=" container">

    <%
        AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
        String userEmail = accountDTO.getEmail();
        String form = (String) request.getAttribute(FORM_PAGE);
        String filterPage = (String) request.getAttribute(FILTER_PAGE);
        String tablePage = StringUtils.defaultIfBlank((String) request.getAttribute(PAGE), StringUtils.EMPTY);
        String command = StringUtils.defaultIfBlank((String) request.getAttribute(COMMAND), SHOW_MAIN_PAGE.name());
        int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
        if (request.getSession().getAttribute(LAST_PAGE) != null) {
            String currentPage = String.format(OPEN_TABLE_PAGE_BY_FILTER, command, tablePage, pageNumber);
            request.getSession().setAttribute(LAST_PAGE, currentPage);
        }
    %>

    <div class="header-container">
        <%if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {%>
        <div class="header-el">
            <p><%=accountDTO.getServiceCenterName()%>
            </p>
        </div>
        <%}%>

        <div class="header-el">
            <p><%=userEmail%>
            </p>
        </div>
    </div>

    <%
        if (!StringUtils.isBlank(filterPage)) {
            pageContext.include(filterPage);
        }
    %>
</div>
