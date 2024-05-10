<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.entities.device.Device_" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.*" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="it.academy.utils.enums.RepairCategory" %>
<%@ page import="it.academy.entities.account.ServiceCenter_" %>
<%@ page import="it.academy.entities.repair.Repair_" %>
<%@ page import="it.academy.entities.device.Model_" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.entities.spare_part.SparePartOrder_" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="filterParameters.jsp"%>

<%
    AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
    RoleEnum role = accountDTO.getRole();
    if (!tablePage.isBlank()) {
%>


<form action="main" method="post" id="search">
    <input class="search" type="search" placeholder="Поиск" id="search_input" <%if (filters.containsKey(Repair_.REPAIR_NUMBER)) {%>
           value="<%=filters.get(Repair_.REPAIR_NUMBER)%>"<%}%>/>
    <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
    <div style="display: none" id="search_container">

        <% if (RoleEnum.ADMIN.equals(role)) {%>

        <div class="f-input">
            <input class="search" type="search" name="<%=ServiceCenter_.SERVICE_NAME%>"
                   placeholder="Сервисный центр"
                <%if (filters.containsKey(ServiceCenter_.SERVICE_NAME)) {%>
                   value="<%=filters.get(ServiceCenter_.SERVICE_NAME)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=Repair_.REPAIR_NUMBER%>"
                   placeholder="Номер ремонта"
                <%if (filters.containsKey(Repair_.REPAIR_NUMBER)) {%>
                   value="<%=filters.get(Repair_.REPAIR_NUMBER)%>"<%}%>>
        </div>

        <%}%>
        <div class="search-date-container">
            <label class="form-el">Дата заказа:</label>
            <input class="search" type="date" name="<%=SparePartOrder_.ORDER_DATE%>" <%if (filters.containsKey(SparePartOrder_.ORDER_DATE)) {%>
                   value="<%=filters.get(SparePartOrder_.ORDER_DATE)%>"<%}%>>
        </div>

        <div class="search-date-container">
            <label class="form-el">Дата отправки:</label>
            <input class="search" type="date" name="<%=SparePartOrder_.DEPARTURE_DATE%>"<%if (filters.containsKey(SparePartOrder_.DEPARTURE_DATE)) {%>
                   value="<%=filters.get(SparePartOrder_.DEPARTURE_DATE)%>"<%}%>>
        </div>

        <div class="search-date-container">
            <label class="form-el">Дата доставки:</label>
            <input class="search" type="date" name="<%=SparePartOrder_.DELIVERY_DATE%>" <%if (filters.containsKey(SparePartOrder_.DELIVERY_DATE)) {%>
                   value="<%=filters.get(SparePartOrder_.DELIVERY_DATE)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=Model_.BRAND%>"
                   placeholder="Название бренда" <%if (filters.containsKey(Model_.BRAND)) {%>
                   value="<%=filters.get(Model_.BRAND)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=Model_.TYPE%>"
                   placeholder="Тип устройства" <%if (filters.containsKey(Model_.TYPE)) {%>
                   value="<%=filters.get(Model_.TYPE)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=Model_.NAME%>"
                   placeholder="Название модели" <%if (filters.containsKey(Model_.NAME)) {%>
                   value="<%=filters.get(Device_.MODEL)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=REPAIR_TYPE%>"
                   placeholder="Тип устройства" <%if (filters.containsKey(REPAIR_TYPE)) {%>
                   value="<%=filters.get(REPAIR_TYPE)%>"<%}%>>
        </div>


        <div class="f-input">
            <input class="button light" type="submit" value="Найти" form="search">
        </div>
    </div>

</form>
<script rel="script" src="${pageContext.request.contextPath}/js/SearchBlockBehavior.js"></script>
<% } %>