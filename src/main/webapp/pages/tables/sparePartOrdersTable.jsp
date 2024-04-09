<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.dto.spare_parts.SparePartOrderDTO" %>
<%@ page import="it.academy.dto.spare_parts.SparePartDTO" %>
<%@ page import="java.util.Map" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<SparePartOrderDTO> data = (ListForPage<SparePartOrderDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<SparePartOrderDTO> list = data.getList();
        %>


        <div class="radio-container">
            <form class="status-form" action="main" method="post" id="find_spare_part_orders">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="command" value="show_spare_part_orders_table">
            </form>
        </div>


            <table>
                <tr>
                    <th>Номер ремонта</th>
                    <th>Дата заказа</th>
                    <th>Дата отправки</th>
                    <th>Дата доставки</th>
                    <th>Наименование запчасти</th>
                    <th class="menu">Управление</th>
                </tr>

            <% for (SparePartOrderDTO sparePartOrder : list) {
                Map<SparePartDTO, Integer> spareParts = sparePartOrder.getSpareParts();
                for (SparePartDTO sparePart : spareParts.keySet()) {
            %>
                <tr class="t-tr">
                    <td class="code"><%=sparePartOrder.getRepairWorkshopNumber()%></td>
                    <td class="level"><%=sparePartOrder.getOrderDate() != null? sparePartOrder.getOrderDate() : ""%></td>
                    <td class="name"><%=sparePartOrder.getDepartureDate() != null? sparePartOrder.getDepartureDate() : ""%></td>
                    <td class="name"><%=sparePartOrder.getDeliveryDate() != null? sparePartOrder.getDeliveryDate() : ""%></td>
                    <td class="name"><%=sparePart.getName()%></td>
                    <td>
                        <div class="button-table-container">
                            <form action="repair" method="post">
                                <input type="hidden" name="command" value="show_confirmed_repair">
                                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                                <input type="hidden" name="<%=REPAIR_ID%>" value="<%=sparePartOrder.getRepairId()%>">
                                <input class="choose-button order-btn" type="submit" value="Перейти в ремонт">
                            </form>
                        </div>
                    </td>
                </tr>
                <% } }%>
            </table>

        <%if (data.getMaxPageNumber() != 1) {%>
        <div class="footer">
        <div class="button-container">
            <%if (pageNumber != FIRST_PAGE) { %>
            <form action="main" method="post">
                <input type="hidden" name="command" value="show_spare_part_orders_table">
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
                <input type="hidden" name="command" value="show_spare_part_orders_table">
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