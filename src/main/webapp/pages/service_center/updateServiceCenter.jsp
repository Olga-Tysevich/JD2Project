<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.account.ServiceCenterDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.UPDATE_SERVICE_CENTER" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ServiceCenterDTO serviceCenter = (ServiceCenterDTO) request.getAttribute(SERVICE_CENTER);
    RoleEnum role = (RoleEnum) request.getSession().getAttribute(ROLE);
%>
<div class="included-container">

    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Изменение сервисного центра</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=UPDATE_SERVICE_CENTER%>">
                <% if (serviceCenter.getId() != null && RoleEnum.ADMIN.equals(role)) {%>
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=serviceCenter.getId()%>">
                <div class="f-input">
                    <div class="radio-container-rp">
                        <label >Активный: </label>
                        <label >да: </label>
                        <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (serviceCenter.getIsActive()) {%>checked<%}%> />
                        <label >нет: </label>
                        <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!serviceCenter.getIsActive()) {%>checked<%}%>/>
                    </div>
                </div>

                <div class="f-input">
                    <label class="form-el">Сервисное имя:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_NAME%>" value="<%=serviceCenter.getServiceName()%>">
                </div>
                <%} else {%>
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=serviceCenter.getId()%>">
                <input type="hidden" name="<%=SERVICE_CENTER_NAME%>" value="<%=serviceCenter.getServiceName()%>">
                <input type="hidden" name="<%=IS_ACTIVE%>"  value="<%=serviceCenter.getIsActive()%>">
                <%}%>

                <div class="f-input">
                    <label class="form-el">Банк:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_BANK_NAME%>" value="<%=serviceCenter.getBankName()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Банковский аккаунт:</label>
                    <input class="f-form" required  type="text" name="<%=SERVICE_CENTER_BANK_ACCOUNT%>" value="<%=serviceCenter.getBankAccount()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">БИК:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_BANK_CODE%>" value="<%=serviceCenter.getBankCode()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Адрес банка:</label>
                    <input class="f-form" required  type="text" name="<%=SERVICE_CENTER_BANK_ADDRESS%>" value="<%=serviceCenter.getBankAddress()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Полное название:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_FULL_NAME%>" value="<%=serviceCenter.getFullName()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Юридический адрес:</label>
                    <input class="f-form" required  type="text" name="<%=SERVICE_CENTER_LEGAL_ADDRESS%>" value="<%=serviceCenter.getLegalAddress()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Фактический адрес:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_ACTUAL_ADDRESS%>" value="<%=serviceCenter.getActualAddress()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Телефон:</label>
                    <input class="f-form" required type="tel" name="<%=SERVICE_CENTER_PHONE%>" value="<%=serviceCenter.getPhone()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">email:</label>
                    <input class="f-form" required type="email" name="<%=EMAIL%>" value="<%=serviceCenter.getEmail()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">УНН:</label>
                    <input class="f-form" required type="number" name="<%=SERVICE_CENTER_TAXPAYER_NUMBER%>" value="<%=serviceCenter.getTaxpayerNumber()%>">
                </div>

                <div class="f-input">
                    <label class="form-el">ОКПО:</label>
                    <input class="f-form" required type="number" name="<%=SERVICE_CENTER_REGISTRATION_NUMBER%>" value="<%=serviceCenter.getRegistrationNumber()%>">
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