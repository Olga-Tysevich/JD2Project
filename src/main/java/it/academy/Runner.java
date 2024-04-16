package it.academy;

import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.req.ServiceCenterDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.services.AdminService;
import it.academy.services.ServiceCenterService;
import it.academy.services.impl.AdminServiceImpl;
import it.academy.services.impl.ServiceCenterServiceImpl;
import it.academy.utils.Generator;
import it.academy.utils.converters.ServiceCenterConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.academy.utils.Constants.RANDOM;

@Slf4j
public class Runner {
    public static final int MAX_SIZE = 30;
    private static AdminService adminService = new AdminServiceImpl();
    private static ServiceCenterService serviceCenterService = new ServiceCenterServiceImpl();

    public static void main(String[] args) throws EmailAlreadyRegistered, EnteredPasswordsNotMatch {

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
            createAccountDTO.setCurrentAccount(admin);
            createAccountDTO.setServiceCenterId(serviceCenterDTOS.get(RANDOM.nextInt(serviceCenterDTOS.size())).getId());
            try {
                adminService.createAccount(createAccountDTO);
            } catch (Exception ignored) {
            }
        }


    }

}
