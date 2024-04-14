<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.resp.ListForPage" %>
<%@ page import="it.academy.dto.resp.RepairTypeDTO" %>
<section>
    <div class="container t-container">

        <%
            ListForPage<RepairTypeDTO> data = (ListForPage<RepairTypeDTO>) request.getAttribute(LIST_FOR_PAGE);
            int pageNumber = data.getPageNumber();
            int maxPageNumber = data.getMaxPageNumber();
            List<RepairTypeDTO> list = data.getDto();
        %>


        <div class="radio-container">
            <form class="status-form" action="main" method="post" id="find_repairs">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                <input type="hidden" name="command" value="show_repair_type_table">
            </form>
        </div>


            <table>
                <tr>
                    <th>Код ремонта</th>
                    <th>Уровень ремонта</th>
                    <th>Описание</th>
                    <th class="menu">Управление</th>
                </tr>

            <% for (RepairTypeDTO repairType : list) { %>

                <tr class="t-tr">
                    <td class="code"><%=repairType.getCode()%></td>
                    <td class="level"><%=repairType.getLevel()%></td>
                    <td class="name"><%=repairType.getName()%></td>
                    <td>
                        <div class="button-table-container">
                            <form action="repair" method="post">
                                <input type="hidden" name="command" value="show_repair_type">
                                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                                <input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairType.getId()%>">
                                <input class="choose-button btn-table" type="submit" value="Изменить">
                            </form>
                        </div>
                    </td>
                </tr>
                <% } %>
            </table>

        <%if (data.getMaxPageNumber() != 1) {%>
        <div class="footer">
        <div class="button-container">
            <%if (pageNumber != FIRST_PAGE) { %>
            <form action="main" method="post">
                <input type="hidden" name="command" value="show_repair_type_table">
                <%int prevPage = pageNumber - 1;%>
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=prevPage%>">
                <input class="button light" type="submit" name="button" value="Предыдущая">
            </form>
            <% } %>

            <p><%=pageNumber%>
                из
                <%=maxPageNumber%>
            </p>


            <%if (pageNumber != maxPageNumber) { %>
            <form action="main" method="post">
                <input type="hidden" name="command" value="show_repair_type_table">
                <%int nextPage = pageNumber + 1;%>
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=nextPage%>">
                <input class="button light" type="submit" name="button" value="Следующая">
            </form>
            <% } %>

        </div>
        </div>
        <% } %>

        <div class="add-form">
            <form action="main" method="post" id="addRepairType">
                <input type="hidden" name="command" value="add_repair_type">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">

            <div class="f-input">
                <label class="form-el">Код ремонта</label>
                <input class="f-form" type="text" name="<%=REPAIR_TYPE_CODE%>" value="">
            </div>

            <div class="f-input">
                <label class="form-el">Уровень ремонта</label>
                <input class="f-form" type="text" name="<%=REPAIR_TYPE_LEVEL%>" value="">
            </div>

            <div class="f-input">
                <label class="form-el">Описание</label>
                <input class="f-form" type="text" name="<%=REPAIR_TYPE_NAME%>" value="">
            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Добавить" form="addRepairType"/>
            </div>
        </form>
        </div>

    </div>
</section>