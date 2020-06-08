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
@Table(name = "DELIVERY")
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELIVERY_GENERATOR")
    @SequenceGenerator(name="DELIVERY_GENERATOR", sequenceName = "SEQ_DELIVERY", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DELIVERY")
    private char delivery;

    @Column(name = "DELIVERY_AREA")
    private char deliveryArea;

    @Column(name = "DELIVERY_PRICE")
    private double deliveryPrice;

    @Column(name = "WARRANTY")
    private char warranty;
}
