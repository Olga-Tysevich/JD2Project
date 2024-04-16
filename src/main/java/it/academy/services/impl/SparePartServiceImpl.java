package it.academy.services.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.dao.SparePartDAO;
import it.academy.dao.impl.DeviceTypeDAOImpl;
import it.academy.dao.impl.SparePartDAOImpl;
import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.dto.resp.SparePartDTO;
import it.academy.entities.DeviceType;
import it.academy.entities.SparePart;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.SparePartService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.DeviceTypeConverter;
import it.academy.utils.converters.SparePartConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class SparePartServiceImpl implements SparePartService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl(transactionManger);

    @Override
    public void createSparePart(ChangeSparePartDTO changeSparePartDTO) {
        changeSparePart(changeSparePartDTO, sparePartDAO::create);
    }

    @Override
    public void updateSparePart(ChangeSparePartDTO changeSparePartDTO) {
        changeSparePart(changeSparePartDTO, sparePartDAO::update);
    }

    private void changeSparePart(ChangeSparePartDTO changeSparePartDTO, Consumer<SparePart> method) {
        ServiceHelper.checkCurrentAccount(changeSparePartDTO.getCurrentAccount());

        SparePart result = SparePartConverter.convertToEntity(changeSparePartDTO);
        transactionManger.beginTransaction();

        SparePart temp = sparePartDAO.findByUniqueParameter(SPARE_PART_NAME, changeSparePartDTO.getName());
        if (temp != null && !temp.getId().equals(changeSparePartDTO.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(SPARE_PART_ALREADY_EXIST);
        }

        if (changeSparePartDTO.getDeviceTypeIdList() != null && changeSparePartDTO.getDeviceTypeIdList().isEmpty()) {
            transactionManger.commit();
            throw new IllegalArgumentException(DEVICE_TYPE_NOT_SELECTED);
        }

        if (changeSparePartDTO.getId() == null) {
            changeSparePartDTO.setIsActive(true);
            method.accept(result);
        }
        changeSparePartDTO.getDeviceTypeIdList().forEach(dt -> {
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
            List<SparePart> spareParts = sparePartDAO.findByDeviceTypeId(id);
            return SparePartConverter.convertToDTOList(spareParts, null);
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
        List<SparePart> spareParts;
        List<DeviceTypeDTO> deviceTypes = getDeviceTypeList();

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            spareParts = sparePartDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input);
        } else {
            spareParts = sparePartDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
        }

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

        if (deviceTypes.isEmpty()) {
            throw new DeviceTypesNotFound();
        }
        return deviceTypes;
    }

}
