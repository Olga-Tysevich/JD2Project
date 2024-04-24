<%@ page import="static it.academy.utils.constants.Constants.END_DATE" %>
<%@ page import="static it.academy.utils.constants.Constants.REPAIR_TYPE_ID" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page import="java.sql.Date" %>

    <%
        Date endDate = (Date) request.getAttribute(END_DATE);
        long repairTypeId = (long) request.getAttribute(REPAIR_TYPE_ID);
        String repairTypeCode = (String) request.getAttribute(REPAIR_TYPE_CODE);
        String repairTypeLevel = (String) request.getAttribute(REPAIR_TYPE_LEVEL);
        String repairTypeName = (String) request.getAttribute(REPAIR_TYPE_NAME);
    %>


    <div class="f-input">
        <label class="date-label" for="endDate">Дата завершения: </label>
        <div class="date-container">
            <input class="f-form" required type="date" id="endDate" name="<%=END_DATE%>"  value="<%=endDate%>"/>
        </div>
    </div>

    <div class="f-input">
        <p>
            Выполненный ремонт:
            <label for="repairCode">Код:</label>
            <input type="hidden" name="<%=REPAIR_TYPE_ID%>" value="<%=repairTypeId%>">
            <input type="text" id="repairCode" name="<%=REPAIR_TYPE_CODE%>"
                   value="<%=repairTypeCode%>"/>
            <label for="repairLevel">Уровень:</label>
            <input type="text" id="repairLevel" name="<%=REPAIR_TYPE_LEVEL%>"
                   value="<%=repairTypeLevel%>"/>
            <label for="repairName">Описание:</label>
            <input type="text" id="repairName" name="<%=REPAIR_TYPE_NAME%>"
                   value="<%=repairTypeName%>"/>
        </p>
    </div>
