package it.academy.services.spare_part_order.impl;

import it.academy.dao.repair.RepairDAO;
import it.academy.dao.spare_part.OrderItemDAO;
import it.academy.dao.spare_part.SparePartDAO;
import it.academy.dao.spare_part.SparePartOrderDAO;
import it.academy.dao.repair.impl.RepairDAOImpl;
import it.academy.dao.spare_part.impl.OrderItemDAOImpl;
import it.academy.dao.spare_part.impl.SparePartDAOImpl;
import it.academy.dao.spare_part.impl.SparePartOrderDAOImpl;
import it.academy.dto.TablePage;
import it.academy.dto.spare_part_order.ChangeSparePartOrderDTO;
import it.academy.dto.spare_part_order.CreateOrderDTO;
import it.academy.dto.spare_part_order.SparePartOrderDTO;
import it.academy.entities.repair.Repair;
import it.academy.entities.spare_part.OrderItem;
import it.academy.entities.spare_part.SparePart;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.utils.PageCounter;
import it.academy.utils.enums.RepairStatus;
import it.academy.entities.spare_part.SparePartOrder;
import it.academy.services.spare_part_order.SparePartOrderService;
import it.academy.utils.converters.SparePartOrderConverter;
import it.academy.utils.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public void create(CreateOrderDTO createOrderDTO) {
        transactionManger.execute(() -> {
            Repair repair = repairDAO.find(createOrderDTO.getRepairId());

            if (repair == null) {
                log.info(OBJECT_NOT_FOUND_PATTERN, createOrderDTO.getRepairId(), Repair.class);
                throw new ObjectNotFound(REPAIR_NOT_FOUND);
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
    public void update(ChangeSparePartOrderDTO partDTO) {
        transactionManger.execute(() -> {
            SparePartOrder order = sparePartOrderDAO.find(partDTO.getId());
            order.setDepartureDate(partDTO.getDepartureDate());
            order.setDeliveryDate(partDTO.getDeliveryDate());
            return sparePartOrderDAO.update(order);
        });
    }

    @Override
    public List<SparePartOrderDTO> findAllByRepairId(long id) {
        Supplier<List<SparePartOrderDTO>> find = () -> {
            List<SparePartOrder> sparePart = sparePartOrderDAO.findByRepairId(id);
            return SparePartOrderConverter.convertToDTOList(sparePart);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage<SparePartOrderDTO> findForPage(int pageNumber) {
        Supplier<TablePage<SparePartOrderDTO>> find = () -> {
            long numberOfEntries = sparePartOrderDAO.getNumberOfEntries();
            List<SparePartOrder> orders = sparePartOrderDAO.findForPage(pageNumber, LIST_SIZE);
            List<SparePartOrderDTO> result = SparePartOrderConverter.convertToDTOList(orders);
            return new TablePage<>(result, numberOfEntries);
        };

        return transactionManger.execute(find);
    }


    @Override
    public TablePage<SparePartOrderDTO> findForPageByFilter(int pageNumber, String filter, String input) {
        Supplier<TablePage<SparePartOrderDTO>> find = () -> {
            long numberOfEntries = sparePartOrderDAO.getNumberOfEntriesByFilter(filter, input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<SparePartOrder> repairs = sparePartOrderDAO.findForPageByAnyMatch(pageNumberForSearch, LIST_SIZE, filter, input);
            List<SparePartOrderDTO> result = SparePartOrderConverter.convertToDTOList(repairs);
            return new TablePage<>(result, numberOfEntries);
        };

        return StringUtils.isBlank(input) ? findForPage(pageNumber) : transactionManger.execute(find);
    }

    @Override
    public SparePartOrderDTO find(long id) {
        return transactionManger.execute(() ->
                SparePartOrderConverter.convertToDTO(sparePartOrderDAO.find(id)));
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> sparePartOrderDAO.delete(id));
    }

}
