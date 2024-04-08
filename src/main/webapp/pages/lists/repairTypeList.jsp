<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
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
                long repairId = (long) request.getAttribute(REPAIR_ID);
                String repairNumber = (String) request.getAttribute(REPAIR_NUMBER);
                List<RepairTypeDTO> repairTypes = (List<RepairTypeDTO>) request.getAttribute(REPAIR_TYPES);
            %>

            <div class="form-container r-form">
                <form class="rc-form" action="repair" method="post" id="repairType">
                    <input type="hidden" name="command" value="complete_repair">
                    <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairId%>">
                    <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=RepairStatus.COMPLETED%>">
                    <input type="hidden" name="<%=REPAIR_NUMBER%>" value="<%=repairNumber%>">
                    <%--<input type="hidden" name="page" value="<%=pageNumber%>">--%>

                    <div class="f-input">
                        <div class="form-el">Ремонта No.<%=REPAIR_NUMBER%></div>
                    </div>

                    <div class="f-input">
                        <label class="form-el">Выполненная работа:</label>
                        <select class="f-form " name="id" size="0">
                            <%for (RepairTypeDTO repairType : repairTypes) { %>
                            <option value="<%=repairType.getId()%>"><%=repairType.getName()%></option>
                            <% } %>
                        </select>
                    </div>
                </form>

                <div class="f-input">
                    <div class="form-el">Справочник типов:</div>
                </div>

                <div class="f-input">
                    <table id="order_table">
                        <tr>
                            <th>Код ремонта</th>
                            <th>Уровень ремонта</th>
                            <th>Описание</th>
                        </tr>
                        <%     for (RepairTypeDTO repairType: repairTypes) {
                        %>
                        <tr id="data_id" class="spare_part_input">
                            <td class="order-td">
                                <div><%=repairType.getCode()%></div>
                            </td>
                            <td class="order-td">
                                <div><%=repairType.getLevel()%></div>
                            </td>
                            <td class="order-td">
                                <div><%=repairType.getName()%></div>
                            </td>
                        </tr>
                        <% } %>
                    </table>
                </div>

            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Подтвердить" form="repairType"/>
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>

            <form action="repair" method="post" id="cancel">
                <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairId%>">
                <input type="hidden" name="command" value="show_confirmed_repair">
            </form>

        </div>

    </div>
</section>
</body>
