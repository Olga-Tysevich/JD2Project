<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.entities.repair.components.RepairStatus" %>
<section>
    <div class=" container">

        <%
            ListForPage<RepairDTO> data = (ListForPage<RepairDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<RepairDTO> list = data.getList();
            List<RepairStatus> statuses = List.of(RepairStatus.values());
        %>

        <div class="radio-container">
            <form action="main">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="command" value="show_request_repair_table">
                <div class="radio-container"><b>Статус ремонта: </b>
                <%for (RepairStatus status: statuses) { %>
                    <input name="<%=REPAIR_STATUS%>" type="radio" value="<%=status%>"> <%=status.getDescription()%>
                <% } %>
                    <input class="choose-button" type="submit" value="Выбрать">
                </div>
            </form>
        </div>

            <table>
                <tr>
                    <th>Дата приема</th>
                    <th>No. заказа</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th>Заявленный дефект</th>
<%--                    <th>Выявленный дефект</th>--%>
                    <th>Статус ремонта</th>
                    <th class="menu">Управление</th>
                </tr>

            <% for (RepairDTO repair : list) { %>

                <tr>
                    <td><%=repair.getStartDate()%></td>
                    <td class="number"><%=repair.getRepairWorkshopRepairNumber()%></td>
                    <td class="number"><%=repair.getModelDescription()%></td>
                    <td><%=repair.getDevice().getSerialNumber()%></td>
                    <td><%=repair.getDefectDescription()%></td>
<%--                    <td><%=repair.getIdentifiedDefectDescription()%></td>--%>
                    <td><%=repair.getStatus().getDescription()%></td>
                    <td>
                        <div class="button-container">
                            <form action="list" method="post">
                                <input type="hidden" name="command" value="change_student">
                                <input type="hidden" name="id" value="<%=repair.getId()%>">
                                 <input type="hidden" name="page" value="<%=pageNumber%>">
                                <input class="button" type="submit" value="Изменить">
                            </form>
                            <form action="list" method="post">
                                <input type="hidden" name="command" value="delete_student">
                                <input type="hidden" name="id" value="<%=repair.getId()%>">
                                <input type="hidden" name="page" value="<%=pageNumber%>">
                                <input class="button" type="submit" value="Удалить">
                            </form>
                        </div>
                    </td>
                </tr>
                <% } %>
            </table>

        <%if (data.getMaxPageNumber() != 1) {%>
        <div class="footer">
        <div class="button-container">
            <%if (pageNumber != FIRST_PAGE) { %>
            <form action="main" method="post">
                <input type="hidden" name="command" value="show_request_repair_table">
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
                <input type="hidden" name="command" value="show_request_repair_table">
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