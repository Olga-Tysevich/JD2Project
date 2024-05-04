package it.academy.services.spare_part_order;

import it.academy.dto.TablePage2;
import it.academy.dto.spare_part.ChangeSparePartOrderDTO;
import it.academy.dto.spare_part.CreateOrderDTO;
import it.academy.dto.spare_part.SparePartOrderDTO;

import java.util.List;

public interface SparePartOrderService {

    void create(CreateOrderDTO createOrderDTO);

    void update(ChangeSparePartOrderDTO partDTO);

    void delete(long id);

    SparePartOrderDTO find(long id);

    List<SparePartOrderDTO> findAllByRepairId(long id);

    TablePage2<SparePartOrderDTO> findForPage(int pageNumber);

    TablePage2<SparePartOrderDTO> findForPageByFilter(int pageNumber, String filter, String input);

}
