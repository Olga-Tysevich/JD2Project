package it.academy.services.spare_part_order;

import it.academy.dto.TablePage;
import it.academy.dto.spare_part_order.ChangeSparePartOrderDTO;
import it.academy.dto.spare_part_order.CreateOrderDTO;
import it.academy.dto.spare_part_order.SparePartOrderDTO;
import java.util.Map;

public interface SparePartOrderService {

    void create(CreateOrderDTO createOrderDTO);

    void update(ChangeSparePartOrderDTO partDTO);

    void delete(long id);

    SparePartOrderDTO find(long id);

    TablePage<SparePartOrderDTO> findForPage(int pageNumber, Map<String, String> userInput);

}
