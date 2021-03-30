package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CATEGORY_DETAIL", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"NAME", "CATEGORY_ID"}, name = "UNQ_CATD_NAME_CATEGORYID")})
public class CategoryDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_DETAIL_GENERATOR")
    @SequenceGenerator(name = "CATEGORY_DETAIL_GENERATOR", sequenceName = "SEQ_CATEGORY_DETAIL", allocationSize = 1)
    private Integer id;

    @Column(name = "NAME")
    @NonNull
    private String name;

    @Column(name = "DESCRIPTION")
    @NonNull
    private String description;

    @Column(name = "CODE")
    @NonNull
    private String code;

    @Column(name = "ENABLED")
    @NonNull
    private char enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryDetail")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<CategoryFilter> categoryFilters;
}
