<%@ page import="static it.academy.utils.constants.Constants.COMMAND" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_REPAIR_TYPE" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.REPAIR_TYPE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="included-container">

    <% RepairTypeDTO repairType = (RepairTypeDTO) request.getAttribute(REPAIR_TYPE); %>

    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Создание типа ремонта</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_REPAIR_TYPE%>">
                <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=true%>">

               <div class="f-input">
                <label class="form-el">Название</label>
                <input class="f-form" required type="text" name="<%=OBJECT_NAME%>" value="<%=repairType != null? repairType.getName()
                    : StringUtils.EMPTY%>">
            </div>

                <div class="f-input">
                    <label class="form-el">Код ремнота</label>
                    <input class="f-form" required type="text" name="<%=REPAIR_TYPE_CODE%>" value="<%=repairType != null? repairType.getName()
                        : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Уровень ремнота</label>
                    <input class="f-form" required type="text" name="<%=REPAIR_TYPE_LEVEL%>" value="<%=repairType != null? repairType.getLevel()
                        : StringUtils.EMPTY%>">
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