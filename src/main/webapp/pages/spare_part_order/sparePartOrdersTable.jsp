<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.spare_part_order.SparePartOrderDTO" %>
<%@ page import="it.academy.dto.TablePage" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.OPEN_FORM" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_UPDATE_REPAIR" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.ORDER" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_ORDER" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>


<section>
    <%@include file="../forms/errorContainer.jsp"%>
    <div class="container t-container">

        <%
            TablePage<SparePartOrderDTO> data = (TablePage<SparePartOrderDTO>) request.getAttribute(TABLE_PAGE);
            List<SparePartOrderDTO> list = data.getList();
        %>


            <table>
                <tr>
                    <th>Номер ремонта</th>
                    <th>Дата заказа</th>
                    <th>Дата отправки</th>
                    <th>Дата доставки</th>
                    <th class="menu">Управление</th>
                </tr>

            <%
                for (SparePartOrderDTO sparePartOrder : list) {
                    long repairId = sparePartOrder.getRepairId();
                    String repairNumber = sparePartOrder.getServiceCenterRepairNumber();
            %>
                <tr class="t-tr">
                    <td><%=repairNumber%></td>
                    <td><%=sparePartOrder.getOrderDate() != null? sparePartOrder.getOrderDate() : ""%></td>
                    <td><%=sparePartOrder.getDepartureDate() != null? sparePartOrder.getDepartureDate() : ""%></td>
                    <td><%=sparePartOrder.getDeliveryDate() != null? sparePartOrder.getDeliveryDate() : ""%></td>
                    <td>
                        <div class="button-table-container">
                            <button class="choose-button order-btn" onclick="location.href='<%=String.format(OPEN_FORM, REPAIR, SHOW_UPDATE_REPAIR, repairId)%>'">
                                Перейти в ремонт</button>

                            <button class="choose-button order-btn"
                                    onclick="location.href='<%=String.format(OPEN_FORM, ORDER, SHOW_SPARE_PART_ORDER, sparePartOrder.getId())%>'">Отправка</button>


                            <button class="choose-button order-btn"
                                    onclick="location.href='<%=String.format(OPEN_FORM, ORDER, DELETE_SPARE_PART_ORDER, sparePartOrder.getId())%>'">Удалить</button>
                        </div>
                    </td>
                </tr>
                <% } %>
            </table>
    </div>
</section>