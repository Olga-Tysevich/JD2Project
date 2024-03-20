package it.academy.entities.service_center;

import it.academy.entities.device.components.Brand;
import it.academy.entities.service_center.components.BankAccount;
import it.academy.entities.service_center.components.Country;
import it.academy.entities.service_center.components.Requisites;
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
@Table(name = "service_centers")
public class ServiceCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fullName", column = @Column(name = "full_name")),
            @AttributeOverride(name = "actualAddress", column = @Column(name = "actual_address")),
            @AttributeOverride(name = "legalAddress", column = @Column(name = "legal_address")),
            @AttributeOverride(name = "taxpayerNumber", column = @Column(name = "taxpayer_number")),
            @AttributeOverride(name = "registrationNumber", column = @Column(name = "registration_number"))
    })
    private Requisites requisites;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "bankAccount", column = @Column(name = "bank_account")),
            @AttributeOverride(name = "bankCode", column = @Column(name = "bank_code")),
            @AttributeOverride(name = "bankName", column = @Column(name = "bank_name")),
            @AttributeOverride(name = "bankAddress", column = @Column(name = "bank_address"))
    })
    private BankAccount bankAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "service_centers_brands",
            joinColumns = {@JoinColumn(name = "service_center_id")},
            inverseJoinColumns = {@JoinColumn(name = "brand_id")})
    private Set<Brand> brands = new HashSet<>();

}
