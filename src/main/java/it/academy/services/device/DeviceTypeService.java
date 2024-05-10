package it.academy.services.device;

import it.academy.dto.TablePage;
import it.academy.dto.device.DeviceTypeDTO;
import java.util.List;
import java.util.Map;

public interface DeviceTypeService {

    void create(DeviceTypeDTO deviceType);

    void update(DeviceTypeDTO deviceType);

    void delete(long id);

    DeviceTypeDTO find(long id);

    List<DeviceTypeDTO> findAll();

    TablePage<DeviceTypeDTO> findForPage(int pageNumber, Map<String, String> userInput);

}
