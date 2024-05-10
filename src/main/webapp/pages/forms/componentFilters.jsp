<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.fiterForSearch.EntityFilter" %>
<%@ page import="it.academy.utils.fiterForSearch.FilterManager" %>
<%@ page import="java.util.Map" %>
<%@ page import="static it.academy.utils.constants.Constants.USER_INPUT" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="static it.academy.utils.constants.Constants.PAGE" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_MAIN_PAGE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.PARAMETER_PATTERN" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.OPEN_TABLE_PAGE_BY_FILTER" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    Map<String, String> filters = request.getAttribute(USER_INPUT) != null ?
            (Map<String, String>) request.getAttribute(USER_INPUT)
            : new HashMap<>();
    String tablePage = StringUtils.defaultIfBlank((String) request.getAttribute(PAGE), StringUtils.EMPTY);
    String command = StringUtils.defaultIfBlank((String) request.getAttribute(COMMAND), SHOW_MAIN_PAGE.name());
    int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
    String userInput = filters.entrySet().stream()
            .map(f -> String.format(PARAMETER_PATTERN, f.getKey(), f.getValue()))
            .collect(Collectors.joining());
    String currentPage = String.format(OPEN_TABLE_PAGE_BY_FILTER, command, tablePage, pageNumber) + userInput;
    request.getSession().removeAttribute(LAST_PAGE);
    request.getSession().setAttribute(LAST_PAGE, currentPage);
    if (!tablePage.isBlank()) {
%>

<form action="main" method="post" id="search">
    <input class="search" type="search" placeholder="Поиск" id="search_input"/>
    <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
    <div style="display: none" id="search_container">

        <div class="f-input">
            <input class="search" type="search" name="<%=OBJECT_NAME%>"
                   placeholder="Название"
                <%if (filters.containsKey(OBJECT_NAME)) {%>
                   value="<%=filters.get(OBJECT_NAME)%>"<%}%>>
        </div>


        <div class="f-input">
            <input class="button light" type="submit" value="Найти" form="search">
        </div>
    </div>

</form>
<script rel="script" src="${pageContext.request.contextPath}/js/SearchBlockBehavior.js"></script>
<% } %>