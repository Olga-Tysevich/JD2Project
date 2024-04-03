<section>
    <div class=" container">

        <%
            String userEmail = ((UserDTO) session.getAttribute(USER)).getEmail();
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
            int maxPageNumber = request.getAttribute(MAX_PAGE) == null ? FIRST_PAGE : (int) request.getAttribute(MAX_PAGE);
            List<StudentDTO> list = (List<StudentDTO>) request.getAttribute(STUDENTS);
        %>

            <table>
                <tr>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Возраст</th>
                    <th>Оценка</th>
                    <th>Город</th>
                    <th>Улица</th>
                    <th>Дом</th>
                    <th class="menu">Управление</th>
                </tr>

            <% for (StudentDTO student : list) { %>

                <tr>
                    <td><%=student.getName()%></td>
                    <td><%=student.getSurname()%></td>
                    <td class="number"><%=student.getAge()%></td>
                    <td class="number"><%=student.getMark()%></td>
                    <td><%=student.getCity()%></td>
                    <td><%=student.getStreet()%></td>
                    <td class="number"><%=student.getHouseNumber()%></td>
                    <td>
                        <div class="button-container">
                            <form action="list" method="post">
                                <input type="hidden" name="command" value="change_student">
                                <input type="hidden" name="id" value="<%=student.getId()%>">
                                 <input type="hidden" name="page" value="<%=pageNumber%>">
                                <input class="button" type="submit" value="Изменить">
                            </form>
                            <form action="list" method="post">
                                <input type="hidden" name="command" value="delete_student">
                                <input type="hidden" name="id" value="<%=student.getId()%>">
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