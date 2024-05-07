<%@ page import="it.academy.dto.account.ServiceCenterDTO" %>
<%@ page import="static it.academy.utils.constants.Constants.SERVICE_CENTER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_SERVICE_CENTER" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ServiceCenterDTO serviceCenter = (ServiceCenterDTO) request.getAttribute(SERVICE_CENTER);
%>

<div class="included-container">

    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Создание сервисного центра</h1>
        </div>
        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_SERVICE_CENTER%>">
                <div class="f-input">
                    <label class="form-el">Сервисное имя:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_NAME%>" value="<%=serviceCenter != null?
                        serviceCenter.getServiceName() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Банк:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_BANK_NAME%>" value="<%=serviceCenter != null?
                        serviceCenter.getBankName() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Банковский аккаунт:</label>
                    <input class="f-form" required  type="text" name="<%=SERVICE_CENTER_BANK_ACCOUNT%>" value="<%=serviceCenter != null?
                        serviceCenter.getBankAccount() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">БИК:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_BANK_CODE%>" value="<%=serviceCenter != null?
                        serviceCenter.getBankCode() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Адрес банка:</label>
                    <input class="f-form" required  type="text" name="<%=SERVICE_CENTER_BANK_ADDRESS%>" value="<%=serviceCenter != null?
                        serviceCenter.getBankAddress() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Полное название:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_FULL_NAME%>" value="<%=serviceCenter != null?
                        serviceCenter.getFullName() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Юридический адрес:</label>
                    <input class="f-form" required  type="text" name="<%=SERVICE_CENTER_LEGAL_ADDRESS%>" value="<%=serviceCenter != null?
                        serviceCenter.getLegalAddress() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Фактический адрес:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_ACTUAL_ADDRESS%>" value="<%=serviceCenter != null?
                        serviceCenter.getActualAddress() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">Телефон:</label>
                    <input class="f-form" required type="tel" name="<%=SERVICE_CENTER_PHONE%>" value="<%=serviceCenter != null?
                        serviceCenter.getPhone() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">email:</label>
                    <input class="f-form" required type="email" name="<%=EMAIL%>" value="<%=serviceCenter != null?
                        serviceCenter.getEmail() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">УНН:</label>
                    <input class="f-form" required type="number" name="<%=SERVICE_CENTER_TAXPAYER_NUMBER%>" value="<%=serviceCenter != null?
                        serviceCenter.getTaxpayerNumber() : StringUtils.EMPTY%>">
                </div>

                <div class="f-input">
                    <label class="form-el">ОКПО:</label>
                    <input class="f-form" required type="number" name="<%=SERVICE_CENTER_REGISTRATION_NUMBER%>" value="<%=serviceCenter != null?
                        serviceCenter.getRegistrationNumber() : StringUtils.EMPTY%>">
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