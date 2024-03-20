package it.academy.entities.repair.decommissioning;

import it.academy.entities.repair.Repair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "decommissioning_certificates")
public class DecommissioningCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    //TODO Подумать как генерировать номер акта
    @Column
    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repair_id")
    private Repair repair;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "decommissioning_cause_id")
    private DecommissioningCause cause;

}
