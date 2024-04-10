package it.academy.services.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.DeviceType;
import it.academy.services.BrandService;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class BrandServiceImpl implements BrandService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private BrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public void addBrand(BrandDTO brand) {
        Brand result = BrandConverter.convertDTOToEntity(brand);
        transactionManger.execute(() -> brandDAO.create(result));
    }

    @Override
    public void updateBrand(BrandDTO brand) {
        Brand result = BrandConverter.convertDTOToEntity(brand);
        transactionManger.execute(() -> brandDAO.update(result));
    }

    @Override
    public BrandDTO findBrand(long id) {
        Brand result = transactionManger.execute(() -> brandDAO.find(id));
        return BrandConverter.convertToDTO(result);
    }

    @Override
    public List<BrandDTO> findBrand() {
        List<Brand> repairs = transactionManger.execute(() -> brandDAO.findAll());
        return BrandConverter.convertListToDTO(repairs);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(int pageNumber) {
        List<EntityFilter> filters = getFiltersForBrand();

        Supplier<ListForPage<BrandDTO>> find = () -> {
            List<Brand> repairs = brandDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) brandDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<BrandDTO> list = BrandConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = getFiltersForBrand();

        Supplier<ListForPage<BrandDTO>> find = () -> {
            List<Brand> repairs = brandDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) brandDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<BrandDTO> list = BrandConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    private List<EntityFilter> getFiltersForBrand() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        filters.add(new EntityFilter(IS_ACTIVE, IS_BLOCKED));
        return filters;
    }

}