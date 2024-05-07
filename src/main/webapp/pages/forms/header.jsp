<%@ page import="static it.academy.utils.constants.Constants.ACCOUNT" %>
<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div class=" container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            String userEmail = accountDTO.getEmail();
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
            String tablePage = StringUtils.defaultIfBlank((String) request.getAttribute(PAGE), StringUtils.EMPTY);
            String command = StringUtils.defaultIfBlank((String) request.getAttribute(COMMAND), SHOW_MAIN_PAGE.name());
            String lastInput = StringUtils.defaultIfBlank((String) request.getAttribute(USER_INPUT), StringUtils.EMPTY);
            String lastFilter = StringUtils.defaultIfBlank((String) request.getAttribute(FILTER), StringUtils.EMPTY);
            String currentPage = String.format(OPEN_TABLE_PAGE_BY_FILTER, command, tablePage, pageNumber, lastFilter, lastInput);
            request.getSession().setAttribute(LAST_PAGE, currentPage);
            String form = (String) request.getAttribute(FORM_PAGE);
        %>

        <div class="header-container">
            <%if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {%>
            <div class="header-el">
                <p><%=accountDTO.getServiceCenterName()%></p>
            </div>
            <%}%>
            <div class="header-el">
                <p><%=userEmail%></p>
            </div>
        </div>
        <%@include file="filters.jsp"%>
    </div>
