<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page import="it.academy.dto.account.ServiceCenterDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.academy.dto.account.CreateAccountDTO" %>
<%@ page import="static it.academy.utils.constants.JSPConstant.SERVICE_CENTERS" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <%
            List<ServiceCenterDTO> serviceCenters = (List<ServiceCenterDTO>) request.getAttribute(SERVICE_CENTERS);
            CreateAccountDTO account = (CreateAccountDTO) request.getAttribute(ACCOUNT);
        %>

               <input type="hidden" name="<%=ROLE%>" value="<%=RoleEnum.SERVICE_CENTER%>">

                <div class="f-input">
                    <label class="form-el">Сервисный центр:</label>
                    <select class="f-form " name="<%=SERVICE_CENTER_ID%>" size="0">
                        <%for (ServiceCenterDTO service : serviceCenters) { %>
                        <option value="<%=service.getId()%>"><%=service.getServiceName()%></option>
                        <% } %>
                    </select>
                </div>

                <div class="f-input">
                    <label class="form-el">email:</label>
                    <input class="f-form" required type="email" name="<%=EMAIL%>" value="<%=account.getEmail()%>"
                    pattern="^[a-zA-Z0-9-.]+@([a-zA-Z-]+\\.)+[a-zA-Z-]{2,4}$">
                </div>

                <div class="f-input">
                    <label class="form-el">Имя пользователя:</label>
                    <input class="f-form" required type="text" name="<%=USER_NAME%>" value="<%=account.getUserName()%>"
                    pattern="[A-ZА-Я][a-zа-я]{2,19}">
                </div>

                <div class="f-input">
                    <label class="form-el">Фамилия пользователя:</label>
                    <input class="f-form" required type="text" name="<%=USER_SURNAME%>" value="<%=account.getUserSurname()%>"
                    pattern="[A-ZА-Я][a-zа-я]{2,19}">
                </div>

                <div class="f-input">
                    <label class="form-el">Введите пароль:</label>
                    <input class="f-form" required type="password" placeholder="Введите пароль"
                           name="<%=PASSWORD%>" value="<%=account.getPassword()%>"
                           pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">
                </div>

                <div class="f-input">
                    <label class="form-el">Введите пароль:</label>
                    <input class="f-form" required type="password" placeholder="Подтвердите пароль"
                           name="<%=PASSWORD_CONFIRM%>" value="<%=account.getConfirmPassword()%>"
                           pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">
                </div>