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
            RepairStatus lastStatus = (RepairStatus) request.getAttribute(REPAIR_STATUS);
        %>

        <div class="radio-container">
            <form class="status-form" action="main" method="post" id="find_repairs">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="command" value="show_repair_table">
                <div class="radio-container"><div>Статус ремонта: </div>
                <%for (RepairStatus status: statuses) { %>
                    <div>
                    <input name="<%=REPAIR_STATUS%>" type="radio" value="<%=status%>"> <%=status.getDescription()%>
                    </div>
                <% } %>
                    <div>
                    <input class="choose-button" type="submit" value="Выбрать">
                    </div>
                </div>
            </form>
        </div>

            <table>
                <tr>
                    <th>Сервисный центр</th>
                    <th class="date">Дата приема</th>
                    <th>No. заказа</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th>Заявленный дефект</th>
                    <th>Статус ремонта</th>
                    <th class="menu">Управление</th>
                </tr>

            <% for (RepairDTO repair : list) { %>

                <tr>
                    <td class="date"><%=repair.getRepairWorkshop().getServiceName()%></td>
                    <td class="date"><%=repair.getStartDate()%></td>
                    <td class="number"><%=repair.getRepairWorkshopRepairNumber()%></td>
                    <td class="number"><%=repair.getModelDescription()%></td>
                    <td><%=repair.getDevice().getSerialNumber()%></td>
                    <td class="defect"><%=repair.getDefectDescription()%></td>
                    <td><%=repair.getStatus().getDescription()%></td>
                    <td>
                        <div class="button-table-container">
                            <form action="repair" method="post">
                                <input type="hidden" name="command" value="show_confirmed_repair">
                                <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repair.getId()%>">
                                <input class="choose-button btn-table" type="submit" value="Изменить">
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
                <input type="hidden" name="command" value="show_repair_table">
                <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=lastStatus%>">
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
                <input type="hidden" name="command" value="show_repair_table">
                <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=lastStatus%>">
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