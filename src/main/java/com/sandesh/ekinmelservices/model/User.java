package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "USER_EMAIL_UNQ")}, name = "USERS")
public class User implements Serializable {

    @Id
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;
    @Column(name = "ENABLED")
    private char enabled;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "REGISTER_DATE")
    @Temporal(TemporalType.DATE)
    private Date registerDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Authority> authorities;
}
