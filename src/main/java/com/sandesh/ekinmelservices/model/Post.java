package com.sandesh.ekinmelservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "POST")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_GENERATOR")
    @SequenceGenerator(name="POST_GENERATOR", sequenceName = "SEQ_POST", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "POST_DATE")
    private Date postDate;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;
}
