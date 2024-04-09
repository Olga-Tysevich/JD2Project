package it.academy.entities.spare_parts_order;

import it.academy.entities.device.components.DeviceType;
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
@Table(name = "spare_parts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class SparePart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Setter(AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "spareParts", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<DeviceType> typeSet = new HashSet<>();

    public void addDeviceType(DeviceType deviceType) {
        if (deviceType != null) {
            typeSet.add(deviceType);
        }
    }

    public void removeDeviceType(DeviceType deviceType) {
        if (deviceType != null) {
            typeSet.remove(deviceType);
        }
    }

}
