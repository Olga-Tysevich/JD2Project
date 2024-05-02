package it.academy.services.spare_part_order.impl;

import it.academy.dao.repair.RepairDAO;
import it.academy.dao.spare_part.OrderItemDAO;
import it.academy.dao.spare_part.SparePartDAO;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_part.impl.OrderItemDAOImpl;
import it.academy.dao.spare_part.impl.SparePartDAOImpl;
import it.academy.dao.spare_part.impl.SparePartOrderDAOImpl;
import it.academy.dto.spare_part.ChangeSparePartOrderDTO;
import it.academy.dto.spare_part.CreateOrderDTO;
import it.academy.dto.spare_part.SparePartOrderDTO;
import it.academy.dto.TablePage;
import it.academy.entities.repair.Repair;
import it.academy.entities.spare_part.OrderItem;
import it.academy.entities.spare_part.SparePart;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.utils.enums.RepairStatus;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.utils.Builder;
import it.academy.utils.converters.spare_part.SparePartOrderConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_NOT_FOUND_PATTERN;

@Slf4j
public class SparePartOrderServiceImpl implements SparePartOrderService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final SparePartOrderDAO sparePartOrderDAO = new SparePartOrderDAOImpl(transactionManger);
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl(transactionManger);
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImpl(transactionManger);
    private final RepairDAO repairDAO = new RepairDAOImpl(transactionManger);


    @Override
    public void createSparePartOrder(CreateOrderDTO createOrderDTO) {
        transactionManger.execute(() -> {
            Repair repair = repairDAO.find(createOrderDTO.getRepairId());

            if (repair == null) {
                log.info(OBJECT_NOT_FOUND_PATTERN, createOrderDTO.getRepairId(), Repair.class);
                throw  new ObjectNotFound(REPAIR_NOT_FOUND);
            }
            repair.setStatus(RepairStatus.WAITING_FOR_SPARE_PARTS);

            SparePartOrder orderForCreate = SparePartOrder.builder()
                    .repair(repair)
                    .build();
            SparePartOrder sparePartOrder = sparePartOrderDAO.create(orderForCreate);
            log.info(OBJECT_CREATED_PATTERN, sparePartOrder);

            Set<OrderItem> orderItems = createOrderDTO.getOrderItems().stream()
                    .map(i -> {
                        SparePart sparePart = sparePartDAO.find(i.getId());
                        return OrderItem.builder()
                                .sparePartOrder(sparePartOrder)
                                .sparePart(sparePart)
                                .quantity(i.getQuantity())
                                .build();
                    }).collect(Collectors.toSet());

            orderItems.forEach(i -> {
                OrderItem item = orderItemDAO.create(i);
                log.info(OBJECT_CREATED_PATTERN, item);
            });
            return sparePartOrder;
        });
    }


    @Override
    public void changeSparePartOrder(ChangeSparePartOrderDTO partDTO) {
        transactionManger.execute(() -> {
            SparePartOrder order = sparePartOrderDAO.find(partDTO.getId());
            order.setDepartureDate(partDTO.getDepartureDate());
            order.setDeliveryDate(partDTO.getDeliveryDate());
            return sparePartOrderDAO.update(order);
        });
    }

    @Override
    public List<SparePartOrderDTO> findSparePartOrdersByRepairId(long id) {
        Supplier<List<SparePartOrderDTO>> find = () -> {
            List<SparePartOrder> sparePart = sparePartOrderDAO.findSparePartOrdersByRepairId(id);
            return SparePartOrderConverter.convertListToDTO(sparePart);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage<SparePartOrderDTO> findSparePartOrders(int pageNumber) {
        List<EntityFilter> filters = FilterManager.getFiltersForSparePartOrder();

        Supplier<TablePage<SparePartOrderDTO>> find = () -> {
            List<SparePartOrder> repairs = sparePartOrderDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) 2) / LIST_SIZE);
            List<SparePartOrderDTO> list = SparePartOrderConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public TablePage<SparePartOrderDTO> findSparePartOrders(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = FilterManager.getFiltersForSparePartOrder();

        Supplier<TablePage<SparePartOrderDTO>> find = () -> {
            List<SparePartOrder> repairs = sparePartOrderDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) 2) / LIST_SIZE);
            List<SparePartOrderDTO> list = SparePartOrderConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public SparePartOrderDTO findSparePartOrder(long id) {
        return transactionManger.execute(() ->
                SparePartOrderConverter.convertToDTO(sparePartOrderDAO.find(id)));
    }

    @Override
    public void deleteSparePartOrder(long id) {
        transactionManger.execute(() -> sparePartOrderDAO.delete(id));
    }

}
