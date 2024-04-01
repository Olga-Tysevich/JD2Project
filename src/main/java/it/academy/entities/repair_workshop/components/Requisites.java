package it.academy.entities.repair_workshop.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Requisites implements Serializable {

    private String fullName;

    private String actualAddress;

    private String legalAddress;

    private String phone;

    private String email;

    private String taxpayerNumber;

    private String registrationNumber;
}
