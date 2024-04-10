<%@ page import="static it.academy.utils.Constants.OPEN_START_PAGE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="java.util.List" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%
        int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        ModelDTO model = (ModelDTO) request.getAttribute(MODEL);
        List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute(BRANDS);
        List<DeviceTypeDTO> deviceTypes = (List<DeviceTypeDTO>) request.getAttribute(DEVICE_TYPES);
    %>

    <form action="main" method="post">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="change_model_id">
                <input type="hidden" name="command" value="change_model">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=MODEL_ID%>" value="<%=model.getId()%>">

                <div class="f-input">
                    <label class="form-el">Активно</label>
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=model.getIsActive()%>"
                           <%if (model.getIsActive()) {%>checked<%}%>>
                </div>

                <div class="f-input">
                    <label class="form-el">Бренд:</label>
                    <select class="f-form " name="<%=BRAND_ID%>" size="1">
                        <%for (BrandDTO brandDTO : brandList) {%>
                        <option value="<%=brandDTO.getId()%>"><%=brandDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Тип устройства:</label>
                    <select class="f-form " name="<%=DEVICE_TYPE_ID%>" size="1">
                        <%for (DeviceTypeDTO deviceTypeDTO : deviceTypes) {%>
                        <option value="<%=deviceTypeDTO.getId()%>"><%=deviceTypeDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Название модели</label>
                    <input class="f-form" type="text" name="<%=MODEL_NAME%>" value="<%=model.getName()%>">
                </div>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="change_model_id"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>
        </div>



    </div>
</section>

</body>
