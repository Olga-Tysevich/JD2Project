
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="lf-button-container">
    <form action="order" method="get" id="order">
        <input type="hidden" name="<%=COMMAND%>" value="<%=GET_NEW_SPARE_PART_ORDER%>">
        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
        <input type="hidden" name="<%=MODEL_ID%>" value="<%=repairDTO.getModelId()%>">
        <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairNumber()%>">
        <input type="hidden" name="<%=REPAIR_FORM%>" value="<%=repairForm%>">
        <%--                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">--%>
        <%--                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">--%>
        <input class="choose-button lf-button" type="submit" value="Заказать запчасти" form="order"/>
    </form>

    <form action="repair" method="get" id="completed">
        <input type="hidden" name="<%=COMMAND%>" value="show_repair_type_list">
        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
        <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairNumber()%>">
        <%--                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">--%>
        <%--                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">--%>
        <input class="choose-button lf-button" type="submit" value="Сообщить о выполнении" form="completed"/>
    </form>
</div>