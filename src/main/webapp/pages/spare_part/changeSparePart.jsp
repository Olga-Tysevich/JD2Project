<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.spare_part.SparePartForChangeDTO" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%
        SparePartForChangeDTO sparePart = (SparePartForChangeDTO) request.getAttribute(SPARE_PART);
        List<ModelDTO> models = sparePart.getAllModels();
        List<ModelDTO> relatedModels = sparePart.getRelatedModels();
    %>

    <% if (sparePart.getId() != null) { %>
        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=sparePart.getId()%>">
    <div class="f-input">
        <label class="form-el">Активен</label>
        <label >Активный: </label>
        <label >да: </label>
        <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (sparePart.getIsActive()) {%>checked<%}%> />
        <label >нет: </label>
        <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!sparePart.getIsActive()) {%>checked<%}%>/>
    </div>
    <% } %>

    <div class="f-input">
        <label class="form-el">Название запчасти</label>
        <input class="f-form" type="text" required name="<%=OBJECT_NAME%>" value="<%=sparePart.getName()%>">
    </div>

    <div class="f-input">
        <label class="form-el">Модели:</label>
        <div>
            <div class="model_container">
                <%for (ModelDTO model : models) {%>
                <div>
                    <input type="checkbox" name="<%=SPARE_PART_MODEL_ID%>" value="<%=model.getId()%>"
                           <% if(relatedModels != null && relatedModels.contains(model)) {%>checked<%}%>>
                    <%=model.getName()%>
                </div>
                <%}%>
            </div>
        </div>
    </div>
