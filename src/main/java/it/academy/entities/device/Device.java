package it.academy.entities.device;

import it.academy.entities.device.embeddable.Buyer;
import it.academy.entities.device.embeddable.Salesman;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "devices", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"serial_number"})
})
public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "date_of_sale")
    private Date dateOfSale;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "buyer_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "buyer_surname")),
            @AttributeOverride(name = "phone", column = @Column(name = "buyer_phone"))
    })
    private Buyer buyer;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "salesman_name")),
            @AttributeOverride(name = "phone", column = @Column(name = "salesman_phone"))
    })
    private Salesman salesman;
}
