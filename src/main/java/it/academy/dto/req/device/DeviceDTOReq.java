package it.academy.dto.req.device;

import it.academy.entities.device.components.Buyer;
import it.academy.entities.device.components.Model;
import it.academy.entities.device.components.Salesman;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class DeviceDTOReq {

    private Long id;

    private Model model;

    private String serialNumber;

    private Date dateOfSale;

    private Buyer buyer;

    private Salesman salesman;
}
