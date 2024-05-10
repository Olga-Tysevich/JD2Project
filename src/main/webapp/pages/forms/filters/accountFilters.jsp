<%@ page import="it.academy.entities.device.Model_" %>
<%@ page import="it.academy.entities.account.ServiceCenter_" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="filterParameters.jsp"%>
<%
    if (!tablePage.isBlank()) {
%>

<form action="main" method="post" id="search">
    <input class="search" type="search" placeholder="Поиск" id="search_input" <%if (filters.containsKey(ServiceCenter_.SERVICE_NAME)) {%>
           value="<%=filters.get(ServiceCenter_.SERVICE_NAME)%>"<%}%>/>
    <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
    <div style="display: none" id="search_container">


        <div class="f-input">
            <input class="search" type="search" name="<%=ServiceCenter_.SERVICE_NAME%>"
                   placeholder="Название"
                <%if (filters.containsKey(ServiceCenter_.SERVICE_NAME)) {%>
                   value="<%=filters.get(ServiceCenter_.SERVICE_NAME)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=EMAIL%>"
                   placeholder="email"
                <%if (filters.containsKey(EMAIL)) {%>
                   value="<%=filters.get(EMAIL)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=SERVICE_CENTER_ACTUAL_ADDRESS%>"
                   placeholder="Фактический адрес"
                <%if (filters.containsKey(SERVICE_CENTER_ACTUAL_ADDRESS)) {%>
                   value="<%=filters.get(SERVICE_CENTER_ACTUAL_ADDRESS)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=SERVICE_CENTER_LEGAL_ADDRESS%>"
                   placeholder="Юридический адрес"
                <%if (filters.containsKey(SERVICE_CENTER_LEGAL_ADDRESS)) {%>
                   value="<%=filters.get(SERVICE_CENTER_LEGAL_ADDRESS)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=SERVICE_CENTER_PHONE%>"
                   placeholder="Телефон"
                <%if (filters.containsKey(SERVICE_CENTER_PHONE)) {%>
                   value="<%=filters.get(SERVICE_CENTER_PHONE)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="button light" type="submit" value="Найти" form="search">
        </div>
    </div>

</form>
<script rel="script" src="${pageContext.request.contextPath}/js/SearchBlockBehavior.js"></script>
<% } %>