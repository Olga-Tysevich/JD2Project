package it.academy.services.device.impl;

import it.academy.dao.ComponentDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.device.Brand;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.device.BrandService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class BrandServiceImpl implements BrandService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ComponentDAO<Brand, Long> brandDAO = new BrandDAOImpl(transactionManger);

    @Override
    public void createBrand(BrandDTO brandDTO) {
        Brand brand = BrandConverter.convertToEntity(brandDTO);
        Supplier<Brand> create = () -> {
            checkIfExist(brand);
            return brandDAO.create(brand);
        };
        transactionManger.execute(create);
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, brand);
    }

    @Override
    public void updateBrand(BrandDTO brandDTO) {
        Brand brand = BrandConverter.convertToEntity(brandDTO);
        Supplier<Brand> update = () -> {
            checkIfExist(brand);
            return brandDAO.update(brand);
        };
        transactionManger.execute(update);
        log.info(LoggerConstants.OBJECT_UPDATED_PATTERN, brand);
    }

    @Override
    public BrandDTO findBrand(long id) {
        Brand brand = transactionManger.execute(() -> {
            return brandDAO.find(id);
        });
        if (brand == null) {
            log.warn(OBJECT_NOT_FOUND_PATTERN, id, Brand.class);
            throw new ObjectNotFound(BRAND_NOT_FOUND);
        }
        return BrandConverter.convertToDTO(brand);
    }

    @Override
    public List<BrandDTO> findBrands() {
        List<Brand> brands = transactionManger.execute(brandDAO::findAll);
        if (brands.isEmpty()) {
            log.warn(OBJECTS_NOT_FOUND_PATTERN, Brand.class);
            throw new ObjectNotFound(BRANDS_NOT_FOUND);
        }
        return BrandConverter.convertToDTOList(brands);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(int pageNumber) {
        long numberOfEntries = brandDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> brandDAO.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(int pageNumber, String filter, String input) {
        long numberOfEntries = brandDAO.getNumberOfEntriesByFilter(filter, input);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> brandDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
    }

    private ListForPage<BrandDTO> find(Supplier<List<Brand>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<Brand> brands = ServiceHelper.getList(transactionManger, methodForSearch, Brand.class);
        List<EntityFilter> filters = FilterManager.getFiltersForBrand();
        List<BrandDTO> listDTO = BrandConverter.convertToDTOList(brands);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

    private void checkIfExist(Brand brand) {
        long brandId = brand.getId() != null ? brand.getId() : 0L;
        if (brandDAO.checkIfComponentExist(brandId, brand.getName())) {
            log.warn(OBJECT_ALREADY_EXIST, brand);
            transactionManger.rollback();
            throw new ObjectAlreadyExist(BRAND_ALREADY_EXIST);
        }
    }

}