<%@ page import="it.academy.dto.spare_parts.SparePartOrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.utils.Constants.ORDERS" %>
<%@ page import="it.academy.dto.spare_parts.SparePartDTO" %>
<%@ page import="java.util.Map" %>
<%@ page import="static it.academy.utils.Constants.ORDER_ID" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
     List<SparePartOrderDTO> orders = (List<SparePartOrderDTO>) request.getAttribute(ORDERS);
     if (orders != null && !orders.isEmpty()) {
         for (SparePartOrderDTO orderDTO : orders) {
        Map<SparePartDTO, Integer> spareParts = orderDTO.getSpareParts();%>

<form class="lr-form" action="main" method="post" id="repair">
    <input type="hidden" name="command" value="change_order">
    <input type="hidden" name="<%=ORDER_ID%>" value="<%=orderDTO.getId()%>" disabled>
    <%     for (SparePartDTO sparePartDTO: spareParts.keySet()) {
    %>
    <div class="order-container">

        <div class="f-input change-order">
                <input id="change_id" class="choose-button btn-table" type="submit" value="Изменить" />
        </div>

        <div class="f-input">
            <label class="date-label" for="orderStartDate" >Дата заказа: </label>
            <div class="date-container">
                <input class="f-form" id="orderStartDate" type="date" name="<%=ORDER_DATE%>"  value="<%=orderDTO.getOrderDate()%>" disabled/>
            </div>
        </div>

        <div class="f-input">
            <label class="date-label" for="orderDepartureDate">Дата отправки: </label>
            <div class="date-container">
                <input class="f-form" id="orderDepartureDate" type="date" name="<%=DEPARTURE_DATE%>"  value="<%=orderDTO.getDepartureDate()%>"/>
            </div>
        </div>

        <div class="f-input">
            <label class="date-label" for="orderDeliveryDate">Дата доставки: </label>
            <div class="date-container">
                <input class="f-form" id="orderDeliveryDate" type="date" name="<%=DELIVERY_DATE%>"  value="<%=orderDTO.getDeliveryDate()%>"/>
            </div>
        </div>

        <table id="order_table">
            <tr>
                <th>Наименование</th>
                <th>Количество</th>
            </tr>
            <tr id="data_id" class="spare_part_input">
                <td class="order-td">
                    <input type="hidden" name="<%=SPARE_PART_ID%>" value="<%=sparePartDTO.getId()%>" disabled>
                    <input type="text" name="<%=SPARE_PART_NAME%>" value="<%=sparePartDTO.getName()%>" disabled>
                </td>
                <td class="order-td">
                    <input class="quantity " type="number" name="<%=SPARE_PART_QUANTITY%>"
                           value="<%=spareParts.get(sparePartDTO)%>" disabled>
                </td>
            </tr>
        </table>

        <div class="f-input change-order">
            <input id="remove_id" class="choose-button btn-table" type="submit" value="Удалить" />
        </div>
    </div>
</form>
    <% } }
    }%>

<script rel="script" src="${pageContext.request.contextPath}/js/ChangeOrderForm.js"></script>

