package it.academy.entities.repair;

import it.academy.entities.device.Device;
import it.academy.entities.repair.components.Defect;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.entities.repair.components.RepairType;
import it.academy.entities.service_center.ServiceCenter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "repairs")
public class Repair implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    //TODO Подумать как генерировать внутренний номер
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_center_id")
    private ServiceCenter serviceCenter;

    @Column
    private String serviceRepairNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private RepairStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private RepairCategory category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private RepairType type;

    @Column(name = "start_date", updatable = false)
    @Generated(GenerationTime.INSERT)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "defct_description")
    private String defectDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defect_id")
    private Defect identifiedDefect;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "deleted")
    private boolean isDeleted;

}
