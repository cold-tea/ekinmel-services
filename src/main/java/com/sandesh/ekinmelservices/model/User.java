package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "USER_EMAIL_UNQ")}, name = "USERS")
public class User implements Serializable {

    @Id
    @Column(name = "USERNAME", nullable = false, updatable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "ENABLED")
    private char enabled;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "MOBILE", nullable = false)
    private String mobile;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "REGISTER_DATE", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date registerDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Authority> authorities;
}
