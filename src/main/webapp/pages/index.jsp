<%@ page import="static it.academy.utils.Constants.OPEN_START_PAGE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Стартовая страница</title>
    </head>

    <body>
<%--    <jsp:include page="login.jsp"/>--%>
    <% response.sendRedirect(OPEN_START_PAGE);%>
    </body>

</html>