<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_SPARE_PART_FORM" %>
<%@ page import="it.academy.dto.resp.*" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.dto.spare_part.SparePartDTO" %>
<%@ page import="it.academy.dto.ListForPage" %>
<section>
    <div class="container t-container">

        <%
            AccountDTO accountDTO = ((AccountDTO) session.getAttribute(ACCOUNT));
            RoleEnum role = accountDTO.getRole();
            ListForPage<SparePartDTO> data = (ListForPage<SparePartDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            List<SparePartDTO> spareParts = data.getList();
            String tablePage = data.getPage();
        %>


        <table>
            <tr>
                <th>Наименование запчасти</th>
                <th>Активно</th>
                <% if (RoleEnum.ADMIN.equals(role)) {%>
                <th class="menu">Управление</th>
                <% } %>
            </tr>

            <%
                for (SparePartDTO sparePart : spareParts) {
            %>
            <tr class="t-tr">
                <td class="level"><%=sparePart.getName()%></td> <td class="code">
                <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=sparePart.getIsActive()%>"
                       <%if (sparePart.getIsActive()) {%>checked<%}%> disabled>
            </td>
                <td>
                    <div class="button-table-container">
                        <form action="repair" method="post">
                            <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_SPARE_PART_FORM%>">
                            <input type="hidden" name="<%=OBJECT_ID%>" value="<%=sparePart.getId()%>">
                            <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                            <input type="hidden" name="<%=PAGE%>" value="<%=tablePage%>">
                            <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=sparePart.getIsActive()%>">
                            <% if (RoleEnum.ADMIN.equals(role)) {%>
                            <input class="choose-button order-btn" type="submit" value="Изменить" >
                            <% } %>
                        </form>
                    </div>
                </td>
            </tr>
            <% }%>
        </table>
    </div>
</section>