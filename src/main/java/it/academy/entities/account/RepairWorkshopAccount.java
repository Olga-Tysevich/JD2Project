package it.academy.entities.account;

import it.academy.entities.repair_workshop.RepairWorkshop;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue("R")
public class RepairWorkshopAccount extends Account {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repair_workshop_id")
    private RepairWorkshop repairWorkshop;
}
