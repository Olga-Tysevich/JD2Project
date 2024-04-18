<%@ page import="static it.academy.utils.constants.Constants.PAGE_NUMBER" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="it.academy.dto.resp.AccountDTO" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.SHOW_ACCOUNT_TABLE" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.CHANGE_ACCOUNT" %>
<%@ page import="it.academy.utils.enums.RoleEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>
    <div class="forms-container lf">

        <%
            int pageNumber = request.getAttribute(PAGE_NUMBER) == null ? FIRST_PAGE : (int) request.getAttribute(PAGE_NUMBER);
            AccountDTO account = (AccountDTO) request.getAttribute(ACCOUNT);
        %>
        <div class="lr-container">
            <form action="main" method="post" id="account">
                <div class="f-input">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=CHANGE_ACCOUNT%>">
                    <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
                    <input type="hidden" name="<%=ROLE%>" value="<%=account.getRole()%>">
                    <% if (!RoleEnum.ADMIN.equals(account.getRole())) { %>
                    <input type="hidden" name="<%=SERVICE_CENTER_ID%>" value="<%=account.getServiceCenterId()%>">
                    <% } %>
                    <input type="hidden" name="<%=OBJECT_ID%>" value="<%=account.getId()%>">
                </div>
                <div class="f-input">
                    <div class="radio-container-rp">
                        <label >Активный: </label>
                        <label >да: </label>
                        <input type="radio" name="<%=IS_ACTIVE%>"  value="true" <%if (account.getIsActive()) {%>checked<%}%> />
                        <label >нет: </label>
                        <input type="radio" name="<%=IS_ACTIVE%>"  value="false" <%if (!account.getIsActive()) {%>checked<%}%>/>
                    </div>
                </div>

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
                    <%
                        String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                    %>
                    <p class="error" id="error" style="display: none"><%=errorMessage%></p>
                </div>

                <div class="button-container">
                    <input class="button" type="submit" value="Сохранить" form="account"/>
                    <input class="button" type="submit" value="Отмена" form="cancel"/>
                </div>

            </form>

            <form action="main" method="post" id="cancel">
                <input type="hidden" name="<%=COMMAND%>" value="<%=SHOW_ACCOUNT_TABLE%>">
                <input type="hidden" name="<%=PAGE%>" value="<%=request.getParameter(PAGE)%>">
                <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
            </form>
        </div>
    </div>
</section>
<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
</body>
