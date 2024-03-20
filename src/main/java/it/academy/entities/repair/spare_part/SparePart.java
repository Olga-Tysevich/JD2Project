package it.academy.entities.repair.spare_part;

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
    @ManyToMany(mappedBy = "spareParts", fetch = FetchType.LAZY)
    private Set<DeviceType> typeSet = new HashSet<>();

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "spareParts", fetch = FetchType.LAZY)
    private Set<SparePartsOrder> orders = new HashSet<>();
}
