package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.device.req.BrandDTO;
import it.academy.entities.device.components.Brand;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.device.BrandService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class BrandServiceImpl implements BrandService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private BrandDAO brandDAO = new BrandDAOImpl();

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
    public List<BrandDTO> findBrands() {
        List<Brand> repairs = transactionManger.execute(() -> brandDAO.findAll());
        return BrandConverter.convertToListDTO(repairs);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(int pageNumber) {
        return getServiceCenterList(() -> brandDAO.findForPage(pageNumber, LIST_SIZE), pageNumber,
                BrandConverter::convertToListDTO);
    }

    @Override
    public ListForPage<BrandDTO> findBrands(int pageNumber, String filter, String input) {
        return getServiceCenterList(() -> brandDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber,
                BrandConverter::convertToListDTO);
    }

    private ListForPage<BrandDTO> getServiceCenterList(Supplier<List<Brand>> method, int pageNumber,
                                                               Function<List<Brand>, List<BrandDTO>> converter) {
        List<EntityFilter> filters = FilterManager.getFiltersForServiceCenter();

        Supplier<ListForPage<BrandDTO>> find = () -> {
            List<Brand> centers = method.get();
            int maxPageNumber = (int) Math.ceil(((double) brandDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<BrandDTO> list = converter.apply(centers);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }


}