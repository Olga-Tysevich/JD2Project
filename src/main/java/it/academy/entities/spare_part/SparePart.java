package it.academy.entities.spare_part;

import it.academy.entities.device.Model;
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

    @Column(name = "active")
    private Boolean isActive;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Setter(AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "spareParts", cascade = CascadeType.MERGE)
    private Set<Model> models = new HashSet<>();

    public void addModel(Model model) {
        if (model != null) {
            models.add(model);
        }
    }

}
