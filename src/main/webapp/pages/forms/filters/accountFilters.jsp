<%@ page import="it.academy.entities.device.Model_" %>
<%@ page import="it.academy.entities.account.ServiceCenter_" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.entities.account.Account_" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="filterParameters.jsp"%>
<%
    AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
    RoleEnum role = accountDTO.getRole();
    if (!tablePage.isBlank()) {
%>

<form action="main" method="post" id="search">
    <input class="search" type="search" placeholder="Поиск" id="search_input" <%if (filters.containsKey(Account_.EMAIL)) {%>
           value="<%=filters.get(Account_.EMAIL)%>"<%}%>/>
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
        <%}%>

        <div class="f-input">
            <input class="search" type="search" name="<%=Account_.EMAIL%>"
                   placeholder="email"
                <%if (filters.containsKey(Account_.EMAIL)) {%>
                   value="<%=filters.get(Account_.EMAIL)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=Account_.USER_NAME%>"
                   placeholder="Имя пользователя"
                <%if (filters.containsKey(Account_.USER_NAME)) {%>
                   value="<%=filters.get(Account_.USER_NAME)%>"<%}%>>
        </div>

        <div class="f-input">
            <input class="search" type="search" name="<%=Account_.USER_SURNAME%>"
                   placeholder="Фамилия пользователя"
                <%if (filters.containsKey(Account_.USER_SURNAME)) {%>
                   value="<%=filters.get(Account_.USER_SURNAME)%>"<%}%>>
        </div>
        <div class="f-input">
            <input class="button light" type="submit" value="Найти" form="search">
        </div>
    </div>

</form>
<script rel="script" src="${pageContext.request.contextPath}/js/SearchBlockBehavior.js"></script>
<% } %>