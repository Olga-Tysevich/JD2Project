package it.academy.entities.service_center.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class BankAccount {

    private String bankAccount;

    private String bankCode;

    private String bankName;

    private String bankAddress;

}
