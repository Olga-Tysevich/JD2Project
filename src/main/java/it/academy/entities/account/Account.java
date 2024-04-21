package it.academy.entities.account;

import it.academy.utils.enums.RoleEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static it.academy.utils.constants.LoggerConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "active")
    @NotNull(message = IS_ACTIVE_MUST_BE_NOT_NULL)
    private Boolean isActive;

    @Column
    @NotNull(message = EMAIL_MUST_BE_NOT_NULL)
    @Pattern(regexp = EMAIL_PATTERN, message = INVALID_EMAIL_ERROR)
    private String email;

    @Column(name = "user_name")
    @Size(min = MIN_FIELD_LENGTH, max = MAX_FIELD_LENGTH, message = INVALID_NAME_SIZE_ERROR)
    @Pattern(regexp = TEXT_PATTERN, message = INVALID_NAME_ERROR)
    private String userName;

    @Column(name = "user_surname")
    @Size(min = MIN_FIELD_LENGTH, max = MAX_FIELD_LENGTH, message = INVALID_NAME_SIZE_ERROR)
    @Pattern(regexp = TEXT_PATTERN, message = INVALID_SURNAME_ERROR)
    private String userSurname;

    @Column
    @NotNull(message = INVALID_PASSWORD_ERROR)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = INVALID_ROLE_ERROR)
    private RoleEnum role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_center_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ServiceCenter serviceCenter;

}
