<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%
        int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        String tablePage = (String) request.getAttribute(PAGE);
        RepairTypeDTO repairType = (RepairTypeDTO) request.getAttribute(REPAIR_TYPE);
    %>

    <form action="main" method="post">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="change_repair_type">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_REPAIR_TYPE%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairType.getId()%>">

                <div class="f-input">
                    <label class="form-el">Активен</label>
                    <label >Активный: </label>
                    <label >да: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (repairType.getIsActive()) {%>checked<%}%> />
                    <label >нет: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!repairType.getIsActive()) {%>checked<%}%>/>
                </div>


                <div class="f-input">
                    <label class="form-el">Название</label>
                    <input class="f-form" required type="text" name="<%=OBJECT_NAME%>" value="<%=repairType.getName()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Код ремнота</label>
                    <input class="f-form" required type="text" name="<%=REPAIR_TYPE_CODE%>" value="<%=repairType.getName()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Уровень ремнота</label>
                    <input class="f-form" required type="text" name="<%=REPAIR_TYPE_LEVEL%>" value="<%=repairType.getLevel()%>">
                </div>
            </form>

            <div class="f-input">
                <%
                    String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                %>
                <p class="error" id="error" style="display: none"><%=errorMessage%></p>
            </div>


            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="change_repair_type"/>
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>
        </div>

        <form action="main" method="post" id="cancel">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_PAGE%>">
            <input type="hidden" name="<%=DISPLAY_TABLE_COMMAND%>" value="<%=SHOW_REPAIR_TYPE_TABLE%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
        </form>

    </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
</body>
