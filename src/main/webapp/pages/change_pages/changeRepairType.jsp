<%@ page import="static it.academy.utils.Constants.OPEN_START_PAGE" %>
<%@ page import="static it.academy.utils.Constants.REPAIR_TYPE_FORM_BODY" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <div class="forms-container lf">

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="repair_type">
                <input type="hidden" name="command" value="change_repair_type">
                <jsp:include page="<%=REPAIR_TYPE_FORM_BODY%>"/>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="repair_type"/>
                <input class="button" type="submit" value="Удалить" form="repair_type"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>
        </div>

    </div>
</section>

</body>
