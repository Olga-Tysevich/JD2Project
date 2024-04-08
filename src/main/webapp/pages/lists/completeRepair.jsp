<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.Constants" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.entities.repair.components.RepairStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <div class=" container">

        <div class=" forms-container">

            <%
                RepairDTO repairDTO = (RepairDTO) request.getAttribute(REPAIR);
                List<RepairTypeDTO> repairTypes = (List<RepairTypeDTO>) request.getAttribute(MODELS);
            %>

            <div class="form-container r-form">
                <form class="rc-form" action="repair" method="post" id="showRepair">
                    <input type="hidden" name="command" value="change_repair">
                    <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairDTO.getId()%>">
                    <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=RepairStatus.COMPLETED%>">
                    <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairDTO.getRepairWorkshopRepairNumber()%>">
                    <input type="hidden" name="<%=DEVICE_TYPE_ID%>" value="<%=repairDTO.getDeviceTypeId()%>">
                    <%--<input type="hidden" name="page" value="<%=pageNumber%>">--%>
                    <div class="f-input">
                        <label class="form-el">Выполненный ремонт:</label>
                        <select class="f-form " name="id" size="0">
                            <%for (RepairTypeDTO repairType : repairTypes) { %>
                            <option value="<%=repairType.getId()%>"><%=repairType.getName()%></option>
                            <% } %>
                        </select>
                    </div>
                </form>
            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Подтвердить" form="showRepair"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>

        </div>

    </div>
</section>
</body>
