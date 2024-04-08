<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>

<%
    RepairTypeDTO repairType = (RepairTypeDTO) request.getAttribute(REPAIR_TYPE);
%>

<input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairType.getId()%>">

<div class="f-input">
    <label class="form-el">Код ремонта</label>
    <input type="text" name="<%=REPAIR_TYPE_CODE%>" value="<%=repairType.getCode()%>">
</div>

<div class="f-input">
    <label class="form-el">Уровень ремонта</label>
    <input type="text" name="<%=REPAIR_TYPE_LEVEL%>" value="<%=repairType.getLevel()%>">
</div>

<div class="f-input">
    <label class="form-el">Описание</label>
    <input type="text" name="<%=REPAIR_TYPE_NAME%>" value="<%=repairType.getName()%>">
</div>


