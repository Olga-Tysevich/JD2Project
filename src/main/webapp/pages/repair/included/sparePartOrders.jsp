<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.spare_part_order.SparePartOrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.spare_part_order.OrderItemDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
     for (SparePartOrderDTO orderDTO : orders) {
       List<OrderItemDTO> spareParts = orderDTO.getSpareParts();%>

        <div class="order-container">

        <div class="f-input">
            <label class="date-label">Дата заказа: </label>
            <div class="date-container">
                <input class="f-form" type="date" value="<%=orderDTO.getOrderDate()%>" disabled>
            </div>
        </div>

        <div class="f-input">
            <label class="date-label">Дата отправки: </label>
            <div class="date-container">
                <%if (orderDTO.getDepartureDate() != null) {%>
                <input class="f-form" type="date" value="<%=orderDTO.getDepartureDate()%>" disabled/>
                <% } %>
            </div>
        </div>

        <div class="f-input">
            <label class="date-label">Дата доставки: </label>
            <div class="date-container">
                <%if (orderDTO.getDepartureDate() != null) {%>
                <input class="f-form" type="date" value="<%=orderDTO.getDeliveryDate()%>" disabled/>
                <% }%>
            </div>
        </div>

        <table id="order_table">
            <tr>
                <th>Наименование</th>
                <th>Количество</th>
            </tr>

            <%for (OrderItemDTO itemDTO : spareParts) {%>
            <tr id="data_id" class="spare_part_input">
                <td class="order-td">
                    <div><%=itemDTO.getName()%></div>
                </td>
                <td class="order-td">
                    <div><%=itemDTO.getQuantity()%></div>
                </td>
            </tr>
            <% } %>
        </table>

    </div>
<% } %>