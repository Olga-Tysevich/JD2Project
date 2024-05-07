<%@ page import="static it.academy.utils.constants.Constants.COMMAND" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_BRAND" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="included-container">


    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Создание бренда</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_BRAND%>">

                <div class="f-input">
                    <label class="form-el">Описание</label>
                    <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="">
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