package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BASE_FILTER_DETAIL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NAME", "BASE_ID"},name = "UNQ_BASED_NAME_BASEID")
})
public class BaseFilterDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BASE_FILTER_DETAIL_GENERATOR")
    @SequenceGenerator(name="BASE_FILTER_DETAIL_GENERATOR", sequenceName = "SEQ_BASE_FILTER_DETAIL", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "ENABLED")
    private char enabled;

    @ManyToOne
    @JoinColumn(name = "BASE_ID", referencedColumnName = "ID")
    @JsonBackReference
    private BaseFilter baseFilter;

}
