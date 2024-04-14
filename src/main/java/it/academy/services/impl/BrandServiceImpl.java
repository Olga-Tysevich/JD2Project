package it.academy.services.impl;

import it.academy.dao.BrandDAO;
import it.academy.dao.impl.BrandDAOImpl;
import it.academy.dto.req.BrandDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.device.components.Brand;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.BrandService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;

import static it.academy.utils.Constants.*;

public class BrandServiceImpl implements BrandService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final BrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public void createBrand(BrandDTO brand) throws AccessDenied {

        ServiceHelper.checkCurrentAccount(brand.getCurrentAccount());

        Brand result = BrandConverter.convertToEntity(brand);
        transactionManger.beginTransaction();

        if (brandDAO.findByUniqueParameter(BRAND_NAME, brand.getName()) != null) {
            transactionManger.commit();
            throw new IllegalArgumentException(BRAND_ALREADY_EXIST);
        }

        brandDAO.create(result);

        transactionManger.commit();
    }

    @Override
    public void updateBrand(BrandDTO brand) throws AccessDenied {

        ServiceHelper.checkCurrentAccount(brand.getCurrentAccount());

        Brand result = BrandConverter.convertToEntity(brand);
        transactionManger.beginTransaction();

        Brand temp = brandDAO.findByUniqueParameter(BRAND_NAME, brand.getName());

        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(BRAND_ALREADY_EXIST);
        }

        brandDAO.update(result);

        transactionManger.commit();
    }

    @Override
    public BrandDTO findBrand(long id) {
        Brand result = transactionManger.execute(() -> brandDAO.find(id));
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

        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return ServiceHelper.getList(brandDAO,
                    () -> brandDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input), pageNumber,
                    BrandConverter::convertToDTOList,
                    FilterManager::getFiltersForBrand);
        }
        return ServiceHelper.getList(brandDAO,
                () -> brandDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber,
                BrandConverter::convertToDTOList,
                FilterManager::getFiltersForBrand);
    }

}