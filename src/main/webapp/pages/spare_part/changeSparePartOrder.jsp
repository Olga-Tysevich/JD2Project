<%@ page import="static it.academy.utils.constants.Constants.ORDERS" %>
<%@ page import="java.util.Map" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.resp.SparePartOrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.resp.SparePartDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_SPARE_PART_ORDER" %>
<%@ page import="it.academy.dto.resp.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
     AccountDTO accountDTO = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
     RoleEnum role = accountDTO.getRole();
     List<SparePartOrderDTO> orders = (List<SparePartOrderDTO>) request.getAttribute(ORDERS);
     int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
     String pageForDisplay = (String) request.getAttribute(PAGE);
     if (orders != null && !orders.isEmpty()) {
         for (SparePartOrderDTO orderDTO : orders) {
        Map<SparePartDTO, Integer> spareParts = orderDTO.getSpareParts();%>

<form class="lr-form" action="repair" method="post" id="repair">
    <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_SPARE_PART_ORDER%>">
    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    <input type="hidden" name="<%=PAGE%>" value="<%=pageForDisplay%>">
    <input type="hidden" name="<%=OBJECT_ID%>" value="<%=orderDTO.getId()%>">
    <input type="hidden" name="<%=ORDER_REPAIR_ID%>" value="<%=request.getAttribute(ORDER_REPAIR_ID)%>">
    <div class="order-container">

        <div class="f-input change-order">
                <input id="change_id" class="choose-button btn-table" type="submit" value="Изменить"/>
        </div>

        <div class="f-input">
            <label class="date-label" for="orderStartDate" >Дата заказа: </label>
            <div class="date-container">
                <input class="f-form" type="hidden" name="<%=ORDER_DATE%>"  value="<%=orderDTO.getOrderDate()%>"/>
                <input class="f-form" id="orderStartDate" type="date" name="<%=ORDER_DATE%>"  value="<%=orderDTO.getOrderDate()%>" disabled/>
            </div>
        </div>

        <div class="f-input">
            <label class="date-label" for="orderDepartureDate">Дата отправки: </label>
            <div class="date-container">
                <%if (orderDTO.getDepartureDate() != null) {%>
                <input class="f-form departure-date-input" id="orderDepartureDate" type="date" name="<%=DEPARTURE_DATE%>"
                       value="<%=orderDTO.getDepartureDate()%>"
                       <% if(orderDTO.getDepartureDate() != null || !RoleEnum.ADMIN.equals(role)) {%>disabled<%}%>
                />
                <% } else {%>
                <input class="f-form departure-date-input" id="orderDepartureDate" type="date" name="<%=DEPARTURE_DATE%>"  value=""/>
                <%}%>
            </div>
        </div>

        <div class="f-input">
            <label class="date-label" for="orderDeliveryDate">Дата доставки: </label>
            <div class="date-container">
                <%if (orderDTO.getDepartureDate() != null) {%>
                <input class="f-form delivery-date-input" id="orderDeliveryDate" type="date" name="<%=DELIVERY_DATE%>"
                       value="<%=orderDTO.getDeliveryDate()%>"
                       <% if(orderDTO.getDepartureDate() != null || !RoleEnum.ADMIN.equals(role)) {%>disabled<%}%>
                />
                <% } else {%>
                <input class="f-form delivery-date-input" id="orderDeliveryDate" type="date" name="<%=DELIVERY_DATE%>"  value=""/>
                <%}%>
            </div>
        </div>

        <table id="order_table">
            <tr>
                <th>Наименование</th>
                <th>Количество</th>
            </tr>

            <%     for (SparePartDTO sparePartDTO : spareParts.keySet()) {
            %>

            <tr id="data_id" class="spare_part_input">
                <td class="order-td">
                    <input type="hidden" name="<%=SPARE_PART_ID%>" value="<%=sparePartDTO.getId()%>" disabled>
                    <input type="text" name="<%=OBJECT_NAME%>" value="<%=sparePartDTO.getName()%>" disabled>
                </td>
                <td class="order-td">
                    <input class="quantity " type="number" name="<%=SPARE_PART_QUANTITY%>"
                           value="<%=spareParts.get(sparePartDTO)%>" disabled>
                </td>
            </tr>
            <% } %>
        </table>

        <% if (orderDTO.getDepartureDate() == null || !RoleEnum.ADMIN.equals(role)) { %>
        <div class="f-input">
            <input id="remove_id" class="choose-button btn-table remove" type="button" value="Удалить"/>
        </div>
        <% } %>
    </div>
</form>
    <% }
    }%>


<script rel="script" src="${pageContext.request.contextPath}/js/ChangeOrderForm.js"></script>

