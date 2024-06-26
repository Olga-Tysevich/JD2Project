package it.academy.entities.device;

import it.academy.entities.spare_part.SparePart;
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
@Table(name = "models", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "brand_id", "type_id"})
})
public class Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column(name = "active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private DeviceType type;


    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Setter(AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "models", cascade = CascadeType.MERGE)
    private Set<SparePart> spareParts = new HashSet<>();

    public void addSparePart(SparePart sparePart) {
        if (sparePart != null) {
            spareParts.add(sparePart);
            sparePart.addModel(this);
        }
    }

}
