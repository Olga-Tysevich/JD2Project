package it.academy.entities.device.components;

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
@Table(name = "spare_parts")
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
    @ManyToMany(mappedBy = "spareParts", fetch = FetchType.LAZY)
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
