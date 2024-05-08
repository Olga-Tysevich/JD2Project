<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<%@ page import="it.academy.dto.repair.UserRepairFormDTO" %>
<%@ page import="it.academy.dto.spare_part_order.SparePartOrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
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

        <div class="cancel-button-container">
            <button class="cancel-button"
                    onclick="location.href='<%=request.getSession().getAttribute(LAST_PAGE)%>'">X</button>
        </div>

            <%
                UserRepairFormDTO repairFormDTO = (UserRepairFormDTO) request.getAttribute(REPAIR_FORM);
                RepairDTO repairDTO = repairFormDTO.getRepairDTO();
                List<SparePartOrderDTO> orders = repairFormDTO.getOrders();
            %>

        <div class="lr-container">
            <form class="lr-form" action="main" method="post" id="form_for_submit">
                <input type="hidden" name="<%=COMMAND%>" value="<%=UPDATE_REPAIR%>" id="command_id">
                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">

                <div class="f-input">
                <label class="form-el">Модель:</label>
                    <div class="f-form"><%=repairDTO.getDeviceDescription()%></div>
                </div>

                <div class="f-input">
                    <label class="form-el">Серийный номер:</label>
                    <div class="f-form"><%=repairDTO.getSerialNumber()%></div>
                </div>

                <div class="f-input">
                    <label class="form-el">Причина обращения:</label>
                    <div class="f-form"><%=repairDTO.getDefectDescription()%></div>
                </div>

                <div class="f-input">
                    <label class="form-el">Номер заказ-наряда:</label>
                    <div class="f-form"><%=repairDTO.getRepairNumber()%></div>
                </div>

                <div class="f-input">
                    <label class="date-label">Дата продажи: </label>
                    <div class="date-container">
                        <input class="f-form" type="date" value="<%=repairDTO.getDateOfSale()%>" disabled/>
                    </div>
                </div>


                <div class="f-input">
                    <label class="form-el">Продавец:</label>
                    <div class="f-form"><%=repairDTO.getSalesmanName()%></div>
                </div>

                <div class="f-input">
                    <label class="form-el">Телефон продавца:</label>
                    <div class="f-form"><%=repairDTO.getSalesmanPhone()%></div>
                </div>

                <div class="f-input">
                    <label class="form-el">Имя владельца:</label>
                    <div class="f-form"><%=repairDTO.getBuyerName()%></div>
                </div>

                <div class="f-input">
                    <label class="form-el">Фамилия владельца:</label>
                    <div class="f-form"><%=repairDTO.getBuyerSurname()%></div>
                </div>

                <div class="f-input">
                    <label class="form-el">Телефон владельца:</label>
                    <div class="f-form"><%=repairDTO.getBuyerPhone()%></div>
                </div>

                <% if(repairDTO.getStatus().isFinishedStatus()) {
                    RepairTypeDTO repairType = repairDTO.getRepairType();
                %>
                <div class="f-input">
                    <label class="date-label">Дата завершения: </label>
                    <div class="date-container">
                        <input class="f-form" type="date" value="<%=repairDTO.getEndDate()%>" disabled/>
                    </div>
                </div>

                <div class="f-input">
                    Выполненный ремонт:
                    <div class="f-input">
                        <label >Код:</label>
                        <br>
                        <%=repairType.getCode()%>
                    </div>
                    <div class="f-input">
                        <label>Уровень:</label>
                        <br>
                        <%=repairType.getLevel()%>
                    </div>
                    <div class="f-input">
                        <label>Описание:</label>
                        <br>
                        <%=repairType.getName()%>
                    </div>
                </div>
                <% } %>

            </form>

            <%if (RepairStatus.CURRENT.equals(repairDTO.getStatus())
                    || RepairStatus.WAITING_FOR_SPARE_PARTS.equals(repairDTO.getStatus())) {%>
                <%@include file="included/userRepairTypeList.jsp"%>
            <div class="lf-button-container">
                <form action="order" method="get" id="order">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=GET_NEW_SPARE_PART_ORDER%>">
                    <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairDTO.getId()%>">
                    <input type="hidden" name="<%=MODEL_ID%>" value="<%=repairDTO.getModelId()%>">
                    <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairNumber()%>">
                    <input class="choose-button lf-button" type="submit" value="Заказать запчасти" form="order"/>
                </form>

                <button class="choose-button lf-button" type="button" onclick="showRepairTypes()">Сообщить о выполнении</button>
            </div>
            <%}%>

            <% if (orders != null && !orders.isEmpty()) { %>
                 <%@include file="included/sparePartOrders.jsp"%>
            <%}%>

        </div>

</section>
<script rel="script" src="${pageContext.request.contextPath}/js/RepairForm.js"></script>
</body>
