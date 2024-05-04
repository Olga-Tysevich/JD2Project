<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.REPAIR_TABLE_PAGE_PATH" %>
<%@ page import="it.academy.dto.TablePage2" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.OPEN_FORM" %>
<section>
    <div class=" container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            TablePage2<RepairDTO> data = (TablePage2<RepairDTO>) request.getAttribute(TABLE_PAGE);
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
            List<RepairDTO> list = data.getList();
            List<RepairStatus> statuses = List.of(RepairStatus.values());
        %>

        <div class="radio-container">
            <form class="status-form" action="main" method="post" id="find_repairs">
                <input type="hidden" name="<%=COMMAND%>" value="<%=GET_REPAIRS_BY_STATUS%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=REPAIR_TABLE_PAGE_PATH%>">
                <div class="radio-container">

                <div>Статус ремонта: </div>
                <%for (RepairStatus status: statuses) { %>
                    <div>
                    <input name="<%=REPAIR_STATUS%>" type="radio" value="<%=status%>"> <%=status.getDescription()%>
                    </div>
                <% } %>
                    <div>
                        <input name="<%=REPAIR_STATUS%>" type="radio" value="<%=ALL_REPAIRS%>"> <%=ALL_REPAIRS%>
                    </div>
                    <div>
                    <input class="choose-button" type="submit" value="Выбрать">
                    </div>
                </div>
            </form>
        </div>

            <table>
                <tr>
                    <%if (RoleEnum.ADMIN.equals(role)) {%>
                    <th>Сервисный центр</th>
                    <%}%>
                    <th>Категория ремонта</th>
                    <th class="date">Дата приема</th>
                    <th>No. заказа</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th>Заявленный дефект</th>
                    <th>Статус ремонта</th>
                    <th class="menu">Управление</th>
                </tr>

            <% for (RepairDTO repair : list) {%>

                <tr>
                    <%if (RoleEnum.ADMIN.equals(role)) {%>
                    <td><%=repair.getServiceCenterName()%></td>
                    <%}%>
                    <td><%=repair.getCategory().getDescription()%></td>
                    <td class="date"><%=repair.getStartDate()%></td>
                    <td class="number"><%=repair.getRepairNumber()%></td>
                    <td class="number"><%=repair.getDeviceDescription()%></td>
                    <td><%=repair.getSerialNumber()%></td>
                    <td class="defect"><%=repair.getDefectDescription()%></td>
                    <td><%=repair.getStatus().getDescription()%></td>


                    <td>
                        <div class="button-table-container">
                            <form action="repair" method="get">
                                <input type="hidden" name="<%=COMMAND%>" value="<%=GET_REPAIR%>">
                                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repair.getId()%>">
                                <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=repair.getStatus()%>">
                                <% if (RoleEnum.ADMIN.equals(role) || RepairStatus.REQUEST.equals(repair.getStatus())) { %>
                                    <input class="choose-button btn-table" type="submit" value="Изменить">
                                <% } else {%>
                                    <input class="choose-button btn-table" type="submit" value="Открыть">
                                <% } %>
                            </form>
                        </div>
                    </td>

                </tr>
                <% } %>
            </table>
    </div>
</section>