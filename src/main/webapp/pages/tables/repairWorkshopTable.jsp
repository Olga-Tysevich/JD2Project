<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.repair_workshop.RepairWorkshopDTO" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<RepairWorkshopDTO> data = (ListForPage<RepairWorkshopDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<RepairWorkshopDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Сервис</th>
                <th>email</th>
                <th>Телефон</th>
                <th>Адрес</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (RepairWorkshopDTO repairWorkshop : list) {
            %>
            <tr class="t-tr">
                <td class="code"><%=repairWorkshop.getServiceName()%></td>
                <td class="code"><%=repairWorkshop.getEmail()%></td>
                <td class="code"><%=repairWorkshop.getPhone()%></td>
                <td class="code"><%=repairWorkshop.getActualAddress()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=repairWorkshop.getIsActive()%>"
                           <%if (repairWorkshop.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="command" value="show_repair_workshop">
                        <input type="hidden" name="<%=REPAIR_WORKSHOP_ID%>" value="<%=repairWorkshop.getId()%>">
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