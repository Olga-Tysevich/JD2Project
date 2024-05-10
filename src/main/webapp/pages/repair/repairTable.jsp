<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.dto.TablePage" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.LAST_PAGE" %>
<section>
    <div class=" container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            TablePage<RepairDTO> data = (TablePage<RepairDTO>) request.getAttribute(TABLE_PAGE);
            List<RepairDTO> list = data.getList();
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
        %>
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
                                <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_UPDATE_REPAIR%>">
                                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repair.getId()%>">
                                <input type="hidden" name="<%=REPAIR_STATUS%>" value="<%=repair.getStatus()%>">
                                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
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