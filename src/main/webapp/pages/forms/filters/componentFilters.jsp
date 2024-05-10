<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="filterParameters.jsp"%>
<%
    if (!tablePage.isBlank()) {
%>

<form action="main" method="post" id="search">
    <input class="search" type="search" placeholder="Поиск" id="search_input" <%if (filters.containsKey(OBJECT_NAME)) {%>
        value="<%=filters.get(OBJECT_NAME)%>"<%}%>/>
    <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
    <div style="display: none" id="search_container">


        <div class="f-input">
            <input class="search" type="search" name="<%=OBJECT_NAME%>"
                   placeholder="Название"
                <%if (filters.containsKey(OBJECT_NAME)) {%>
                   value="<%=filters.get(OBJECT_NAME)%>"<%}%>/>
        </div>


        <div class="f-input">
            <input class="button light" type="submit" value="Найти" form="search">
        </div>
    </div>

</form>
<script rel="script" src="${pageContext.request.contextPath}/js/SearchBlockBehavior.js"></script>
<% } %>