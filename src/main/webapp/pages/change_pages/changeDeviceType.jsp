<%@ page import="static it.academy.utils.Constants.OPEN_START_PAGE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%
        int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        DeviceTypeDTO deviceType = (DeviceTypeDTO) request.getAttribute(DEVICE_TYPE);
    %>

    <form action="main" method="post">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="change_device_type_id">
                <input type="hidden" name="command" value="change_device_type">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=DEVICE_TYPE_ID%>" value="<%=deviceType.getId()%>">

                <div class="f-input">
                    <label class="form-el">Активно</label>
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=deviceType.getIsActive()%>"
                           <%if (deviceType.getIsActive()) {%>checked<%}%>>
                </div>


                <div class="f-input">
                    <label class="form-el">Тип девайса</label>
                    <input class="f-form" type="text" name="<%=DEVICE_TYPE_NAME%>" value="<%=deviceType.getName()%>">
                </div>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="change_device_type_id"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>
        </div>



    </div>
</section>

</body>
