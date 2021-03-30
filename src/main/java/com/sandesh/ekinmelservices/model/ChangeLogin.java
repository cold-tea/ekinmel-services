package com.sandesh.ekinmelservices.model;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangeLogin extends Login implements Serializable {

    private String newPassword;
    private String confirmPassword;

    public ChangeLogin(String username, String password,String newPassword, String confirmPassword) {
        super(username, password);
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
}
