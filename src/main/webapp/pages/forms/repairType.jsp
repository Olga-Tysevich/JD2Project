<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
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
                    <%--<input type="hidden" name="page" value="<%=pageNumber%>">--%>

                    <div class="f-input">
                        <div class="form-el">Ремонта No.<%=repairNumber%></div>
                    </div>

                <div class="f-input">
                    <table id="order_table">
                        <tr>
                            <th>Код ремонта</th>
                            <th>Уровень ремонта</th>
                            <th>Описание</th>
                            <th>Управление</th>
                        </tr>
                        <% for (RepairTypeDTO repairType: repairTypes) {
                        %>
                        <form class="rc-form" action="repair" method="post" id="repairType">
                            <input type="hidden" name="command" value="complete_repair">
                            <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairId%>">
                            <input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairType.getId()%>">
                            <input type="hidden" name="<%=REPAIR_TYPE_CODE%>" value="<%=repairType.getCode()%>">
                            <input type="hidden" name="<%=REPAIR_TYPE_LEVEL%>" value="<%=repairType.getLevel()%>">
                            <input type="hidden" name="<%=REPAIR_TYPE_NAME%>" value="<%=repairType.getName()%>">
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
                            <td class="order-td">
                                <div>
                                    <input class="choose-button btn-table lf-button" type="submit" value="Выбрать" form="repairType"/>
                                </div>
                            </td>
                        </tr>
                        <% } %>
                        </form>
                    </table>
                </div>

            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Отмена" form="cancel"/>
            </div>

            <form action="repair" method="post" id="cancel">
                <input type="hidden" name="command" value="show_confirmed_repair">
            </form>

        </div>

    </div>
</section>
</body>
