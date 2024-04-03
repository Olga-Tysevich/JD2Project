<%@ page import="static it.academy.utils.Constants.TABLE_CLASS" %>
<%@ page import="static it.academy.utils.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.req.repair.RepairDTOReq" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<section>
    <div class=" container">

        <%
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
            int maxPageNumber = request.getAttribute(MAX_PAGE) == null ? FIRST_PAGE : (int) request.getAttribute(MAX_PAGE);
            List<RepairDTOReq> list = (List<RepairDTOReq>) request.getAttribute(TABLE_FOR_PAGE);

        %>

            <table>
                <tr>
                    <th>No. заявки</th>
                    <th>Дата приема</th>
                    <th>No. заказа</th>
                    <th>Модель</th>
                    <th>Серийный номер</th>
                    <th>Заявленный дефект</th>
                    <th>Выявленный дефект</th>
                    <th>Статус ремонта</th>
                    <th class="menu">Управление</th>
                </tr>

            <% for (RepairDTOReq repair : list) { %>

                <tr>
                    <td><%=repair.getNumber()%></td>
                    <td><%=repair.getStartDate()%></td>
                    <td class="number"><%=repair.getServiceRepairNumber()%></td>
                    <td class="number"><%=repair.getDevice().getModelDescription()%></td>
                    <td><%=repair.getDevice().getSerialNumber()%></td>
                    <td><%=repair.getDefectDescription()%></td>
                    <td><%=repair.getIdentifiedDefect()%></td>
                    <td><%=repair.getStatus()%></td>
                    <td>
                        <div class="button-container">
                            <form action="list" method="post">
                                <input type="hidden" name="command" value="change_student">
                                <input type="hidden" name="id" value="<%=repair.getId()%>">
                                 <input type="hidden" name="page" value="<%=pageNumber%>">
                                <input class="button" type="submit" value="Изменить">
                            </form>
                            <form action="list" method="post">
                                <input type="hidden" name="command" value="delete_student">
                                <input type="hidden" name="id" value="<%=repair.getId()%>">
                                <input type="hidden" name="page" value="<%=pageNumber%>">
                                <input class="button" type="submit" value="Удалить">
                            </form>
                        </div>
                        </td>
                    </tr>
                <% } %>
            </table>

      <div class="footer">
          <form action="list" method="post">
              <input type="hidden" name="command" value="change_student">
              <input type="hidden" name="id" value="0">
              <input type="hidden" name="addressId" value="0">
              <input type="hidden" name="page" value="<%=pageNumber%>">
              <input class="button add" type="submit" value="Добавить">
          </form>
      </div>

        <div class="button-container">
            <form action="list" method="post">
                <input type="hidden" name="command" value="show_students">
                <%int prevPage = pageNumber - 1;%>
                <input type="hidden" name="page" value="<%=Math.max(prevPage, FIRST_PAGE)%>">
                <input class="button light" type="submit" name="button" value="Предыдущая">
            </form>

            <p><%=pageNumber%>
            </p>

            <form action="list" method="post">
                <input type="hidden" name="command" value="show_students">
                <%int nextPage = pageNumber + 1;%>
                <input type="hidden" name="page" value="<%=Math.min(nextPage, maxPageNumber)%>">
                <input class="button light" type="submit" name="button" value="Следующая">
            </form>
        </div>

    </div>
</section>