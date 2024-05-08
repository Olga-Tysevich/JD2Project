<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.ORDER_DEPARTURE_DATE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.UPDATE_SPARE_PART_ORDER" %>
<%@ page import="it.academy.dto.spare_part_order.SparePartOrderDTO" %>

<%
    SparePartOrderDTO order = (SparePartOrderDTO) request.getAttribute(ORDER);
%>


<div class="included-container">

    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Отправка заказа зч к ремонту No.<%=order.getServiceCenterRepairNumber()%></h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=UPDATE_SPARE_PART_ORDER%>">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=order.getId()%>">

                <div class="f-input">
                    <label class="form-el">Дата отправки</label>
                    <input class="f-form" type="date" name="<%=ORDER_DEPARTURE_DATE%>" value="<%=order.getDepartureDate()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Дата доставки</label>
                    <input class="f-form" type="date" name="<%=ORDER_DELIVERY_DATE%>" value="<%=order.getDeliveryDate()%>">
                </div>

                <%@include file="../forms/errorContainer.jsp"%>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
                <button class="button"
                        onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">Отмена</button>
            </div>

        </div>
    </div>
</div>