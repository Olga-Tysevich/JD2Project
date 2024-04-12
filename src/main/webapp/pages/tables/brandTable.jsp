<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.ListForPage" %>
<%@ page import="it.academy.dto.device.req.BrandDTO" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<BrandDTO> data = (ListForPage<BrandDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<BrandDTO> list = data.getList();
        %>

        <table>
            <tr>
                <th>Тип устройства</th>
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
                    <form action="repair" method="post" >
                        <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_BRAND%>">
                        <input type="hidden" name="<%=BRAND_ID%>" value="<%=brand.getId()%>">
                        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=brand.getIsActive()%>">
                        <input class="choose-button order-btn" type="submit" value="Изменить" >
                    </form>
                </td>
            </tr>
            <% }%>
        </table>

        <div class="add-form">
            <form action="main" method="post" id="addBrand">
                <input type="hidden" name="<%=COMMAND%>" value="<%=ADD_BRAND%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

                <div class="f-input">
                    <label class="form-el">Описание</label>
                    <input class="f-form" type="text" name="<%=BRAND_NAME%>" value="">
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Добавить" form="addBrand"/>
                </div>
            </form>
        </div>
    </div>
</section>