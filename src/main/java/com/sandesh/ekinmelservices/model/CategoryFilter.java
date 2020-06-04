package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY_FILTER", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"NAME", "CATEGORY_DETAIL_ID"},
                name = "UNQ_CATF_NAME_CATEGORYDETAILID")})
public class CategoryFilter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_FILTER_GENERATOR")
    @SequenceGenerator(name = "CATEGORY_FILTER_GENERATOR", sequenceName = "SEQ_CATEGORY_FILTER", allocationSize = 1)
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
    @JsonBackReference
    @JoinColumn(name = "CATEGORY_DETAIL_ID", referencedColumnName = "ID")
    private CategoryDetail categoryDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryFilter")
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CategoryFilterDetail> categoryFilterDetails;
}

