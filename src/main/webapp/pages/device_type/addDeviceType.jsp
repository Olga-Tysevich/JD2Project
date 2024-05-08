<%@ page import="static it.academy.utils.constants.Constants.COMMAND" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_DEVICE_TYPE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="included-container">

    <%
        String name = (String) request.getAttribute(OBJECT_NAME);
    %>

    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Создание типа устройства</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_DEVICE_TYPE%>">
                <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=true%>">


                <div class="f-input">
                    <label class="form-el">Тип устройства</label>
                    <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="<%=StringUtils.defaultIfBlank(name, StringUtils.EMPTY)%>">
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