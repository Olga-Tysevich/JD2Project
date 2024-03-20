package it.academy.entities.repair.spare_part;

import it.academy.entities.repair.Repair;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class SparePartsOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repair_id")
    private Repair repair;

    @Column(name = "order_date", updatable = false)
    @Generated(GenerationTime.INSERT)
    private Date orderDate;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "spare_parts_orders",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "spare_part_id")})
    private Set<SparePart> spareParts = new HashSet<>();
}
