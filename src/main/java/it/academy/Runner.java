package it.academy;

import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.services.account.AccountService;
import it.academy.services.account.ServiceCenterService;
import it.academy.services.account.impl.AccountServiceImpl;
import it.academy.services.account.impl.ServiceCenterServiceImpl;
import it.academy.services.device.BrandService;
import it.academy.services.device.DeviceTypeService;
import it.academy.services.device.ModelService;
import it.academy.services.device.impl.BrandServiceImpl;
import it.academy.services.device.impl.DeviceTypeServiceImpl;
import it.academy.services.device.impl.ModelServiceImpl;
import it.academy.utils.Generator;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.converters.device.DeviceTypeConverter;
import it.academy.utils.converters.account.ServiceCenterConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.constants.Constants.RANDOM;

@Slf4j
public class Runner {
    public static final int MAX_SIZE = 30;
    private static AccountService accountService = new AccountServiceImpl();
    private static ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private static BrandService brandService = new BrandServiceImpl();
    private static DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
    private static ModelService modelService = new ModelServiceImpl();

    public static void main(String[] args) {

        AccountDTO admin = accountService.findAccount(1L);
        log.info(admin.toString());

        List<ServiceCenterDTO> serviceCenters = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> ServiceCenterConverter.convertToDTO(Generator.generateServiceCenter()))
                .collect(Collectors.toList());

        for (ServiceCenterDTO serviceCenter : serviceCenters) {
            try {
                serviceCenterService.addServiceCenter(serviceCenter);
            } catch (Exception ignored) {
            }
        }

        List<CreateAccountDTO> account = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> Generator.generateAccount())
                .collect(Collectors.toList());

        List<ServiceCenterDTO> serviceCenterDTOS = serviceCenterService.findServiceCenters();

        for (CreateAccountDTO createAccountDTO : account) {
            createAccountDTO.setServiceCenterId(serviceCenterDTOS.get(RANDOM.nextInt(serviceCenterDTOS.size())).getId());
            try {
                accountService.createAccount(createAccountDTO);
            } catch (Exception ignored) {
            }
        }

        List<BrandDTO> brands = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> BrandConverter.convertToDTO(Generator.generateBrand()))
                .collect(Collectors.toList());

        brands.forEach(b -> {
            brandService.createBrand(b);
        });

        List<DeviceTypeDTO> deviceTypes = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> DeviceTypeConverter.convertToDTO(Generator.generateDeviceType()))
                .collect(Collectors.toList());

        deviceTypes.forEach(d -> deviceTypeService.createDeviceType(d));

        List<ModelDTO> modelList = modelService.findModels();
        List<BrandDTO> brandList = brandService.findBrands();

        modelList.forEach(dt -> {
            BrandDTO brand = brandList.get(RANDOM.nextInt(brandList.size()));
            ChangeModelDTO changeModelDTO = ChangeModelDTO.builder()
                    .brandId(brand.getId())
                    .deviceTypeId(dt.getId())
                    .isActive(true)
                    .name(Generator.generateModelName())
                    .build();
            modelService.createModel(changeModelDTO);
        });

    }

}
