<%@ page import="static it.academy.utils.Constants.OPEN_START_PAGE" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Сервисный центр</title>
</head>
<body>
<section>

    <%
        int pageNumber = (int) request.getAttribute(PAGE_NUMBER);
        RepairTypeDTO repairType = (RepairTypeDTO) request.getAttribute(REPAIR_TYPE);
    %>

    <form action="main" method="post" id="repair_table">
        <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
    </form>

    <div class="forms-container lf">
           <div class="lr-container">
               <form class="lr-form" action="main" method="post" id="repair_type">
                   <input type="hidden" name="command" value="change_repair_type">
                   <input type="hidden" name="<%=PAGE_NUMBER%>" value="<%=pageNumber%>">
                    <input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairType.getId()%>">

                    <div class="f-input">
                         <label class="form-el">Код ремонта</label>
                         <input class="f-form" type="text" name="<%=REPAIR_TYPE_CODE%>" value="<%=repairType.getCode()%>">
                    </div>

                    <div class="f-input">
                          <label class="form-el">Уровень ремонта</label>
                          <input class="f-form" type="text" name="<%=REPAIR_TYPE_LEVEL%>" value="<%=repairType.getLevel()%>">
                    </div>

                    <div class="f-input">
                          <label class="form-el">Описание</label>
                          <input class="f-form" type="text" name="<%=REPAIR_TYPE_NAME%>" value="<%=repairType.getName()%>">
                    </div>
            </form>

            <div class="button-container">
                <input class="button" type="submit" value="Сохранить" form="repair_type"/>
                <input class="button" type="button" value="Отмена" onclick="location.href='<%=OPEN_START_PAGE%>'"/>
            </div>
        </div>



    </div>
</section>

</body>
