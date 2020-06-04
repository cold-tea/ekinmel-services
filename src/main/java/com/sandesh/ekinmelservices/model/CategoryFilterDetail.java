package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CATEGORY_FILTER_DETAIL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NAME", "CATEGORY_FILTER_ID"},
                name = "UNQ_CATFD_NAME_CATEGORYFILTERID")})
public class CategoryFilterDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_FILTER_DETAIL_GENERATOR")
    @SequenceGenerator(name = "CATEGORY_FILTER_DETAIL_GENERATOR", sequenceName = "SEQ_CATEGORY_FILTER_DETAIL", allocationSize = 1)
    private Integer id;

    @Column(name = "NAME")
    @NonNull
    private String name;

    @Column(name = "CODE")
    @NonNull
    private String code;

    @Column(name = "ENABLED")
    @NonNull
    private char enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_FILTER_ID", referencedColumnName = "ID")
    @JsonBackReference
    private CategoryFilter categoryFilter;
}
