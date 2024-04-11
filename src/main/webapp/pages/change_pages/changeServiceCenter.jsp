<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.service_center.ServiceCenterDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>
    <div class="forms-container lf">

        <%
            int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
            ServiceCenterDTO serviceCenter = (ServiceCenterDTO) request.getAttribute(SERVICE_CENTER);
            String command = (String) request.getAttribute(COMMAND);
        %>
            <div class="lr-container">
                    <form action="main" method="post" id="service_center">
                        <div class="f-input">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=command%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        </div>
                        <%=OBJECT_ID%>
                        <%=serviceCenter%>
                        <% if (serviceCenter.getId() != null) {%>
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=serviceCenter.getId()%>">
                        <div class="f-input">
                            <div class="radio-container-rp">
                                <label for="isActive">Активный: </label>
                                <input type="radio" id="isActive" name="<%=IS_ACTIVE%>"  value="<%=serviceCenter.getIsActive()%>"
                                <%if (serviceCenter.getIsActive()) {%>checked<%}%> />
                            </div>
                        </div>
                        <%} %>
                        <div class="f-input">
                            <label class="form-el">Сервисное имя:</label>
                        <input type="text" name="<%=SERVICE_CENTER_NAME%>" value="<%=serviceCenter.getServiceName()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Банк:</label>
                        <input type="text" name="<%=SERVICE_CENTER_BANK_NAME%>" value="<%=serviceCenter.getBankName()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Банковский аккаунт:</label>
                        <input type="text" name="<%=SERVICE_CENTER_BANK_ACCOUNT%>" value="<%=serviceCenter.getBankAccount()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">БИК:</label>
                        <input type="text" name="<%=SERVICE_CENTER_BANK_CODE%>" value="<%=serviceCenter.getBankCode()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Адрес банка:</label>
                        <input type="text" name="<%=SERVICE_CENTER_BANK_ADDRESS%>" value="<%=serviceCenter.getBankAddress()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Полное название:</label>
                        <input type="text" name="<%=SERVICE_CENTER_FULL_NAME%>" value="<%=serviceCenter.getFullName()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Юридический адрес:</label>
                        <input type="text" name="<%=SERVICE_CENTER_LEGAL_ADDRESS%>" value="<%=serviceCenter.getLegalAddress()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Фактический адрес:</label>
                        <input type="text" name="<%=SERVICE_CENTER_ACTUAL_ADDRESS%>" value="<%=serviceCenter.getActualAddress()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Телефон:</label>
                        <input type="tel" name="<%=SERVICE_CENTER_PHONE%>" value="<%=serviceCenter.getPhone()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">email:</label>
                        <input type="email" name="<%=SERVICE_CENTER_EMAIL%>" value="<%=serviceCenter.getEmail()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">УНН:</label>
                        <input type="number" name="<%=SERVICE_CENTER_TAXPAYER_NUMBER%>" value="<%=serviceCenter.getTaxpayerNumber()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">ОКПО:</label>
                        <input type="number" name="<%=SERVICE_CENTER_REGISTRATION_NUMBER%>" value="<%=serviceCenter.getRegistrationNumber()%>">
                        </div>

                        <div class="button-container">
                            <input class="button" type="submit" value="Сохранить" form="service_center"/>
                            <input class="button" type="submit" value="Отмена" form="cancel"/>
                        </div>

                    </form>

                <form method="post" action="main" id="cancel">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SERVICE_CENTER_TABLE%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                </form>

            </div>
    </div>
</section>

</body>
