<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="static it.academy.utils.constants.Constants.LIST_FOR_PAGE" %>
<%@ page import="static it.academy.utils.constants.Constants.FIRST_PAGE" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    ListForPage list = (ListForPage) request.getAttribute(LIST_FOR_PAGE);
    int pageNumber = list.getPageNumber() == null ? FIRST_PAGE : list.getPageNumber();
    int maxPageNumber = list.getMaxPageNumber() == null ? FIRST_PAGE : list.getMaxPageNumber();
    String tablePage = list.getPage();
    String command = list.getCommand();
    String filter = request.getSession().getAttribute(FILTER) == null? "" : (String) request.getSession().getAttribute(FILTER);
    String input = request.getSession().getAttribute(USER_INPUT) == null? "" : (String) request.getSession().getAttribute(USER_INPUT);
%>

<div class="table-container">
    <% if (tablePage != null) {
        pageContext.include(tablePage);
        if (maxPageNumber > 1) {%>
    <div class="footer">
        <div class="button-container">
            <%if (pageNumber != FIRST_PAGE) { %>
            <form action="main" method="post">
                <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=FILTER%>" value="<%=filter%>">
                <input type="hidden" name="<%=USER_INPUT%>" value="<%=input%>">
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
                <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=FILTER%>" value="<%=filter%>">
                <input type="hidden" name="<%=USER_INPUT%>" value="<%=input%>">
                <%int nextPage = pageNumber + 1;%>
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
                <input class="button light" type="submit" name="button" value="Следующая">
            </form>
            <% }
            } %>

        </div>
    </div>
    <% } %>

</div>