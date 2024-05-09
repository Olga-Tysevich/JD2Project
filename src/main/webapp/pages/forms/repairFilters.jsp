<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="static it.academy.utils.constants.Constants.USER_INPUT" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_MAIN_PAGE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="it.academy.entities.device.Device_" %>
<%@ page import="java.util.HashMap" %>
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
    String currentPage = String.format(OPEN_TABLE_PAGE_BY_FILTER2, command, tablePage) + userInput;
    request.getSession().removeAttribute(LAST_PAGE);
    request.getSession().setAttribute(LAST_PAGE, currentPage);
    if (!tablePage.isBlank()) {
%>


<form action="main" method="post" id="search">
    <input class="search" type="search" placeholder="Поиск"  id="search_input"/>
    <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
    <div style="display: none" id="search_container">
        <div class="f-input">
            <input class="search" type="search" name="<%=Device_.SERIAL_NUMBER%>"
                   placeholder="<%=DEVICE_SERIAL_NUMBER_DESCRIPTION%>"
                <%if (filters.containsKey(Device_.SERIAL_NUMBER)) {%> value="<%=filters.get(Device_.SERIAL_NUMBER)%>"<%}%>>
        </div>

        <div class="f-input">
        <input class="search" type="search" name="<%=BUYER_SURNAME%>"
               placeholder="<%=BUYER_NAME_DESCRIPTION%>" <%if (filters.containsKey(BUYER_SURNAME)) {%>
               value="<%=filters.get(BUYER_SURNAME)%>"<%}%>>
        </div>

        <div class="f-input">
        <input class="search" type="search" name="<%=SALESMAN_NAME%>"
               placeholder="<%=SALESMAN_NAME_DESCRIPTION%>" <%if (filters.containsKey(SALESMAN_NAME)) {%>
               value="<%=filters.get(SALESMAN_NAME)%>"<%}%>>
        </div>

        <div class="f-input">
        <input class="search" type="search" name="<%=Device_.DATE_OF_SALE%>"
               placeholder="<%=DATE_OF_SALE_DESCRIPTION%>" <%if (filters.containsKey(Device_.MODEL)) {%>
               value="<%=filters.get(Device_.DATE_OF_SALE)%>"<%}%>>
        </div>

        <div class="f-input">
        <input class="search" type="search" name="<%=Device_.MODEL%>"
               placeholder="<%=MODEL_NAME_DESCRIPTION%>" <%if (filters.containsKey(Device_.MODEL)) {%>
               value="<%=filters.get(Device_.MODEL)%>"<%}%>>
        </div>
        <div class="f-input">
        <input class="button light" type="submit" value="Найти" form="search">
        </div>
    </div>

</form>
<script rel="script" src="${pageContext.request.contextPath}/js/SearchBlockBehavior.js"></script>
<% } %>