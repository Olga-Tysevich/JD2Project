package it.academy.entities.repair.spare_parts_order;

import it.academy.entities.device.components.SparePart;
import it.academy.entities.repair.Repair;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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
    @CreationTimestamp
    private Date orderDate;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ElementCollection
    @MapKeyColumn(name = "part_id")
    @Column(name = "quantity")
    @CollectionTable(name = "spare_parts_orders",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    private Map<SparePart, Integer> spareParts = new HashMap<>();


    public void addSparePart(SparePart sparePart, int quantity) {
        if (sparePart != null && spareParts.containsKey(sparePart) && quantity != 0) {
            int newQuantity = spareParts.get(sparePart) + quantity;
            spareParts.put(sparePart, newQuantity);
        } else {
            spareParts.put(sparePart, quantity);
        }
    }

    public void removeSparePart(SparePart sparePart, int quantity) {
        if (sparePart != null && spareParts.containsKey(sparePart) && quantity != 0) {
            int newQuantity = spareParts.get(sparePart) - quantity;

            if (newQuantity != 0) {
                spareParts.put(sparePart, newQuantity);
            } else {
                spareParts.remove(sparePart);
            }

        }
    }
}
