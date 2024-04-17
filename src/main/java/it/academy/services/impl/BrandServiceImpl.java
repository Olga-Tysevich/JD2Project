package it.academy.services.impl;

import it.academy.dao.BrandDAO;
import it.academy.dao.impl.BrandDAOImpl;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.Brand;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.BrandService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.BrandConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;

@Slf4j
public class BrandServiceImpl implements BrandService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);

    @Override
    public void createBrand(BrandDTO brand) throws AccessDenied {
        changeBrand(brand, brandDAO::create);
        log.info(String.format(ERROR_PATTERN, brand));
    }

    @Override
    public void updateBrand(BrandDTO brand) throws AccessDenied {
        changeBrand(brand, brandDAO::update);
        log.info(String.format(OBJECT_UPDATED_PATTERN, brand));
    }

    private void changeBrand(BrandDTO brand, Consumer<Brand> method) {
        ServiceHelper.checkCurrentAccount(brand.getCurrentAccount());

        Brand result = BrandConverter.convertToEntity(brand);
        transactionManger.beginTransaction();

        Brand temp = brandDAO.findByUniqueParameter(OBJECT_NAME, brand.getName());

        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(BRAND_ALREADY_EXIST);
        }

        try {
            method.accept(result);
        } catch (Exception e) {
            log.error(String.format(ERROR_PATTERN, e.getMessage(), result));
            throw e;
        }

        transactionManger.commit();
    }

    @Override
    public BrandDTO findBrand(long id) {
        Brand result = transactionManger.execute(() -> {
            try {
                Brand brand = brandDAO.find(id);
                log.info(String.format(OBJECT_FOUND_PATTERN, brand));
                return brand;
            } catch (Exception e) {
                log.error(String.format(ERROR_PATTERN, e.getMessage(), OBJECT_ID + id));
                throw e;
            }
        });
        return BrandConverter.convertToDTO(result);
    }

    @Override
    public List<BrandDTO> findBrands(AccountDTO accountDTO) {

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return BrandConverter.convertToDTOList(brandDAO.findActiveObjects(true));
        }

        List<Brand> repairs = transactionManger.execute(brandDAO::findAll);
        return BrandConverter.convertToDTOList(repairs);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(AccountDTO accountDTO, int pageNumber) {

        return findBrands(accountDTO, pageNumber, null, null);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(AccountDTO accountDTO, int pageNumber, String filter, String input) {

        Supplier<List<Brand>> find;
        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            find = () -> brandDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input);
        } else {
            find = () -> brandDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
        }
        return ServiceHelper.getList(brandDAO,
                find, pageNumber,
                BrandConverter::convertToDTOList,
                FilterManager::getFiltersForBrand);
    }

}