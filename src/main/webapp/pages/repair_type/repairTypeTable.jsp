<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.TablePage" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            TablePage<RepairTypeDTO> data = (TablePage<RepairTypeDTO>) request.getAttribute(TABLE_PAGE);
            List<RepairTypeDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Наименование</th>
                <th>Код ремнота</th>
                <th>Уровень ремнота</th>
                <th>Активно</th>
                <% if (RoleEnum.ADMIN.equals(role)) {%>
                <th class="menu">Управление</th>
                <% } %>
            </tr>

            <% for (RepairTypeDTO repairType : list) {
            %>
            <tr class="t-tr">
                <td class="code"><%=repairType.getName()%></td>
                <td class="code"><%=repairType.getCode()%></td>
                <td class="code"><%=repairType.getLevel()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=repairType.getIsActive()%>"
                           <%if (repairType.getIsActive()) {%>checked<%}%> disabled>
                </td>

                <% if (RoleEnum.ADMIN.equals(role)) {%>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_UPDATE_REPAIR_TYPE%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairType.getId()%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=repairType.getIsActive()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>

                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_REPAIR_TYPE%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairType.getId()%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                </td>
                <% } %>
            </tr>
            <% }%>
        </table>
    </div>
</section>