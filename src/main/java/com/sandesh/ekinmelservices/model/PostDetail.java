package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "POST_DETAIL", uniqueConstraints = {
        @UniqueConstraint(columnNames = "DELIVERY_ID", name = "UNQ_POST_DETAIL_DELIVERYID"),
        @UniqueConstraint(columnNames = "PRICING_ID", name = "UNQ_POST_DETAIL_PRICINGID")
})
public class PostDetail implements Serializable {

    @Id
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @MapsId(value = "id")
    private Post post;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_DETAIL_ID", referencedColumnName = "ID")
    @JsonBackReference
    private CategoryDetail categoryDetail;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRICING_ID", referencedColumnName = "ID")
    private Pricing pricing;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DELIVERY_ID", referencedColumnName = "ID")
    private Delivery delivery;
}
