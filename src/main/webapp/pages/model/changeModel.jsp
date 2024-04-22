<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.device.ModelForChangeDTO" %>

    <%
        ModelForChangeDTO modelData = (ModelForChangeDTO) request.getAttribute(MODEL);
        ModelDTO model = modelData.getModelDTO();
        List<BrandDTO> brandList = modelData.getBrands();
        List<DeviceTypeDTO> deviceTypes = modelData.getDeviceTypes();
    %>

        <% if (model.getId() != null) { %>
            <input type="hidden" name="<%=OBJECT_ID%>" value="<%=model.getId()%>">
        <% } %>
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
                <%for (DeviceTypeDTO deviceTypeDTO : deviceTypes) {%>
                <option value="<%=deviceTypeDTO.getId()%>" <%if (deviceTypeDTO.getId().equals(model.getDeviceTypeId())) {%>selected<%}%>>
                    <%=deviceTypeDTO.getName()%></option>
                <%}%>
            </select>
        </div>

        <div class="f-input">
            <label class="form-el">Название модели</label>
            <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="<%=model.getName()%>">
        </div>
