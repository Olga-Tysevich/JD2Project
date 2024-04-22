<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>

    <%
        BrandDTO brand = (BrandDTO) request.getAttribute(BRAND);
    %>


    <input type="hidden" name="<%=OBJECT_ID%>" value="<%=brand.getId()%>">
      <div class="f-input">
           <label class="form-el">Активен</label>
           <label >Активный: </label>
           <label >да: </label>
           <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (brand.getIsActive()) {%>checked<%}%> />
           <label >нет: </label>
           <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!brand.getIsActive()) {%>checked<%}%>/>
      </div>

      <div class="f-input">
           <label class="form-el">Название</label>
           <input class="f-form" required type="text" name="<%=OBJECT_NAME%>" value="<%=brand.getName()%>">
      </div>
