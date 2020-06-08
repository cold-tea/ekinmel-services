package com.sandesh.ekinmelservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PRICING")
public class Pricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRICING_GENERATOR")
    @SequenceGenerator(name="PRICING_GENERATOR", sequenceName = "SEQ_PRICING", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CONDITION")
    private String condition;

    @Column(name = "NEGOTIABLE")
    private char negotiable;
}
