<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.servlets.commands.factory.CommandEnum" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <div class="forms-container lf">

        <%
            CommandEnum formCommand = (CommandEnum) request.getAttribute(COMMAND);
            String formPage = (String) request.getAttribute(FORM_PAGE);
        %>

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=formCommand%>" id="command_id">

                <%pageContext.include(formPage);%>

                <%@include file="errorContainer.jsp"%>

            </form>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>

                    <button class="button"
                            onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">Отмена</button>
                </div>
        </div>

    </div>
</section>
</body>
