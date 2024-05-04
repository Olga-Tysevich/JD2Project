<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.fiterForSearch.EntityFilter" %>
<%@ page import="it.academy.utils.fiterForSearch.FilterManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    if (!tablePage.isBlank()) {
    List<EntityFilter> filters = FilterManager.getFiltersForPage(tablePage);
%>
<form action="main" method="post" id="search">
    <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
    <input class="search" type="search" name="<%=USER_INPUT%>" placeholder="Поиск" <%if (!lastInput.isBlank()) {%>value="<%=lastInput%>"<%}%>>
    <select class="filter" name="<%=FILTER%>" size="1">
        <% if (filters != null) {
            for (EntityFilter filter: filters) { %>
        <option value="<%=filter.getFieldName()%>" <%if (filter.getFieldName().equals(lastFilter)) {%>selected<%}%>>
            <%=filter.getDescription()%></option>
        <% }
        } %>
    </select>
    <input class="button light" type="submit" value="Найти" form="search">
</form>
<% } %>