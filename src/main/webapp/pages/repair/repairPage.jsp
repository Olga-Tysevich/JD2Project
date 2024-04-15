<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.req.BrandDTO" %>
<%@ page import="it.academy.dto.resp.AccountDTO" %>
<%@ page import="it.academy.dto.resp.ModelDTO" %>
<%@ page import="it.academy.dto.resp.RepairFormDTO" %>
<%@ page import="it.academy.utils.enums.RepairCategory" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.academy.dto.resp.RepairDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_REPAIR" %>
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
            RepairFormDTO repairForm = (RepairFormDTO) request.getAttribute(REPAIR_FORM);
            Map<Long, String> serviceCenters = repairForm.getServiceCenters();
            List<BrandDTO> brands = repairForm.getBrands();
            List<ModelDTO> models = repairForm.getModels();
            List<RepairStatus> statuses = List.of(RepairStatus.values());
            List<RepairCategory> categoryList = List.of(RepairCategory.values());
            RepairDTO repairDTO = request.getAttribute(REPAIR) != null? (RepairDTO) request.getAttribute(REPAIR) : null;
            Long lastBrandId = request.getAttribute(BRAND_ID) != null ? (long) request.getAttribute(BRAND_ID) : brands.get(0).getId();
            Long repairId = repairDTO != null? repairDTO.getId() : null;
            String pageForDisplay = (String) request.getAttribute(PAGE);
            int pageNumber = request.getParameter(PAGE_NUMBER) != null? Integer.parseInt(request.getParameter(PAGE_NUMBER)) : FIRST_PAGE;
        %>

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_REPAIR%>">
                <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairId%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=pageForDisplay%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=BRAND_ID%>" value="<%=lastBrandId%>">
                <input type="text" name="<%=BRAND_ID%>" value="<%=lastBrandId%>">
                <input type="hidden" name="<%=DEVICE_ID%>" value="<%=repairDTO != null? repairDTO.getId(): null%>">

                <% if (RoleEnum.ADMIN.equals(role)) { %>
                <div class="f-input">
                    <label class="form-el">Сервисный центр:</label>
                    <select class="f-form " name="<%=REPAIR_SERVICE_CENTER_ID%>" size="1">
                        <%for (Map.Entry<Long, String> serviceCenter : serviceCenters.entrySet()) {%>
                        <option value="<%=serviceCenter.getKey()%>"
                                <%if(repairDTO != null && serviceCenter.getKey().equals(repairId)) {%>selected<%}%>>
                            <%=serviceCenter.getValue()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <% } else { %>
                <input type="hidden" name="<%=REPAIR_SERVICE_CENTER_ID%>"
                       value="<%=currentAccount.getServiceCenter().getId()%>">
                <% } %>

                <div class="f-input">
                    <label class="form-el">Статус ремонта:</label>
                    <select class="f-form " name="<%=REPAIR_STATUS%>" size="1">
                        <%for (RepairStatus status : statuses) {%>
                        <option value="<%=status.name()%>"
                                <%if(repairDTO != null && status.equals(repairDTO.getStatus())) {%>selected<%}%>>
                            <%=status.getDescription()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="f-input">
                    <label class="form-el">Категория ремонта:</label>
                    <select class="f-form " name="<%=REPAIR_CATEGORY%>" size="1">
                        <%for (RepairCategory category : categoryList) {%>
                        <option value="<%=category.name()%>"
                                <%if(repairDTO != null && category.equals(repairDTO.getCategory())) {%>selected<%}%>>
                            <%=category.getDescription()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Бренд:</label>
                    <select class="f-form " name="<%=CURRENT_BRAND_ID%>" size="1" id="select_send">
                        <%for (BrandDTO brandDTO : brands) {%>
                        <option value="<%=brandDTO.getId()%>"
                                <%if(repairDTO != null && brandDTO.getId().equals(repairDTO.getBrandId())) {%>selected<%}%>>
                            <%=brandDTO.getName() + " " + brandDTO.getId()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Модель:</label>
                    <select class="f-form " name="<%=MODEL_ID%>" size="1">
                        <%for (ModelDTO modelDTO : models) {%>
                        <option value="<%=modelDTO.getId()%>"
                                <%if(repairDTO != null && modelDTO.getId().equals(repairDTO.getModelId())) {%>selected<%}%>>
                            <%=modelDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el" for="sn">Серийный номер:</label>
                    <input class="f-form" required type="text" name="<%=SERIAL_NUMBER%>"
                           value="<%=repairDTO != null? repairDTO.getSerialNumber() : ""%>" id="sn">
                </div>

                <div class="f-input">
                    <label class="form-el" for="defectDescription">Причина обращения:</label>
                    <input class="f-form" required type="text" name="<%=DEFECT_DESCRIPTION%>"
                           value="<%=repairDTO != null? repairDTO.getDefectDescription() : ""%>" id="defectDescription">
                </div>

                <div class="f-input">
                    <label class="form-el" for="serviceNumber">Номер заказ-наряда:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_REPAIR_NUMBER%>"
                           value="<%=repairDTO != null? repairDTO.getRepairNumber() : ""%>" id="serviceNumber">
                </div>

                <div class="f-input">
                        <label class="date-label" for="saleDate">Дата продажи: </label>
                    <div class="date-container">
                        <input class="f-form" required type="date" id="saleDate" name="<%=DATE_OF_SALE%>"
                               value="<%=repairDTO != null? repairDTO.getDateOfSale() : ""%>"/>
                    </div>
                </div>


                <div class="f-input">
                    <label class="form-el" for="salesman">Продавец:</label>
                    <input class="f-form" required type="text" name="<%=SALESMAN_NAME%>"
                           value="<%=repairDTO != null? repairDTO.getSalesmanName() : ""%>" id="salesman">
                </div>

                <div class="f-input">
                    <label class="form-el" for="salesmanPhone">Телефон продавца:</label>
                    <input class="f-form" required type="tel" name="<%=SALESMAN_PHONE%>"
                           value="<%=repairDTO != null? repairDTO.getSalesmanPhone() : ""%>" id="salesmanPhone">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerName">Имя владельца:</label>
                    <input class="f-form" required type="text" name="<%=BUYER_NAME%>"
                           value="<%=repairDTO != null? repairDTO.getBuyerName() : ""%>" id="buyerName">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerSurname">Фамилия владельца:</label>
                    <input class="f-form" required type="text" name="<%=BUYER_SURNAME%>"
                           value="<%=repairDTO != null? repairDTO.getBuyerSurname() : ""%>" id="buyerSurname">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerPhone">Телефон владельца:</label>
                    <input class="f-form" required type="tel" name="<%=BUYER_PHONE%>"
                           value="<%=repairDTO != null? repairDTO.getBuyerPhone() : ""%>" id="buyerPhone">
                </div>


                <div class="f-input">
                    <%
                        String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                    %>
                    <p class="error" id="error" style="display: none"><%=errorMessage%></p>
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
                    <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
                </div>

            </form>
        </div>

    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/RepairForm.js"></script>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>

</body>
