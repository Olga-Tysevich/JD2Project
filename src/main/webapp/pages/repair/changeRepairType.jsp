<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>

    <%
        RepairTypeDTO repairType = (RepairTypeDTO) request.getAttribute(REPAIR_TYPE);
    %>

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
