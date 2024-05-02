package it.academy;

import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ChangeModelDTO;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.dto.spare_part.ChangeSparePartDTO;
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
import it.academy.services.repair.RepairTypeService;
import it.academy.services.repair.impl.RepairTypeServiceImpl;
import it.academy.services.spare_part_order.SparePartService;
import it.academy.services.spare_part_order.impl.SparePartServiceImpl;
import it.academy.utils.Generator;
import it.academy.utils.converters.impl.BrandConverter;
import it.academy.utils.converters.impl.DeviceTypeConverter;
import it.academy.utils.converters.impl.RepairTypeConverter;
import it.academy.utils.converters.impl.ServiceCenterConverter;
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
    private static ServiceCenterConverter serviceCenterConverter = new ServiceCenterConverter();
    private static BrandService brandService = new BrandServiceImpl();
    private static BrandConverter brandConverter = new BrandConverter();
    private static DeviceTypeService deviceTypeService = new DeviceTypeServiceImpl();
    private static DeviceTypeConverter deviceTypeConverter = new DeviceTypeConverter();
    private static ModelService modelService = new ModelServiceImpl();
    private static RepairTypeService repairTypeService = new RepairTypeServiceImpl();
    private static RepairTypeConverter repairTypeConverter = new RepairTypeConverter();
    private static SparePartService sparePartService = new SparePartServiceImpl();

    public static void main(String[] args) {

        AccountDTO admin = accountService.findAccount(1L);
        log.info(admin.toString());

        List<ServiceCenterDTO> serviceCenters = IntStream.range(0, MAX_SIZE)
                .mapToObj(i -> serviceCenterConverter.convertToDTO(Generator.generateServiceCenter()))
                .collect(Collectors.toList());

        for (ServiceCenterDTO serviceCenter : serviceCenters) {
            try {
                serviceCenterService.createServiceCenter(serviceCenter);
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

        IntStream.range(0, MAX_SIZE)
                .forEach(i -> {
                    BrandDTO brandDTO = brandConverter.convertToDTO(Generator.generateBrand());
                    brandService.createBrand(brandDTO);
                });

        IntStream.range(0, MAX_SIZE)
                .forEach(i -> {
                    DeviceTypeDTO deviceType = deviceTypeConverter.convertToDTO(Generator.generateDeviceType());
                    deviceTypeService.createDeviceType(deviceType);
                });


        List<BrandDTO> brandList = brandService.findBrands();
        List<DeviceTypeDTO> deviceTypeList = deviceTypeService.findDeviceTypes();

        IntStream.range(0, MAX_SIZE).forEach(i -> {
            BrandDTO brand = brandList.get(RANDOM.nextInt(brandList.size()));
            DeviceTypeDTO deviceTypeDTO = deviceTypeList.get(RANDOM.nextInt(deviceTypeList.size()));
            ChangeModelDTO changeModelDTO = ChangeModelDTO.builder()
                    .brandId(brand.getId())
                    .deviceTypeId(deviceTypeDTO.getId())
                    .isActive(true)
                    .name(Generator.generateModelName())
                    .build();
            modelService.create(changeModelDTO);
        });

        IntStream.range(0, MAX_SIZE)
                .forEach(i -> {
                    RepairTypeDTO repairTypeDTO = repairTypeConverter.convertToDTO(Generator.generateRepairType());
                    repairTypeService.create(repairTypeDTO);
                });

        List<ModelDTO> modelList = modelService.findAll();

        IntStream.range(0, MAX_SIZE)
                .forEach(i -> {
                    List<Long> modelsId = modelList.subList(0, RANDOM.nextInt(modelList.size() - 1) + 1)
                            .stream()
                            .map(ModelDTO::getId)
                            .collect(Collectors.toList());
                    ChangeSparePartDTO sparePartDTO = ChangeSparePartDTO.builder()
                            .isActive(true)
                            .name(Generator.generateSparePartName())
                            .modelIdList(modelsId)
                            .build();
                    sparePartService.createSparePart(sparePartDTO);
                });

    }

}
