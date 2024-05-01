<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
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

        <%=request.getSession().getAttribute(LAST_PAGE)%>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=formCommand%>" id="command_id">

                <%pageContext.include(formPage);%>


                <div class="f-input">
                    <%
                        String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                    %>
                    <p class="error" id="error" style="display: none"><%=errorMessage%></p>
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
                    <button class="button"
                            onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">Отмена</button>
                </div>

            </form>
        </div>


    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>

</body>
