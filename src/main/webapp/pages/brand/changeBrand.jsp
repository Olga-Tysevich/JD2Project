<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_BRAND" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>

    <%
        BrandDTO brand = (BrandDTO) request.getAttribute(BRAND);
    %>


<div class="included-container">

    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Создание бренда</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_BRAND%>">
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