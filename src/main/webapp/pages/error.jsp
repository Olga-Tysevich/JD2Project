<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.ERROR_MESSAGE" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
        <link rel="stylesheet" href="../css/style.css">
        <title>Стартовая страница</title>
    </head>

    <body>
        <h1><%=ERROR_MESSAGE%></h1>

        <form action="main" method="post">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_START_PAGE%>">
            <input type="hidden" name="page" value="<%=request.getAttribute(PAGE_NUMBER)%>">
            <input class="button add" type="submit" value="Вернуться на главную">
        </form>

    </body>

</html>