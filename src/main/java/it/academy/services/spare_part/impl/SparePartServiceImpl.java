package it.academy.services.spare_part.impl;

import it.academy.dao.DeviceTypeDAO;
import it.academy.dao.SparePartDAO;
import it.academy.dao.impl.DeviceTypeDAOImpl;
import it.academy.dao.impl.SparePartDAOImpl;
import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.req.SparePartDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.SparePartForTableDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.exceptions.model.DeviceTypesNotFound;
import it.academy.services.spare_part.SparePartService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.spare_parst.SparePartConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.LIST_SIZE;

public class SparePartServiceImpl implements SparePartService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private final SparePartDAO sparePartDAO = new SparePartDAOImpl();

    @Override
    public void addSparePart(SparePartDTO partDTO) {
        SparePart sparePart = SparePartConverter.convertDTOToEntity(partDTO);
        transactionManger.execute(() -> {
            SparePart sparePartAfterSave = sparePartDAO.create(sparePart);
//            partDTO.getRelatedDeviceTypes().forEach(dt -> {
//                DeviceType deviceType = deviceTypeDAO.find(dt.getId());
//                deviceType.addSparePart(sparePartAfterSave);
//                deviceTypeDAO.update(deviceType);
//            });
            return sparePartAfterSave;
        });
    }


    @Override
    public List<SparePartForTableDTO> findSparePartsByDeviceTypeId(AccountDTO accountDTO, long id) {
        Supplier<List<SparePartForTableDTO>> find = () -> {
            List<SparePart> sparePart = sparePartDAO.findByDeviceTypeId(id);
//            return SparePartConverter.convertToDTOList(sparePart);
            return null;
        };

        return transactionManger.execute(find);
    }

    @Override
    public void changeSparePart(SparePartDTO partDTO) {
//        SparePart sparePart = SparePartConverter.convertDTOToEntity(partDTO);
//        transactionManger.execute(() -> {
//            SparePart sparePartAfterSave = sparePartDAO.update(sparePart);
//            partDTO.getRelatedDeviceTypes().forEach(dt -> {
//                DeviceType deviceType = deviceTypeDAO.find(dt.getId());
//                deviceType.addSparePart(sparePartAfterSave);
//                deviceTypeDAO.update(deviceType);
//            });
//            return sparePart;
//        });

    }

    @Override
    public SparePartDTO findSparePart(long id) {
        Supplier<SparePartDTO> find = () -> {
            SparePart sparePart = sparePartDAO.find(id);
//            SparePartDTO result = SparePartConverter.convertToDTO(sparePart);
            List<DeviceType> types = deviceTypeDAO.findAll();
            List<DeviceTypeDTO> deviceTypeDTOList = DeviceTypeConverter.convertToDTOList(types);
//            result.setAllDeviceTypes(deviceTypeDTOList);
            return null;
        };
        return transactionManger.execute(find);
    }


    @Override
    public ListForPage<SparePartForTableDTO> findSpareParts(AccountDTO accountDTO, int pageNumber) {
        return findSpareParts(accountDTO, pageNumber, null, null);
    }

    @Override
    public ListForPage<SparePartForTableDTO> findSpareParts(AccountDTO accountDTO, int pageNumber, String filter, String input) {
        transactionManger.beginTransaction();
        System.out.println("in spare part service ");
        List<SparePart> spareParts;
        List<DeviceTypeDTO> deviceTypes = DeviceTypeConverter.convertToDTOList(deviceTypeDAO.findAll());
        System.out.println("deviceTypes " + deviceTypes);

        if (deviceTypes.isEmpty()) {
            throw new DeviceTypesNotFound();
        }

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            spareParts = sparePartDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input);
        } else {
            spareParts = sparePartDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
        }
        System.out.println("spare part " + spareParts);

        ListForPage<SparePartForTableDTO> sparePartsForTable = ServiceHelper.getList(sparePartDAO,
                () -> spareParts,
                pageNumber,
                (l) -> SparePartConverter.convertToDTOList(spareParts, deviceTypes),
                FilterManager::getFiltersForSparePart);
        transactionManger.commit();
        return sparePartsForTable;
    }

}
