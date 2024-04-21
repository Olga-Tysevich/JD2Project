<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_DEVICE_TYPE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_DEVICE_TYPE_TABLE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
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
        DeviceTypeDTO deviceType = (DeviceTypeDTO) request.getAttribute(DEVICE_TYPE);
    %>

    <form action="main" method="post">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="change_device_type_id">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_DEVICE_TYPE%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=deviceType.getId()%>">

                <div class="f-input">
                    <label class="form-el">Активен</label>
                    <label >Активный: </label>
                    <label >да: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (deviceType.getIsActive()) {%>checked<%}%> />
                    <label >нет: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!deviceType.getIsActive()) {%>checked<%}%>/>
                </div>


                <div class="f-input">
                    <label class="form-el">Тип девайса</label>
                    <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="<%=deviceType.getName()%>">
                </div>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="change_device_type_id"/>
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>
        </div>

        <form action="main" method="post" id="cancel">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_PAGE%>">
            <input type="hidden" name="<%=DISPLAY_TABLE_COMMAND%>" value="<%=SHOW_DEVICE_TYPE_TABLE%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
        </form>


    </div>
</section>

</body>
