package it.academy.servlets.commands.impl.add;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.academy.dto.spare_part.CreateOrderDTO;
import it.academy.dto.spare_part.OrderItemDTO;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.services.spare_part_order.impl.SparePartOrderServiceImpl;
import it.academy.servlets.commands.ActionCommand;
import it.academy.servlets.commands.impl.get.forms.GetRepair;
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

            long repairId = Long.parseLong(req.getParameter(OBJECT_ID));
            String orderData = req.getParameter(ORDER_DATA);

            if (orderData != null) {
                TypeToken<List<OrderItemDTO>> typeToken = new TypeToken<>() {};
                Type type = typeToken.getType();
                List<OrderItemDTO> orderItems = gson.fromJson(orderData, type);
                CreateOrderDTO forCreate = CreateOrderDTO.builder()
                        .repairId(repairId)
                        .orderItems(orderItems)
                        .build();
                sparePartOrderService.createSparePartOrder(forCreate);
            }


            return new GetRepair().execute(req, resp);

    }

}
