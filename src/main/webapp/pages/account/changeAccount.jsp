<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.account.AccountDTO" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <%
            AccountDTO currentUser = (AccountDTO) request.getSession().getAttribute(ACCOUNT);
            AccountDTO account = (AccountDTO) request.getAttribute(ACCOUNT);
        %>


            <input type="hidden" name="<%=SERVICE_CENTER_ID%>" value="<%=account.getServiceCenterId()%>">
             <input type="hidden" name="<%=ROLE%>" value="<%=account.getRole()%>">
                    <% if (RoleEnum.ADMIN.equals(currentUser.getRole())) { %>
                    </div>
                    <div class="f-input">
                        <div class="radio-container-rp">
                            <label >Активный: </label>
                            <label >да: </label>
                            <input type="radio" name="<%=IS_ACTIVE%>" value="true" <%if (account.getIsActive()) {%>checked<%}%> />
                            <label >нет: </label>
                            <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!account.getIsActive()) {%>checked<%}%>/>
                        </div>
                    </div>
                    <% } else {%>
                        <input type="hidden" name="<%=IS_ACTIVE%>" value="<%=account.getIsActive()%>">
                    <%}%>

                    <input type="hidden" name="<%=OBJECT_ID%>" value="<%=account.getId()%>">


                <div class="f-input">
                    <label class="form-el">Сервисный центр:</label>
                    <input class="f-form" type="text" value="<%=account.getServiceCenterName()%>" disabled>
                </div>

                <div class="f-input">
                    <label class="form-el">email:</label>
                    <input class="f-form" type="text" name="<%=EMAIL%>" value="<%=account.getEmail()%>"
                           pattern="^[a-zA-Z0-9-.]+@([a-zA-Z-]+\\.)+[a-zA-Z-]{2,4}$">
                </div>

                <div class="f-input">
                    <label class="form-el">Имя пользователя:</label>
                    <input class="f-form" type="text" name="<%=USER_NAME%>" value="<%=account.getUserName()%>"
                           pattern="[A-ZА-Я][a-zа-я]{2,19}">
                </div>

                <div class="f-input">
                    <label class="form-el">Фамилия пользователя:</label>
                    <input class="f-form" type="text" name="<%=USER_SURNAME%>" value="<%=account.getUserSurname()%>"
                           pattern="[A-ZА-Я][a-zа-я]{2,19}">
                </div>

                <div class="f-input">
                    <label class="form-el">Установить новый пароль:</label>
                    <input class="f-form" type="password" placeholder="Введите пароль" name="<%=PASSWORD%>" value=""
                           pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">
                </div>

                <div class="f-input">
                    <label class="form-el">Подтвердить пароль:</label>
                    <input class="f-form" type="password" placeholder="Подтвердите пароль" name="<%=PASSWORD_CONFIRM%>" value=""
                           pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">
                </div>
