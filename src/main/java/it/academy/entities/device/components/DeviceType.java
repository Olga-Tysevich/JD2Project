package it.academy.entities.device.components;

import it.academy.entities.repair.components.Defect;
import it.academy.entities.repair.spare_part.SparePart;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "device_types")
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "device_types_spare_parts",
            joinColumns = {@JoinColumn(name = "device_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "spare_part_id")})
    private Set<SparePart> spareParts = new HashSet<>();

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "device_types_defects",
            joinColumns = {@JoinColumn(name = "device_tye_id")},
            inverseJoinColumns = {@JoinColumn(name = "defect_id")})
    private Set<Defect> defects = new HashSet<>();
}
