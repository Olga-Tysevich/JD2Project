<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.utils.enums.RepairCategory" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_REPAIR" %>
<%@ page import="it.academy.dto.resp.*" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE" %>
<%@ page import="it.academy.dto.spare_part.SparePartOrderDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.dto.repair.ChangeRepairFormDTO" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.dto.repair.RepairFormDTO" %>
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
            Long lastBrandId = request.getAttribute(BRAND_ID) != null ? (long) request.getAttribute(BRAND_ID) : brands.get(0).getId();
            Long repairId = repairDTO != null? repairDTO.getId() : null;
            String pageForDisplay = (String) request.getAttribute(PAGE);
            int pageNumber = request.getParameter(PAGE_NUMBER) != null? Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
            List<SparePartOrderDTO> orders = changeRepairForm.getOrders();
            Long deviceTypeId = repairDTO.getDeviceId();
        %>

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_REPAIR%>">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairId%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=pageForDisplay%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
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
                       value="<%=currentAccount.getServiceCenter().getId()%>">
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
                    <select class="f-form " name="<%=CURRENT_BRAND_ID%>" size="1" id="select_send">
                        <%for (BrandDTO brandDTO : brands) {%>
                        <option value="<%=brandDTO.getId()%>"
                                <%if(brandDTO.getId().equals(repairDTO.getBrandId())) {%>selected<%}%>>
                            <%=brandDTO.getName() + " " + brandDTO.getId()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Модель:</label>
                    <select class="f-form " name="<%=MODEL_ID%>" size="1">
                        <%for (ModelDTO modelDTO : models) {%>
                        <option value="<%=modelDTO.getId()%>"
                                <%if(modelDTO.getId().equals(repairDTO.getModelId())) {%>selected<%}%>>
                            <%=modelDTO.getName()%></option>
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


                <div class="f-input">
                    <%
                        String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                    %>
                    <p class="error" id="error" style="display: none"><%=errorMessage%></p>
                </div>

                <% if(repairDTO.getStatus().isFinishedStatus()) {%>
                <div class="f-input">
                        <label class="date-label" for="endDate">Дата завершения: </label>
                    <div class="date-container">
                        <input class="f-form" required type="date" id="endDate" name="<%=END_DATE%>"  value="<%=repairDTO.getStartDate()%>"/>
                    </div>
                </div>

                <div class="f-input">
                    <p>
                        Выполненный ремонт:
                        <label for="repairCode">Код:</label>
                        <input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairDTO.getRepairTypeId()%>">
                        <input type="text" id="repairCode" name="<%=REPAIR_TYPE_CODE%>"
                               value="<%=repairDTO.getRepairTypeCode() == null? "" : repairDTO.getRepairTypeCode()%>"/>
                        <label for="repairLevel">Уровень:</label>
                        <input type="text" id="repairLevel" name="<%=REPAIR_TYPE_LEVEL%>"
                               value="<%=repairDTO.getRepairTypeLevel() == null? "" : repairDTO.getRepairTypeLevel()%>"/>
                        <label for="repairName">Описание:</label>
                        <input type="text" id="repairName" name="<%=REPAIR_TYPE_NAME%>"
                               value="<%=repairDTO.getRepairTypeName() == null? "" : repairDTO.getRepairTypeName()%>"/>
                    </p>
                </div>
                <%if (RepairStatus.DELIVERED.equals(repairDTO.getStatus())) { %>

                <div class="f-input">
                    <p>
                        <label for="deliveryDate">Дата выдачи: </label>
                        <input type="date" required id="deliveryDate" name="<%=DELIVERY_DATE%>"  value="<%=repairDTO.getStartDate()%>"/>
                    </p>
                </div>
                <% }  %>
                <% } else { %>
                <div class="lf-button-container">
                    <input class="choose-button btn-table lf-button" type="submit" value="Заказать запчасти" form="order"/>
                    <input class="choose-button btn-table lf-button" type="submit" value="Сообщить о выполнении" form="completed"/>
                </div>
                <% }  %>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
                    <input class="button" type="submit" value="Отмена" form="cancel"/>
                </div>

            </form>
        </div>

        <form action="main" method="post" id="cancel">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_REPAIR_TABLE%>">
            <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
        </form>

        <% if (orders != null && !orders.isEmpty()) {
            request.setAttribute(ORDERS, orders);
            request.setAttribute(ORDER_REPAIR_ID, repairId);
            request.setAttribute(PAGE, pageForDisplay);
            request.setAttribute(PAGE_NUMBER, pageNumber);
            pageContext.include(CHANGE_SPARE_PART_ORDER_PAGE_PATH);
        }%>

        <form action="repair" method="post" id="order">
            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_ORDER_SPARE_PART%>">
            <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
            <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairNumber()%>">
            <input type="hidden" name="<%=SPARE_PART_MODEL_ID%>" value="<%=deviceTypeId%>">
            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
        </form>

        <form action="repair" method="post" id="completed">
            <input type="hidden" name="<%=COMMAND%>" value="show_repair_type_list">
            <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
            <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairNumber()%>">
        </form>


    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/RepairForm.js"></script>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>

</body>
