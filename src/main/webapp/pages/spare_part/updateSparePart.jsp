<%@ page import="static it.academy.utils.constants.Constants.COMMAND" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.spare_part.SparePartDTO" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.SPARE_PART" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.UPDATE_SPARE_PART" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="included-container">

    <%
        List<ModelDTO> models = (List<ModelDTO>) request.getAttribute(MODELS);
        SparePartDTO sparePart = (SparePartDTO) request.getAttribute(SPARE_PART);
        List<Long> relatedModels = sparePart.getModelIdList();
    %>

    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Создание запчасти</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=UPDATE_SPARE_PART%>">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=sparePart.getId()%>">
                <div class="f-input">
                    <label class="form-el">Активен</label>
                    <label >Активный: </label>
                    <label >да: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (sparePart.getIsActive()) {%>checked<%}%> />
                    <label >нет: </label>
                    <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!sparePart.getIsActive()) {%>checked<%}%>/>
                </div>

                <div class="f-input">
                    <label class="form-el">Название запчасти</label>
                    <input class="f-form" type="text" required name="<%=OBJECT_NAME%>" value="<%=sparePart.getName()%>">
                </div>

                <label class="form-el">Модели:</label>
                <div class="model_container">
                    <%for (ModelDTO model : models) {%>
                    <div>
                        <input type="checkbox" name="<%=SPARE_PART_MODEL_ID%>" value="<%=model.getId()%>"
                               <% if(relatedModels != null && relatedModels.contains(model.getId())) {%>checked<%}%>>
                        <%=String.format(MODEL_DESCRIPTION_PATTERN, model.getDeviceTypeName(), model.getBrandName(), model.getName())%>
                    </div>
                    <%}%>
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