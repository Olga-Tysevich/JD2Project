<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="it.academy.dto.repair.RepairFormDTO" %>
<%@ page import="it.academy.utils.enums.RepairCategory" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.ADD_REPAIR_PAGE_PATH" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.ADD_REPAIR" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
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
            RepairFormDTO repairForm = (RepairFormDTO) request.getAttribute(REPAIR_FORM);
            RepairDTO repairDTO = repairForm.getRepairDTO();
            List<BrandDTO> brands = repairForm.getBrands();
            List<ModelDTO> models = repairForm.getModels();
            List<RepairCategory> categoryList = List.of(RepairCategory.values());
        %>

<div class="lr-container">
     <form class="lr-form" action="main" method="post" id="form_for_submit">
        <input type="hidden" name="<%=PAGE%>" value="<%=ADD_REPAIR_PAGE_PATH%>">
        <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_REPAIR%>" id="command_id">
        <input type="hidden" name="<%=SERVICE_CENTER_ID%>" value="<%=currentAccount.getServiceCenterId()%>">
        <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=RepairStatus.REQUEST%>">

        <div class="f-input">
            <label class="form-el">Категория ремонта:</label>
            <select class="f-form " name="<%=REPAIR_CATEGORY%>" size="1">
                <%for (RepairCategory category : categoryList) {%>
                <option value="<%=category.name()%>">
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
     </form>

        <div class="button-container">
            <input class="button" type="submit" value="Сохранить" form="form_for_submit"/>
            <button class="button"
                    onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">Отмена</button>
        </div>

    </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/RepairForm.js"></script>
<script rel="script" src="${pageContext.request.contextPath}/js/ErrorBlock.js"></script>

</body>
