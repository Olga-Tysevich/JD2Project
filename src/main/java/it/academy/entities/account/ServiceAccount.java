package it.academy.entities.account;

import it.academy.entities.service_center.ServiceCenter;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("S")
public class ServiceAccount extends Account {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_center_id")
    private ServiceCenter serviceCenter;
}
