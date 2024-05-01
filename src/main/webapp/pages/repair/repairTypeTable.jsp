<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.TablePage" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            TablePage<RepairTypeDTO> data = (TablePage<RepairTypeDTO>) request.getAttribute(TABLE_PAGE);
            int pageNumber = data.getPageNumber();
            String tablePage = data.getPage();
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
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_REPAIR_TYPE%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairType.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=repairType.getIsActive()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>

                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_REPAIR_TYPE%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=repairType.getId()%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                </td>
                <% } %>
            </tr>
            <% }%>
        </table>

        <% if (RoleEnum.ADMIN.equals(role)) {%>
        <div class="add-form">
            <form action="main" method="post" id="addRepairType">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_REPAIR_TYPE%>">
                <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=true%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                <div class="f-input">
                    <label class="form-el">Описание</label>
                    <input class="f-form" type="text" name="<%=OBJECT_NAME%>" value="">
                </div>

                <div class="f-input">
                    <label class="form-el">Код ремонта</label>
                    <input class="f-form" type="text" name="<%=REPAIR_TYPE_CODE%>" value="">
                </div>

                <div class="f-input">
                    <label class="form-el">Уровень ремонта</label>
                    <input class="f-form" type="text" name="<%=REPAIR_TYPE_LEVEL%>" value="">
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Добавить" form="addRepairType"/>
                </div>
            </form>
        </div>
        <% }%>
    </div>
</section>