package it.academy.dto.login.req;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginDTO implements Serializable {

    private String email;

    private String password;

}