<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.utils.enums.RepairCategory" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.dto.repair.RepairFormDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="it.academy.dto.spare_part_order.SparePartOrderDTO" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    AccountDTO accountDTO = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
    RoleEnum role = accountDTO.getRole();
    RepairFormDTO repairForm = (RepairFormDTO) request.getAttribute(REPAIR_FORM);
    List<BrandDTO> brands = repairForm.getBrands();
    List<ModelDTO> models = repairForm.getModels();
    List<RepairStatus> statuses = List.of(RepairStatus.values());
    List<RepairCategory> categoryList = List.of(RepairCategory.values());
    RepairDTO repairDTO = repairForm.getRepairDTO();
    List<SparePartOrderDTO> orders = repairForm.getOrders();
%>

<div class="included-container">
    <div class="forms-container lf">
        <div class="lr-container">
            <h1>Ремонт No.<%=repairDTO.getRepairNumber()%></h1>
        </div>
        <form class="lr-form" action="main" method="post" id="form_for_submit">
            <input type="hidden" name="<%=COMMAND%>" value="<%=UPDATE_REPAIR%>" id="command_id">
            <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
            <input type="hidden" name="<%=DEVICE_ID%>" value="<%=repairDTO.getDeviceId()%>">
            <input type="hidden" name="<%=SERVICE_CENTER_ID%>" value="<%=repairDTO.getServiceCenterId()%>">

            <% if (RoleEnum.ADMIN.equals(role)) {%>
            <div class="f-input">
                <label class="form-el">Сервисный центр:</label>
                <input class="f-form" type="text" name="<%=SERVICE_CENTER_NAME%>" value="<%=repairDTO.getServiceCenterName()%>" disabled>
            </div>

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
            <%}%>

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
                <select class="f-form " name="<%=BRAND_ID%>" size="1" id="select_send" onchange="getModelsForForm()">
                    <%for (BrandDTO brandDTO : brands) {%>
                    <option value="<%=brandDTO.getId()%>"
                            <%if(brandDTO.getId().equals(repairDTO.getBrandId())) {%>selected<%}%>>
                        <%=brandDTO.getName()%></option>
                    <%}%>
                </select>
            </div>

            <div class="f-input">
                <label class="form-el">Модель:</label>
                <select class="f-form " name="<%=MODEL_ID%>" size="1">
                    <%for (ModelDTO model : models) {%>
                    <option value="<%=model.getId()%>"
                            <%if(model.getId().equals(repairDTO.getModelId())) {%>selected<%}%>>
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

            <% if (RoleEnum.ADMIN.equals(role)) {
                RepairTypeDTO repairType = repairDTO.getRepairType();
            %>
            <div class="f-input">
                <label class="date-label">Дата завершения: </label>
                <div class="date-container">
                    <input class="f-form" type="date" value="<%=repairDTO.getStartDate()%>" disabled/>
                </div>
            </div>

            <div class="f-input">
                <label class="form-el" >Выполненный ремонт:</label>
                <div></div>
            </div>

            <div class="f-input">
                <label class="form-el" >Код:</label>
                    <input class="f-form" type="text" value="<%=repairType != null? repairType.getCode() : StringUtils.EMPTY%>" disabled/>
            </div>

            <div class="f-input">
                <label class="form-el" >Уровень:</label>
                    <input class="f-form" type="text" value="<%=repairType != null? repairType.getLevel() : StringUtils.EMPTY%>" disabled/>
            </div>

            <div class="f-input">
                <label class="form-el" >Описание:</label>
                    <input class="f-form" type="text" value="<%=repairType != null? repairType.getName() : StringUtils.EMPTY%>" disabled/>
            </div>

            <%}%>

            <%@include file="../forms/errorContainer.jsp"%>
        </form>



        <% if (RoleEnum.ADMIN.equals(role)) {%>
        <%@include file="included/adminRepairTypeList.jsp"%>

        <div class="button-container">
            <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>

            <button class="button" type="button" onclick="showRepairTypes()">Изменить тип ремонта</button>

            <button class="button"
                    onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">Отмена</button>
        </div>
        <%} else {%>
            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
                <button class="button"
                        onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">Отмена</button>
            </div>
        <%}%>

        <% if (orders != null && !orders.isEmpty()) { %>
        <%@include file="included/sparePartOrders.jsp"%>
        <%}%>

    </div>
</div>
<script rel="script" src="${pageContext.request.contextPath}/js/RepairForm.js"></script>
<script rel="script" src="${pageContext.request.contextPath}/js/ErrorBlock.js"></script>
