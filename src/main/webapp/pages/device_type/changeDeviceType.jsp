<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.DeviceTypeDTO" %>

    <%
        DeviceTypeDTO deviceType = (DeviceTypeDTO) request.getAttribute(DEVICE_TYPE);
    %>

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
