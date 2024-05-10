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

        <div class="f-input">
            <select class="search" name="<%=REPAIR_STATUS%>" size="1">
                <%for (RepairStatus status : RepairStatus.values()) { %>
                <option value="<%=status%>" <%if (filters.containsKey(status.name())) {%>selected<%}%>>
                    <%=status.getDescription()%>
                </option>
                <% }%>
                <option value="<%=REPAIR_STATUS%>" <%if (filters.containsValue(REPAIR_STATUS)) {%>selected<%}%>>
                    Все
                </option>
            </select>
        </div>

        <div class="f-input">
            <select class="search" name="<%=REPAIR_CATEGORY%>" size="1">
                <%for (RepairCategory category : RepairCategory.values()) { %>
                <option value="<%=category%>" <%if (filters.containsKey(category.name())) {%>selected<%}%>>
                    <%=category.getDescription()%>
                </option>
                <% }%>
                <option value="<%=REPAIR_CATEGORY%>" <%if (filters.containsValue(REPAIR_CATEGORY)) {%>selected<%}%>>
                    Все
                </option>
            </select>
        </div>

        <% if (RoleEnum.ADMIN.equals(role)) {%>

        <div class="f-input">
            <input class="search" type="search" name="<%=ServiceCenter_.SERVICE_NAME%>"
                   placeholder="Сервисный центр"
                <%if (filters.containsKey(ServiceCenter_.SERVICE_NAME)) {%>
                   value="<%=filters.get(ServiceCenter_.SERVICE_NAME)%>"<%}%>>
        </div>

        <%}%>

        <div class="f-input">
            <input class="search" type="search" name="<%=Repair_.REPAIR_NUMBER%>"
                   placeholder="Номер ремонта"
                <%if (filters.containsKey(Repair_.REPAIR_NUMBER)) {%>
                   value="<%=filters.get(Repair_.REPAIR_NUMBER)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=Device_.SERIAL_NUMBER%>"
                   placeholder="Серийный номер"
                <%if (filters.containsKey(Device_.SERIAL_NUMBER)) {%>
                   value="<%=filters.get(Device_.SERIAL_NUMBER)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=BUYER_SURNAME_FILTER%>"
                   placeholder="Фамилия покупателя" <%if (filters.containsKey(BUYER_SURNAME_FILTER)) {%>
                   value="<%=filters.get(BUYER_SURNAME_FILTER)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=SALESMAN_NAME_FILTER%>"
                   placeholder="Название продавца" <%if (filters.containsKey(SALESMAN_NAME_FILTER)) {%>
                   value="<%=filters.get(SALESMAN_NAME_FILTER)%>"<%}%>>
        </div>


        <div class="search-date-container">
            <label class="form-el">Дата продажи:</label>
            <input class="search" type="date" name="<%=Device_.DATE_OF_SALE%>"
                   placeholder="Дата продажи" <%if (filters.containsKey(Device_.DATE_OF_SALE)) {%>
                   value="<%=filters.get(Device_.DATE_OF_SALE)%>"<%}%>>
        </div>

        <div class="search-date-container">
            <label class="form-el">Дата начала:</label>
            <input class="search" type="date" name="<%=Repair_.START_DATE%>"
                   placeholder="Дата начала:" <%if (filters.containsKey(Repair_.START_DATE)) {%>
                   value="<%=filters.get(Repair_.START_DATE)%>"<%}%>>
        </div>

        <div class="search-date-container">
            <label class="form-el">Дата окончания:</label>
            <input class="search" type="date" name="<%=Repair_.END_DATE%>"
                   placeholder="Дата окончания:" <%if (filters.containsKey(Repair_.END_DATE)) {%>
                   value="<%=filters.get(Repair_.END_DATE)%>"<%}%>>
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