<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.servlets.commands.factory.CommandEnum" %>
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
            CommandEnum tableCommand = (CommandEnum) request.getAttribute(DISPLAY_TABLE_COMMAND);
            String tablePage = (String) request.getAttribute(PAGE);
            String formPage = (String) request.getAttribute(FORM_PAGE);
            String lastInput = (String) request.getAttribute(USER_INPUT);
            String lastFilter = (String) request.getAttribute(FILTER);
            int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        %>

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=formCommand%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                <%pageContext.include(formPage);%>


                <div class="f-input">
                    <%
                        String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                    %>
                    <p class="error" id="error" style="display: none"><%=errorMessage%></p>
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
                    <input class="button" type="submit" value="Отмена" form="cancel"/>
                </div>

            </form>
        </div>

        <form action="main" method="post" id="cancel">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_PAGE%>">
            <input type="hidden" name="<%=DISPLAY_TABLE_COMMAND%>" value="<%=tableCommand%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
        </form>

    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>

</body>
