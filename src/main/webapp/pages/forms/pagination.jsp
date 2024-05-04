<%@ page import="static it.academy.utils.constants.Constants.FIRST_PAGE" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.TablePage2" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <%
        TablePage2 pageData = request.getAttribute(TABLE_PAGE) != null?
                (TablePage2) request.getAttribute(TABLE_PAGE) : new TablePage2();
        Long numberOfEntries = pageData.getNumberOfEntries();
        int maxPageNumber = numberOfEntries != null? (int) Math.ceil(((double) numberOfEntries) / LIST_SIZE) : FIRST_PAGE;
        if (maxPageNumber > 1) {%>
    <div class="footer">
        <div class="button-container">
            <%if (pageNumber != FIRST_PAGE) { %>
            <form action="main" method="get">
                <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=FILTER%>" value="<%=lastFilter%>">
                <input type="hidden" name="<%=USER_INPUT%>" value="<%=lastInput%>">
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
            <form action="main" method="get">
                <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=FILTER%>" value="<%=lastFilter%>">
                <input type="hidden" name="<%=USER_INPUT%>" value="<%=lastInput%>">
                <%int nextPage = pageNumber + 1;%>
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
                <input class="button light" type="submit" name="button" value="Следующая">
            </form>
            <% }
            }%>

        </div>
    </div>