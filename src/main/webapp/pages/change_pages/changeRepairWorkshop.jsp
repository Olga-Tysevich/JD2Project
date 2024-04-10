<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.repair_workshop.RepairWorkshopDTO" %>
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
            RepairWorkshopDTO repairWorkshop = (RepairWorkshopDTO) request.getAttribute(REPAIR_WORKSHOP);
            String command = (String) request.getAttribute(COMMAND);
        %>
            <div class="lr-container">
                    <form action="main" method="post" id="repair_workshop_id">
                        <div class="f-input">
                        <input type="hidden" name="command" value="<%=command%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        </div>
                        <% if (repairWorkshop.getId() != null) {%>
                        <input type="hidden" name="<%=REPAIR_WORKSHOP_ID%>" value="<%=repairWorkshop.getId()%>">
                        <div class="f-input">
                            <div class="radio-container-rp">
                                <label for="isActive">Активный: </label>
                                <input type="radio" id="isActive" name="<%=IS_ACTIVE%>"  value="<%=repairWorkshop.getIsActive()%>"
                                <%if (repairWorkshop.getIsActive()) {%>checked<%}%> />
                            </div>
                        </div>
                        <%} %>
                        <div class="f-input">
                            <label class="form-el">Сервисное имя:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_SERVICE_NAME%>" value="<%=repairWorkshop.getServiceName()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Банк:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_BANK_NAME%>" value="<%=repairWorkshop.getBankName()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Банковский аккаунт:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_BANK_ACCOUNT%>" value="<%=repairWorkshop.getBankAccount()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">БИК:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_BANK_CODE%>" value="<%=repairWorkshop.getBankCode()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Адрес банка:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_BANK_ADDRESS%>" value="<%=repairWorkshop.getBankAddress()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Полное название:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_FULL_NAME%>" value="<%=repairWorkshop.getFullName()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Юридический адрес:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_LEGAL_ADDRESS%>" value="<%=repairWorkshop.getLegalAddress()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Фактический адрес:</label>
                        <input type="text" name="<%=REPAIR_WORKSHOP_ACTUAL_ADDRESS%>" value="<%=repairWorkshop.getActualAddress()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">Телефон:</label>
                        <input type="tel" name="<%=REPAIR_WORKSHOP_PHONE%>" value="<%=repairWorkshop.getPhone()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">email:</label>
                        <input type="email" name="<%=REPAIR_WORKSHOP_EMAIL%>" value="<%=repairWorkshop.getEmail()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">УНН:</label>
                        <input type="number" name="<%=REPAIR_WORKSHOP_TAXPAYER_NUMBER%>" value="<%=repairWorkshop.getTaxpayerNumber()%>">
                        </div>

                        <div class="f-input">
                            <label class="form-el">ОКПО:</label>
                        <input type="number" name="<%=REPAIR_WORKSHOP_REGISTRATION_NUMBER%>" value="<%=repairWorkshop.getRegistrationNumber()%>">
                        </div>

                        <div class="button-container">
                            <input class="button" type="submit" value="Сохранить" form="repair_workshop_id"/>
                            <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
                        </div>

                    </form>
            </div>
    </div>
</section>

</body>
