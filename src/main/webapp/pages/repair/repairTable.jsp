<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.utils.enums.RepairStatus" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_REPAIR_TABLE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.repair.RepairDTO" %>
<%@ page import="it.academy.dto.device.DeviceDTO" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<section>
    <div class=" container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            ListForPage<RepairDTO> data = (ListForPage<RepairDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<RepairDTO> list = data.getList();
            List<RepairStatus> statuses = List.of(RepairStatus.values());
            String tablePage = data.getPage();
        %>

        <div class="radio-container">
            <form class="status-form" action="main" method="post" id="find_repairs">
                <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_REPAIR_TABLE%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <div class="radio-container"><div>Статус ремонта: </div>
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
                    <th>Сервисный центр</th>
                    <th class="date">Дата приема</th>
                    <th>No. заказа</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th>Заявленный дефект</th>
                    <th>Статус ремонта</th>
                    <% if (RoleEnum.ADMIN.equals(role)) {%>
                    <th class="menu">Управление</th>
                    <% } %>
                </tr>

            <% for (RepairDTO repair : list) {
                DeviceDTO deviceDTO = repair.getDeviceDTO();
                ModelDTO modelDTO = deviceDTO.getModel();
                String deviceDescription = String.format(DEVICE_DESCRIPTION_PATTERN, modelDTO.getBrandName(),
                        modelDTO.getDeviceTypeName(), modelDTO.getName());
            %>

                <tr>
                    <td class="date"><%=repair.getServiceCenterName()%></td>
                    <td class="date"><%=repair.getStartDate()%></td>
                    <td class="number"><%=repair.getRepairNumber()%></td>
                    <td class="number"><%=deviceDescription%></td>
                    <td><%=repair.getSerialNumber()%></td>
                    <td class="defect"><%=repair.getDefectDescription()%></td>
                    <td><%=repair.getStatus().getDescription()%></td>


                    <td>
                        <div class="button-table-container">
                            <form action="repair" method="post">
                                <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_CONFIRMED_REPAIR%>">
                                <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repair.getId()%>">
                                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                                <% if (RoleEnum.ADMIN.equals(role)) { %>
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