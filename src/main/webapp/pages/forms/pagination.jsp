<%@ page import="static it.academy.utils.constants.Constants.FIRST_PAGE" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.TablePage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <%
        TablePage pageData = request.getAttribute(TABLE_PAGE) != null?
                (TablePage) request.getAttribute(TABLE_PAGE) : new TablePage();
        Long numberOfEntries = pageData.getNumberOfEntries();
        int maxPageNumber = numberOfEntries != null? (int) Math.ceil(((double) numberOfEntries) / LIST_SIZE) : FIRST_PAGE;
        String lastPage = (String) request.getSession().getAttribute(LAST_PAGE);

        if (maxPageNumber > 1) {%>
    <div class="footer">
        <div class="button-container">

            <%if (pageNumber != FIRST_PAGE) {
                int prevPage = pageNumber - 1;
            %>


            <button class="button light"
                    onclick="location.href='<%=lastPage + String.format(PARAMETER_PATTERN, PAGE_NUMBER, prevPage)%>'">Предыдущая</button>
            <% } %>

            <p><%=pageNumber%>
                из
                <%=maxPageNumber%>
            </p>


            <%if (pageNumber != maxPageNumber) {
                int nextPage = pageNumber + 1;
            %>

            <button class="button light"
                    onclick="location.href='<%=lastPage + String.format(PARAMETER_PATTERN, PAGE_NUMBER, nextPage)%>'">Следующая</button>
            <% }
            }%>

        </div>
    </div>