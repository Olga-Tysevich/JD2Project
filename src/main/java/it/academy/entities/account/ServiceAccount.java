package it.academy.entities.account;

import it.academy.entities.service_center.ServiceCenter;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue("S")
public class ServiceAccount extends Account {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_center_id")
    private ServiceCenter serviceCenter;
}
