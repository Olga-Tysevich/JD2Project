package it.academy.servlets.commands.impl.add;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.academy.dto.spare_part.CreateOrderDTO;
import it.academy.dto.spare_part.OrderItemDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.forms.GetRepair;
import it.academy.servlets.extractors.Extractor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class AddSparePartOrder implements ActionCommand {
    private SparePartOrderService sparePartOrderService = new SparePartOrderServiceImpl();
    private Gson gson = new Gson();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long repairId = Extractor.extractLongVal(req, OBJECT_ID, null);
        String orderData = Extractor.extractString(req, ORDER_DATA, null);
        String repairForm = Extractor.extractString(req, REPAIR_FORM, null);

        if (orderData != null) {
            TypeToken<List<OrderItemDTO>> typeToken = new TypeToken<>() {
            };
            Type type = typeToken.getType();
            List<OrderItemDTO> orderItems = gson.fromJson(orderData, type);
            CreateOrderDTO forCreate = CreateOrderDTO.builder()
                    .repairId(repairId)
                    .orderItems(orderItems)
                    .build();
            sparePartOrderService.createSparePartOrder(forCreate);
        }

        req.setAttribute(REPAIR_FORM, repairForm);

        return new GetRepair().execute(req, resp);

    }

}
