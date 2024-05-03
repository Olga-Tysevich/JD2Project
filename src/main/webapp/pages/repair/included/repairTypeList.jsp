<%@ page import="it.academy.dto.repair.RepairTypeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.utils.constants.Constants.COMMAND" %>
<%@ page import="static it.academy.servlets.commands.factory.CommandEnum.COMPLETE_REPAIR" %>
<%@ page import="static it.academy.utils.constants.Constants.REPAIR_ID" %>
<%@ page import="static it.academy.utils.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div class="repair-type-container" id="repairTypesBlockId">

        <div class=" forms-container">

            <div class="cancel-button-container">
                <button class="cancel-button" id="closeRepairTypes" onclick="hideRepairTypes()">X</button>
            </div>

            <%
                long repairId = repairDTO.getId();
                List<RepairTypeDTO> repairTypes = repairFormDTO.getRepairTypes();
            %>

            <div class="form-container r-form">
                <form class="rc-form" action="repair" method="get" id="complete_repair">
                    <input type="hidden" name="<%=COMMAND%>" value="<%=COMPLETE_REPAIR%>">
                    <input type="hidden" name="<%=REPAIR_ID%>" value="<%=repairId%>">
                    <div class="f-input">
                        <label class="form-el">Тип ремонта:</label>
                        <select class="f-form " name="id" size="0">
                            <%for (RepairTypeDTO repairTypeDTO : repairTypes) {
                                String repairTypeDescription = String.format(COMPONENT_DESCRIPTION_PATTERN, repairTypeDTO.getLevel(),
                                        repairTypeDTO.getName(), repairTypeDTO.getCode());
                            %>
                            <option value="<%=repairTypeDTO.getId()%>"><%=repairTypeDescription%></option>
                            <% } %>
                        </select>
                    </div>
                </form>
            </div>

            <div class="button-container">
                <input class="button" type="submit" value="Подтвердить" form="complete_repair"/>
            </div>

        </div>

    </div>
