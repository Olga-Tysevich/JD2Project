package it.academy.entities.device.components;

import it.academy.entities.service_center.ServiceCenter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "brands", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column(name = "active")
    private Boolean isActive;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Setter(AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "brands", fetch = FetchType.LAZY)
    private Set<ServiceCenter> services = new HashSet<>();


    public void addServiceCenter(ServiceCenter serviceCenter) {
        if (serviceCenter != null) {
            services.add(serviceCenter);
        }
    }

    public void removeServiceCenter(ServiceCenter serviceCenter) {
        if (serviceCenter != null) {
            services.remove(serviceCenter);
        }
    }

}
