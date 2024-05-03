<%@ page import="static it.academy.utils.constants.Constants.ERROR" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="f-input">
    <%
        String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
    %>
    <p class="error" id="error" style="display: none"><%=errorMessage%></p>
</div>
<script rel="script" src="${pageContext.request.contextPath}/js/ErrorBlock.js"></script>