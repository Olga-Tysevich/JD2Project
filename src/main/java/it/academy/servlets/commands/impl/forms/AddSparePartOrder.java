package it.academy.servlets.commands.impl.forms;

import it.academy.dto.spare_parts.SparePartOrderDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static it.academy.utils.Constants.REPAIR_ID;

public class AddSparePartOrder extends ShowOrderSparePart {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Map<String,String[]> m = req.getParameterMap();

        m.keySet().forEach(k -> System.out.println("key " + k + " values " + Arrays.toString(m.get(k))));


        return null;
    }

}
