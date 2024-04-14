<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.device.resp.ModelDTO" %>
<%@ page import="static it.academy.servlets.factory.CommandEnum.SHOW_MODEL_TABLE" %>
<%@ page import="static it.academy.servlets.factory.CommandEnum.CHANGE_MODEL" %>
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
        List<BrandDTO> brandList = model.getBrands();
        List<DeviceTypeDTO> deviceTypes = model.getDeviceTypes();
    %>

    <form action="main" method="post">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="change_model_id">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_MODEL%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=model.getId()%>">

                <div class="f-input">
                    <label class="form-el">Активен</label>
                    <label >Активный: </label>
                    <label >да: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (model.getIsActive()) {%>checked<%}%> />
                    <label >нет: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!model.getIsActive()) {%>checked<%}%>/>
                </div>

                <div class="f-input">
                    <label class="form-el">Бренд:</label>
                    <select class="f-form " name="<%=BRAND_ID%>" size="1">
                        <%for (BrandDTO brandDTO : brandList) {%>
                        <option value="<%=brandDTO.getId()%>" <%if (brandDTO.getId().equals(model.getBrandId())) {%>selected<%}%>>
                            <%=brandDTO.getName()%>
                            </option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Тип устройства:</label>
                    <select class="f-form " name="<%=TYPE_ID%>" size="1">
                        <%=DEVICE_TYPE_ID%>
                        <%for (DeviceTypeDTO deviceTypeDTO : deviceTypes) {%>
                        <option value="<%=deviceTypeDTO.getId()%>" <%if (deviceTypeDTO.getId().equals(model.getDeviceTypeId())) {%>selected<%}%>>
                            <%=deviceTypeDTO.getName()%></option>
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
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>
        </div>

        <form action="main" method="post" id="cancel">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_MODEL_TABLE%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
        </form>

    </div>
</section>

</body>