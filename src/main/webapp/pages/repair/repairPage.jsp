<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.utils.enums.RepairCategory" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.dto.repair.ChangeRepairFormDTO" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.dto.repair.RepairFormDTO" %>
<%@ page import="it.academy.servlets.commands.factory.CommandEnum" %>
<%@ page import="it.academy.dto.device.DeviceDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_ORDER" %>
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
            CommandEnum formCommand = (CommandEnum) request.getAttribute(COMMAND);
            CommandEnum tableCommand = (CommandEnum) request.getAttribute(DISPLAY_TABLE_COMMAND);
            String tablePage = (String) request.getAttribute(PAGE);
            String formPage = (String) request.getAttribute(FORM_PAGE);
            int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        %>

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=formCommand%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
        <%
            AccountDTO currentAccount = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
            RoleEnum role = currentAccount.getRole();
            ChangeRepairFormDTO changeRepairForm = (ChangeRepairFormDTO) request.getAttribute(CHANGE_REPAIR_FORM);
            RepairFormDTO repairForm = changeRepairForm.getRepairFormDTO();
            Map<Long, String> serviceCenters = repairForm.getServiceCenters();
            List<BrandDTO> brands = repairForm.getBrands();
            List<ModelDTO> models = repairForm.getModels();
            List<RepairStatus> statuses = List.of(RepairStatus.values());
            List<RepairCategory> categoryList = List.of(RepairCategory.values());
            RepairDTO repairDTO = changeRepairForm.getRepairDTO();
            DeviceDTO deviceDTO = repairDTO.getDeviceDTO();
            ModelDTO modelDTO = deviceDTO.getModel();
            Long lastBrandId = (long) request.getAttribute(BRAND_ID);
            Long repairId = repairDTO.getId();
        %>

                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairId%>">
                <input type="hidden" name="<%=BRAND_ID%>" value="<%=lastBrandId%>">
                <input type="hidden" name="<%=DEVICE_ID%>" value="<%=repairId%>">

                <% if (RoleEnum.ADMIN.equals(role)) { %>
                <div class="f-input">
                    <label class="form-el">Сервисный центр:</label>
                    <select class="f-form " name="<%=SERVICE_CENTER_ID%>" size="1">
                        <%for (Map.Entry<Long, String> serviceCenter : serviceCenters.entrySet()) {%>
                        <option value="<%=serviceCenter.getKey()%>"
                                <%if(serviceCenter.getKey().equals(repairId)) {%>selected<%}%>>
                            <%=serviceCenter.getValue()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <% } else { %>
                <input type="hidden" name="<%=SERVICE_CENTER_ID%>"
                       value="<%=currentAccount.getServiceCenterId()%>">
                <% } %>

                <div class="f-input">
                    <label class="form-el">Статус ремонта:</label>
                    <select class="f-form " name="<%=REPAIR_STATUS%>" size="1">
                        <%for (RepairStatus status : statuses) {%>
                        <option value="<%=status.name()%>"
                                <%if(status.equals(repairDTO.getStatus())) {%>selected<%}%>>
                            <%=status.getDescription()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="f-input">
                    <label class="form-el">Категория ремонта:</label>
                    <select class="f-form " name="<%=REPAIR_CATEGORY%>" size="1">
                        <%for (RepairCategory category : categoryList) {%>
                        <option value="<%=category.name()%>"
                                <%if(category.equals(repairDTO.getCategory())) {%>selected<%}%>>
                            <%=category.getDescription()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Бренд:</label>
                    <select class="f-form " name="<%=SELECTED_BRAND_ID%>" size="1" id="select_send">
                        <%for (BrandDTO brandDTO : brands) {%>
                        <option value="<%=brandDTO.getId()%>"
                                <%if(brandDTO.getId().equals(modelDTO.getBrandId())) {%>selected<%}%>>
                            <%=brandDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Модель:</label>
                    <select class="f-form " name="<%=MODEL_ID%>" size="1">
                        <%for (ModelDTO model : models) {%>
                        <option value="<%=model.getId()%>"
                                <%if(model.getId().equals(modelDTO.getId())) {%>selected<%}%>>
                            <%=model.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el" for="sn">Серийный номер:</label>
                    <input class="f-form" required type="text" name="<%=SERIAL_NUMBER%>"
                           value="<%=repairDTO.getSerialNumber()%>" id="sn">
                </div>

                <div class="f-input">
                    <label class="form-el" for="defectDescription">Причина обращения:</label>
                    <input class="f-form" required type="text" name="<%=DEFECT_DESCRIPTION%>"
                           value="<%=repairDTO.getDefectDescription()%>" id="defectDescription">
                </div>

                <div class="f-input">
                    <label class="form-el" for="serviceNumber">Номер заказ-наряда:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_REPAIR_NUMBER%>"
                           value="<%=repairDTO.getRepairNumber()%>" id="serviceNumber">
                </div>

                <div class="f-input">
                        <label class="date-label" for="saleDate">Дата продажи: </label>
                    <div class="date-container">
                        <input class="f-form" required type="date" id="saleDate" name="<%=DATE_OF_SALE%>"
                               value="<%=repairDTO.getDateOfSale()%>"/>
                    </div>
                </div>


                <div class="f-input">
                    <label class="form-el" for="salesman">Продавец:</label>
                    <input class="f-form" required type="text" name="<%=SALESMAN_NAME%>"
                           value="<%=repairDTO.getSalesmanName()%>" id="salesman">
                </div>

                <div class="f-input">
                    <label class="form-el" for="salesmanPhone">Телефон продавца:</label>
                    <input class="f-form" required type="tel" name="<%=SALESMAN_PHONE%>"
                           value="<%=repairDTO.getSalesmanPhone()%>" id="salesmanPhone">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerName">Имя владельца:</label>
                    <input class="f-form" required type="text" name="<%=BUYER_NAME%>"
                           value="<%=repairDTO.getBuyerName()%>" id="buyerName">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerSurname">Фамилия владельца:</label>
                    <input class="f-form" required type="text" name="<%=BUYER_SURNAME%>"
                           value="<%=repairDTO.getBuyerSurname()%>" id="buyerSurname">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerPhone">Телефон владельца:</label>
                    <input class="f-form" required type="tel" name="<%=BUYER_PHONE%>"
                           value="<%=repairDTO.getBuyerPhone()%>" id="buyerPhone">
                </div>

                <div class="button-container">
                    <form action="order" method="post" id="order">
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SPARE_PART_ORDER%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
                        <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairNumber()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input class="choose-button btn-table" type="submit" value="Заказать запчасти" form="order"/>
                    </form>

                    <form action="repair" method="post" id="completed">
                        <input type="hidden" name="<%=COMMAND%>" value="show_repair_type_list">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
                        <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairNumber()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input class="choose-button btn-table" type="submit" value="Сообщить о выполнении" form="completed"/>
                    </form>
                </div>

            </form>
        </div>
        </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/RepairForm.js"></script>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>

</body>
