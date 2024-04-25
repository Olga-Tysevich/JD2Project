<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_PAGE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <title>Ошибка</title>
    </head>

    <body>
        <h1><%=ERROR_MESSAGE%></h1>

        <form action="main" method="post">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_PAGE%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=request.getAttribute(PAGE)%>">
            <input class="button add" type="submit" value="Вернуться на главную">
        </form>

    </body>

</html>