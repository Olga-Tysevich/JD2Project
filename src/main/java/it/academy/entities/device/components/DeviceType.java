package it.academy.entities.device.components;

import it.academy.entities.spare_parts_order.SparePart;
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
@Table(name = "device_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class DeviceType implements Serializable {
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "device_types_spare_parts",
            joinColumns = {@JoinColumn(name = "device_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "spare_part_id")})
    private Set<SparePart> spareParts = new HashSet<>();

    public void addSparePart(SparePart sparePart) {
        if (sparePart != null) {
            spareParts.add(sparePart);
            sparePart.addDeviceType(this);
        }
    }

    public void removeSparePart(SparePart sparePart) {
        if (sparePart != null) {
            spareParts.remove(sparePart);
            sparePart.removeDeviceType(this);
        }
    }

}
