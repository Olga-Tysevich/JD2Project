<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.AccountDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<AccountDTO> data = (ListForPage<AccountDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<AccountDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>email</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Сервисный центр</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (AccountDTO account : list) {
                String repairWorkshopName = account.getRepairWorkshop() != null? account.getRepairWorkshop().getServiceName() : "";
            %>
            <tr class="t-tr">
                <td class="code"><%=account.getEmail()%></td>
                <td class="code"><%=account.getUserName()%></td>
                <td class="code"><%=account.getUserSurname()%></td>
                <td class="code"><%=repairWorkshopName%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=account.getIsActive()%>"
                           <%if (account.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="command" value="show_repair_workshop">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=account.getId()%>">
                        <input type="hidden" name="<%=ACCOUNT_REPAIR_WORKSHOP%>" value="<%=account.getRepairWorkshop()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

<%--        <jsp:include page="/pages/changePageButtons.jsp"/>--%>

        <%if (data.getMaxPageNumber() != 1) {%>
        <div class="footer">
            <div class="button-container">
                <%if (pageNumber != FIRST_PAGE) { %>
                <form action="main" method="post">
                    <input type="hidden" name="command" value="show_repair_workshop_table">
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
                    <input type="hidden" name="command" value="show_repair_workshop_table">
                    <%int nextPage = pageNumber + 1;%>
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
                    <input class="button light" type="submit" name="button" value="Следующая">
                </form>
                <% } %>

            </div>
        </div>
        <% } %>

    </div>
</section>