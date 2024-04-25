package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.device.Brand;
import it.academy.services.device.BrandService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.impl.BrandConverter;
import it.academy.utils.dao.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import static it.academy.utils.constants.Constants.*;

@Slf4j
public class BrandServiceImpl implements BrandService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);
    private final BrandConverter brandConverter = new BrandConverter();
    private final ServiceHelper<Brand, BrandDTO> brandHelper =
            new ServiceHelper<>(brandDAO, Brand.class, brandConverter, transactionManger);

    @Override
    public void createBrand(BrandDTO brandDTO) {
        brandHelper.create(brandDTO, () ->
                brandDAO.checkIfExist(ID_FOR_CHECK, brandDTO.getName()));
    }

    @Override
    public void updateBrand(BrandDTO brandDTO) {
        brandHelper.update(brandDTO, () ->
                brandDAO.checkIfExist(brandDTO.getId(), brandDTO.getName()));
    }

    @Override
    public void deleteBrand(long id) {
        brandHelper.delete(id);
    }

    @Override
    public BrandDTO findBrand(long id) {
        return brandHelper.find(id);
    }

    @Override
    public List<BrandDTO> findBrands() {
        return brandHelper.findAll();
    }

    @Override
    public ListForPage<BrandDTO> findBrands(int pageNumber, String filter, String input) {
       return brandHelper.find(pageNumber, filter, input);
    }

}