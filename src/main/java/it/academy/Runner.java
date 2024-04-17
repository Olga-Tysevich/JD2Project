package it.academy;

import it.academy.dto.req.*;
import it.academy.dto.resp.AccountDTO;
import it.academy.services.*;
import it.academy.services.admin.AdminService;
import it.academy.services.admin.impl.AdminServiceImpl;
import it.academy.services.impl.*;
import it.academy.utils.Generator;
import it.academy.utils.converters.BrandConverter;
import it.academy.utils.converters.DeviceTypeConverter;
import it.academy.utils.converters.ServiceCenterConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.constants.Constants.RANDOM;

@Slf4j
public class Runner {
    public static final int MAX_SIZE = 30;
    private static AdminService adminService = new AdminServiceImpl();
    private static ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();
    private static BrandService brandService = new BrandServiceImpl();
    private static DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
    private static ModelService modelService = new ModelServiceImpl();

    public static void main(String[] args) {

        AccountDTO admin = adminService.findAccount(1L);
        log.info(admin.toString());

        List<ServiceCenterDTO> serviceCenters = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> ServiceCenterConverter.convertToDTO(Generator.generateServiceCenter()))
                .collect(Collectors.toList());

        for (ServiceCenterDTO serviceCenter : serviceCenters) {
            serviceCenter.setCurrentAccount(admin);
            try {
                serviceCenterService.addServiceCenter(serviceCenter);
            } catch (Exception ignored) {
            }
        }

        List<CreateAccountDTO> account = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> Generator.generateAccount())
                .collect(Collectors.toList());

        List<ServiceCenterDTO> serviceCenterDTOS = serviceCenterService.findServiceCenters(admin);

        for (CreateAccountDTO createAccountDTO : account) {
//            createAccountDTO.setCurrentAccount(admin);
            createAccountDTO.setServiceCenterId(serviceCenterDTOS.get(RANDOM.nextInt(serviceCenterDTOS.size())).getId());
            try {
                adminService.createAccount(createAccountDTO);
            } catch (Exception ignored) {
            }
        }

        List<BrandDTO> brands = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> BrandConverter.convertToDTO(Generator.generateBrand()))
                .collect(Collectors.toList());

        brands.forEach(b -> {
            b.setCurrentAccount(admin);
            brandService.createBrand(b);
        });

        List<DeviceTypeDTO> deviceTypes = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> DeviceTypeConverter.convertToDTO(Generator.generateDeviceType()))
                .collect(Collectors.toList());

        deviceTypes.forEach(d -> {
            d.setCurrentAccount(admin);
            deviceTypeService.createDeviceType(d);
        });

        List<DeviceTypeDTO> deviceTypeList = deviceTypeService.findDeviceTypes(admin);
        List<BrandDTO> brandList = brandService.findBrands(admin);

        deviceTypeList.forEach(dt -> {
            BrandDTO brand = brandList.get(RANDOM.nextInt(brandList.size()));
            ChangeModelDTO changeModelDTO = ChangeModelDTO.builder()
                    .brandId(brand.getId())
                    .currentAccount(admin)
                    .deviceTypeId(dt.getId())
                    .isActive(true)
                    .name(Generator.generateModelName())
                    .build();
            modelService.createModel(changeModelDTO);
        });

    }

}
