package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.TablePage2;
import it.academy.dto.device.BrandDTO;
import it.academy.entities.device.Brand;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.device.BrandService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.impl.BrandConverter;
import it.academy.utils.dao.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class BrandServiceImpl implements BrandService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final BrandDAO brandDAO = new BrandDAOImpl(transactionManger);

    @Override
    public BrandDTO create(BrandDTO brandDTO) {
        Brand brand = BrandConverter.convertToEntity(brandDTO);
        try {
            checkBrandName(brand.getName());
            Supplier<Brand> create = () -> {
                checkBrand(ID_FOR_CHECK, brand.getName());
                Brand result = brandDAO.create(brand);
                log.info(OBJECT_CREATED_PATTERN, result);
                return result;
            };
            transactionManger.execute(create);
        } catch (ValidationException | ObjectAlreadyExist e) {
            brandDTO.setErrorMessage(e.getMessage());
        }
        return brandDTO;
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) {
        Brand brand = BrandConverter.convertToEntity(brandDTO);
        try {
            checkBrandName(brand.getName());
            Supplier<Brand> create = () -> {
                checkBrand(brand.getId(), brand.getName());
                Brand result = brandDAO.update(brand);
                log.info(OBJECT_UPDATED_PATTERN, result);
                return result;
            };
            transactionManger.execute(create);
        } catch (ValidationException | ObjectAlreadyExist e) {
            brandDTO.setErrorMessage(e.getMessage());
        }
        return brandDTO;
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> brandDAO.delete(id));
    }

    @Override
    public BrandDTO find(long id) {
        Brand brand = transactionManger.execute(() -> {
            Brand result = brandDAO.find(id);
            if (result == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, Brand.class);
                throw new ObjectNotFound();
            }
            return result;
        });
        return BrandConverter.convertToDTO(brand);
    }

    @Override
    public List<BrandDTO> findAll() {
        List<Brand> brands = transactionManger.execute(brandDAO::findAll);
        return BrandConverter.convertToDTOList(brands);
    }

    @Override
    public TablePage2<BrandDTO> findForPage(int pageNumber) {
        Supplier<TablePage2<BrandDTO>> find = () -> {
            long numberOfEntries = brandDAO.getNumberOfEntries();
            List<Brand> brands = brandDAO.findForPage(pageNumber, LIST_SIZE);
            List<BrandDTO> listDTO = BrandConverter.convertToDTOList(brands);
            return new TablePage2<>(listDTO, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<BrandDTO> findForPageByFilter(int pageNumber, String filter, String input) {
        Supplier<TablePage2<BrandDTO>> find = () -> {
            long numberOfEntries = brandDAO.getNumberOfEntriesByFilter(filter, input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Brand> brands = brandDAO.findForPageByAnyMatch(pageNumberForSearch, LIST_SIZE, filter, input);
            List<BrandDTO> listDTO = BrandConverter.convertToDTOList(brands);
            return new TablePage2<>(listDTO, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    private void checkBrandName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new ValidationException(NAME_IS_EMPTY);
        }
    }

    private void checkBrand(long id, String name) {
        boolean isExist = brandDAO.checkIfExist(id, name);
        if (isExist) {
            throw new ObjectAlreadyExist(BRAND_ALREADY_EXIST);
        }
    }

}