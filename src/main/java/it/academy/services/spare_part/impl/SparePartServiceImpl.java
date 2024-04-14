package it.academy.services.spare_part.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.dao.SparePartDAO;
import it.academy.dao.impl.DeviceTypeDAOImpl;
import it.academy.dao.impl.SparePartDAOImpl;
import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.SparePartDTO;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.spare_part.SparePartService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.spare_parst.SparePartConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class SparePartServiceImpl implements SparePartService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl();

    @Override
    public void createSparePart(ChangeSparePartDTO partDTO) {
        changeSparePart(partDTO, sparePartDAO::create);
    }

    @Override
    public void updateSparePart(ChangeSparePartDTO partDTO) {
        changeSparePart(partDTO, sparePartDAO::update);
    }

    private void changeSparePart(ChangeSparePartDTO partDTO, Consumer<SparePart> method) {
        ServiceHelper.checkCurrentAccount(partDTO.getCurrentAccount());

        SparePart result = SparePartConverter.convertToEntity(partDTO);
        System.out.println("sp result " + result);
        transactionManger.beginTransaction();

        SparePart temp = sparePartDAO.findByUniqueParameter(SPARE_PART_NAME, partDTO.getName());
        if (temp != null && !temp.getId().equals(partDTO.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(SPARE_PART_ALREADY_EXIST);
        }

        if (partDTO.getDeviceTypeIdList() != null && partDTO.getDeviceTypeIdList().isEmpty()) {
            transactionManger.commit();
            throw new IllegalArgumentException(DEVICE_TYPE_NOT_SELECTED);
        }

        if (partDTO.getId() == null) {
            method.accept(result);
        }
        partDTO.getDeviceTypeIdList().forEach(dt -> {
            DeviceType deviceType = deviceTypeDAO.find(dt);
            deviceType.addSparePart(result);
            deviceTypeDAO.update(deviceType);
        });

        sparePartDAO.update(result);
        transactionManger.commit();
    }


    @Override
    public List<SparePartDTO> findSparePartsByDeviceTypeId(AccountDTO accountDTO, long id) {
        Supplier<List<SparePartDTO>> find = () -> {
            List<SparePart> sparePart = sparePartDAO.findByDeviceTypeId(id);
//            return SparePartConverter.convertToDTOList(sparePart);
            return null;
        };

        return transactionManger.execute(find);
    }

    @Override
    public SparePartDTO findSparePart(long id) {
        Supplier<SparePartDTO> find = () -> {
            SparePart sparePart = sparePartDAO.find(id);
            List<DeviceTypeDTO> deviceTypes = getDeviceTypeList();
            return SparePartConverter.convertToDTO(sparePart, deviceTypes);
        };
        return transactionManger.execute(find);
    }


    @Override
    public ListForPage<SparePartDTO> findSpareParts(AccountDTO accountDTO, int pageNumber) {
        return findSpareParts(accountDTO, pageNumber, null, null);
    }

    @Override
    public ListForPage<SparePartDTO> findSpareParts(AccountDTO accountDTO, int pageNumber, String filter, String input) {
        transactionManger.beginTransaction();
        System.out.println("in spare part service page number " + pageNumber);
        List<SparePart> spareParts;
        List<DeviceTypeDTO> deviceTypes = getDeviceTypeList();

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            spareParts = sparePartDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input);
        } else {
            spareParts = sparePartDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
        }
        System.out.println("spare part " + spareParts);

        if (spareParts.isEmpty()) {
            SparePart emptySparePart = SparePart.builder()
                    .name(DEFAULT_VALUE)
                    .isActive(true)
                    .typeSet(new HashSet<>())
                    .build();
            spareParts.add(emptySparePart);
        }


        ListForPage<SparePartDTO> sparePartsForTable = ServiceHelper.getList(sparePartDAO,
                () -> spareParts,
                pageNumber,
                (l) -> SparePartConverter.convertToDTOList(spareParts, deviceTypes),
                FilterManager::getFiltersForSparePart);
        int maxPageNumber = sparePartsForTable.getMaxPageNumber() == 0 ?
                FIRST_PAGE : sparePartsForTable.getMaxPageNumber();
        sparePartsForTable.setMaxPageNumber(maxPageNumber);
        transactionManger.commit();
        return sparePartsForTable;
    }

    private List<DeviceTypeDTO> getDeviceTypeList() {
        List<DeviceTypeDTO> deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findActiveObjects(true));
        System.out.println("deviceTypes " + deviceTypes);

        if (deviceTypes.isEmpty()) {
            throw new DeviceTypesNotFound();
        }
        return deviceTypes;
    }

}
