package it.academy.entities;

import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_center_id")
    private ServiceCenter serviceCenter;

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    @Enumerated(EnumType.STRING)
    private RepairCategory category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "defct_description")
    private String defectDescription;

    @Column
    private String serviceCenterRepairNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private RepairType repairType;

    @Column(name = "start_date", updatable = false)
    @CreationTimestamp
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;


    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "deleted")
    private boolean isDeleted;

}
