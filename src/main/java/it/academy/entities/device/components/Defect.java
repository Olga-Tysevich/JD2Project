package it.academy.entities.device.components;

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
@Table(name = "defects")
public class Defect implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String description;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Setter(AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "defects", fetch = FetchType.LAZY)
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
