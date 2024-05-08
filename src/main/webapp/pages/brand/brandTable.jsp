<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.device.BrandDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_UPDATE_BRAND" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.*" %>
<%@ page import="it.academy.dto.TablePage2" %>
<section>
    <%@include file="../forms/errorContainer.jsp"%>
    <div class="container t-container">

        <%
            TablePage2<BrandDTO> data = (TablePage2<BrandDTO>) request.getAttribute(TABLE_PAGE);
            List<BrandDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Наименование</th>
                <th>Активно</th>
                <th class="menu">Управление</th>
            </tr>

            <% for (BrandDTO brand : list) {
            %>
            <tr class="t-tr">
                <td class="code"><%=brand.getName()%></td>
                <td class="code">
                    <input type="checkbox" name="<%=IS_ACTIVE%>" value="<%=brand.getIsActive()%>"
                           <%if (brand.getIsActive()) {%>checked<%}%> disabled>
                </td>
                <td class="code">
                    <form action="repair" method="get" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_UPDATE_BRAND%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=brand.getId()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=DELETE_BRAND%>">
                        <input type="hidden" name="<%=OBJECT_ID%>" value="<%=brand.getId()%>">
                        <input class="choose-button order-btn" type="submit" value="Удалить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

    </div>
</section>