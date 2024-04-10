<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.MAX_PAGE" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
    int maxPageNumber = Integer.parseInt(request.getParameter(MAX_PAGE));
    String command = (String) request.getAttribute(COMMAND);
%>

<%if (maxPageNumber != 1) {%>
<div class="footer">
    <div class="button-container">
        <%if (pageNumber != FIRST_PAGE) { %>
        <form action="main" method="post">
            <input type="hidden" name="<%=COMMAND%>>" value="<%=command%>">
            <%int prevPage = pageNumber - 1;%>
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=prevPage%>">
            <input class="button light" type="submit" name="button" value="Предыдущая">
        </form>
        <% } %>

        <p><%=pageNumber%>
            из
            <%=maxPageNumber%>
        </p>


        <%if (pageNumber != maxPageNumber) { %>
        <form action="main" method="post">
            <input type="hidden" name="<%=COMMAND%>>" value="<%=command%>">
            <%int nextPage = pageNumber + 1;%>
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
            <input class="button light" type="submit" name="button" value="Следующая">
        </form>
        <% } %>

    </div>
</div>
<% } %>