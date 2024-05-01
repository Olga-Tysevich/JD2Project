<%@ page import="static it.academy.utils.constants.Constants.ERROR" %>
<%@ page import="static it.academy.utils.constants.Constants.COMMAND" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.LOGIN" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="login-section">
    <div class="login-container">

        <form action="login" method="post" id="login">

            <div class="form-container">
                <input type="hidden" name="<%=COMMAND%>" value="<%=LOGIN%>">

                <label class="form-el" for="email">Email:</label>
                <input class="form-el" required type="email" name="email" placeholder="Введите email" value="igor@mail.ru"
                       id="email"
                       pattern="^[a-zA-Z0-9-.]+@([a-zA-Z-]+\\.)+[a-zA-Z-]{2,4}$">

                <label class="form-el" for="password">Пароль:</label>
                <input class="form-el" required type="password" name="password" placeholder="Введите пароль" value="Igor8707!"
                       id="password"
                       pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">


                <%
                    String errorMessage = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                %>

                <p class="error" id="error" style="display: none"><%=errorMessage%></p>
            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Войти" form="login"/>
            </div>

        </form>

        <form action="login" method="post" id="login2">

            <div class="form-container">
                <input type="hidden" name="<%=COMMAND%>" value="<%=LOGIN%>">

                <label class="form-el" for="email">Email:</label>
                <input class="form-el" required type="email" name="email" placeholder="Введите email" value="admin@mail.ru"
                       id="email"
                       pattern="^[a-zA-Z0-9-.]+@([a-zA-Z-]+\\.)+[a-zA-Z-]{2,4}$">

                <label class="form-el" for="password">Пароль:</label>
                <input class="form-el" required type="password" name="password" placeholder="Введите пароль" value="User8707!"
                       id="password"
                       pattern="^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$">


                <%
                    String errorMessage2 = request.getAttribute(ERROR) == null ? "" : (String) request.getAttribute(ERROR);
                %>

                <p class="error" id="error" style="display: none"><%=errorMessage2%></p>
            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Войти" form="login2"/>
            </div>

        </form>

    </div>
</section>

<script rel="script" src="${pageContext.request.contextPath}/js/ChangeFormBehavior.js"></script>
