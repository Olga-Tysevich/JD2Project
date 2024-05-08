<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.device.ModelFormDTO" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.MODEL_FORM" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.UPDATE_MODEL" %>

<%
    ModelFormDTO modelForm = (ModelFormDTO) request.getAttribute(MODEL_FORM);
    List<BrandDTO> brandList = modelForm.getBrands();
    List<DeviceTypeDTO> deviceTypes = modelForm.getDeviceTypes();
    ModelDTO model = (ModelDTO) request.getAttribute(MODEL);
%>

<div class="included-container">


    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Изменение модели</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=UPDATE_MODEL%>">
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

                <%@include file="../forms/errorContainer.jsp"%>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
                <button class="button"
                        onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">Отмена</button>
            </div>

        </div>

    </div>

</div>
