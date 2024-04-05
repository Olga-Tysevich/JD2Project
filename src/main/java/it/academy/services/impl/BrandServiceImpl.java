package it.academy.services.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.device.BrandDTO;
import it.academy.entities.device.components.Brand;
import it.academy.services.BrandService;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.dao.TransactionManger;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private BrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public BrandDTO findBrand(long id) {
        Brand brand = transactionManger.execute(() -> brandDAO.find(id));
        BrandDTO brandDTO  = BrandConverter.convertToDTO(brand);

        transactionManger.closeManager();
        return brandDTO;
    }

    @Override
    public List<BrandDTO> findBrands() {
        List<Brand> result = transactionManger.execute(brandDAO::findAll);
        List<BrandDTO> resp = BrandConverter.convertListToDTO(result);

        transactionManger.closeManager();
        return resp;
    }

}
