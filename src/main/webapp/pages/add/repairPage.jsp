<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.entities.repair.components.RepairStatus" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.entities.repair.components.RepairCategory" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="it.academy.dto.device.req.ChangeModelDTO" %>
<%@ page import="java.sql.Date" %>
<%@ page import="it.academy.dto.device.resp.DeviceDTOResp" %>
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
            RepairDTO repairDTO = (RepairDTO) request.getAttribute(REPAIR);
            long brandId = repairDTO.getDevice().getModel().getBrandId();
            Long deviceId = repairDTO.getDevice().getId();
            DeviceDTOResp device = repairDTO.getDevice();
            List<RepairStatus> statuses = List.of(RepairStatus.values());
            List<RepairCategory> categoryList = List.of(RepairCategory.values());
            List<BrandDTO> brandDTOList = (List<BrandDTO>) request.getAttribute(BRANDS);
            List<ChangeModelDTO> createModelDTOList = (List<ChangeModelDTO>) request.getAttribute(MODELS);
        %>

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="command" value="add_repair">
                <input type="hidden" name="<%=BRAND_ID%>" value="<%=brandId%>">
                <input type="hidden" name="<%=DEVICE_ID%>" value="<%=deviceId%>">

                <div class="f-input">
                    <label class="form-el">Статус ремонта:</label>
                    <select class="f-form " name="<%=REPAIR_STATUS%>" size="1">
                        <%for (RepairStatus status : statuses) {%>
                        <option value="<%=status.name()%>" <%if(status.equals(repairDTO.getStatus())) {%>selected<%}%>>
                            <%=status.getDescription()%></option>
                        <%}%>
                    </select>
                </div>
                <div class="f-input">
                    <label class="form-el">Категория ремонта:</label>
                    <select class="f-form " name="<%=REPAIR_CATEGORY%>" size="1">
                        <%for (RepairCategory category : categoryList) {%>
                        <option value="<%=category.name()%>" <%if(category.equals(repairDTO.getCategory())) {%>selected<%}%>>
                            <%=category.getDescription()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Бренд:</label>
                    <select class="f-form " name="<%=CURRENT_BRAND_ID%>" size="1" id="select_send">
                        <%for (BrandDTO brandDTO : brandDTOList) {%>
                        <option value="<%=brandDTO.getId()%>"
                                <%if(brandDTO.getId().equals(brandId)) {%>selected<%}%> >
                            <%=brandDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">Модель:</label>
                    <select class="f-form " name="<%=MODEL_ID%>" size="1">
                        <%for (ChangeModelDTO createModelDTO : createModelDTOList) {%>
                        <option value="<%=createModelDTO.getId()%>"
                                <%if(createModelDTO.getId().equals(deviceId)) {%>selected<%}%> >
                            <%=createModelDTO.getName()%></option>
                        <%}%>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el" for="sn">Серийный номер:</label>
                    <input class="f-form" required type="text" name="<%=SERIAL_NUMBER%>" value="<%=device.getSerialNumber()%>" id="sn">
                </div>

                <div class="f-input">
                    <label class="form-el" for="defectDescription">Причина обращения:</label>
                    <input class="f-form" required type="text" name="<%=DEFECT_DESCRIPTION%>"
                           value="<%=repairDTO.getDefectDescription()%>" id="defectDescription">
                </div>

                <div class="f-input">
                    <label class="form-el" for="serviceNumber">Номер заказ-наряда:</label>
                    <input class="f-form" required type="text" name="<%=SERVICE_CENTER_REPAIR_NUMBER%>"
                           value="<%=repairDTO.getServiceCenterRepairNumber()%>" id="serviceNumber">
                </div>

                <div class="f-input">
                    <p>
                        <label class="date-label" for="saleDate">Дата продажи: </label>
                    <div class="date-container">
                        <input class="f-form" required type="date" id="saleDate" name="<%=DATE_OF_SALE%>" value="<%=device.getDateOfSale()%>"/>
                    </div>
                    </p>
                </div>


                <div class="f-input">
                    <label class="form-el" for="salesman">Продавец:</label>
                    <input class="f-form" required type="text" name="<%=SALESMAN_NAME%>"
                           value="<%=device.getSalesmanName()%>" id="salesman">
                </div>

                <div class="f-input">
                    <label class="form-el" for="salesmanPhone">Телефон продавца:</label>
                    <input class="f-form" required type="tel" name="<%=SALESMAN_PHONE%>"
                           value="<%=device.getSalesmanPhone()%>" id="salesmanPhone">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerName">Имя владельца:</label>
                    <input class="f-form" required type="text" name="<%=BUYER_NAME%>"
                           value="<%=device.getBuyerName()%>" id="buyerName">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerSurname">Фамилия владельца:</label>
                    <input class="f-form" required type="text" name="<%=BUYER_SURNAME%>"
                           value="<%=device.getBuyerSurname()%>" id="buyerSurname">
                </div>

                <div class="f-input">
                    <label class="form-el" for="buyerPhone">Телефон владельца:</label>
                    <input class="f-form" required type="tel" name="<%=BUYER_PHONE%>"
                           value="<%=device.getBuyerPhone()%>" id="buyerPhone">
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

</body>
