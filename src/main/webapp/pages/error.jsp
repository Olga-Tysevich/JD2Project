<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.OPEN_PAGE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../css/style.css">
        <title>Ошибка</title>
    </head>

    <body>
        <h1><%=request.getAttribute(ERROR)%></h1>

        <form action="main" method="post">
            <input type="hidden" name="<%=COMMAND%>" value="<%=OPEN_PAGE%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=MAIN_PAGE_PATH%>">
            <input class="button add" type="submit" value="Вернуться на главную">
        </form>

    </body>

</html>