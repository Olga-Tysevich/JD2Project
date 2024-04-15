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

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class BrandServiceImpl implements BrandService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final BrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public void createBrand(BrandDTO brand) throws AccessDenied {
        changeBrand(brand, brandDAO::create);
    }

    @Override
    public void updateBrand(BrandDTO brand) throws AccessDenied {
        changeBrand(brand, brandDAO::update);
    }

    private void changeBrand(BrandDTO brand, Consumer<Brand> method) {
        ServiceHelper.checkCurrentAccount(brand.getCurrentAccount());

        Brand result = BrandConverter.convertToEntity(brand);
        transactionManger.beginTransaction();

        Brand temp = brandDAO.findByUniqueParameter(BRAND_NAME, brand.getName());

        if (temp != null && !temp.getId().equals(result.getId())) {
            transactionManger.commit();
            throw new IllegalArgumentException(BRAND_ALREADY_EXIST);
        }

        method.accept(result);

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