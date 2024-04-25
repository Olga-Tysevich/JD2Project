<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.dto.device.ModelDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_MODEL" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.DELETE_MODEL" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            ListForPage<ModelDTO> data = (ListForPage<ModelDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<ModelDTO> models = data.getList();
            String tablePage = data.getPage();
        %>

        <table>
            <tr>
                <th>Название модели</th>
                <th>Бренд</th>
                <th>Тип устройства</th>
                <th>Активно</th>
                <% if (RoleEnum.ADMIN.equals(role)) {%>
                <th class="menu">Управление</th>
                <% } %>
            </tr>

            <% for (ModelDTO model : models) {
            %>
            <tr class="t-tr">
                <td class="code"><%=model.getName()%></td>
                <td class="code"><%=model.getBrandName()%></td>
                <td class="code"><%=model.getDeviceTypeName()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=model.getIsActive()%>"
                           <%if (model.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_MODEL%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=model.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=model.getIsActive()%>">
                        <% if (RoleEnum.ADMIN.equals(role)) {%>
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                        <% } %>
                    </form>

                    <% if (RoleEnum.ADMIN.equals(role)) {%>
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_MODEL%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=model.getId()%>">
                        <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                    <% } %>
                </td>
            </tr>
            <% }%>
        </table>

    </div>
</section>