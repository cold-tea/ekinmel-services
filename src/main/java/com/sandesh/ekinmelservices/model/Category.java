package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CATEGORY", uniqueConstraints =
        {@UniqueConstraint(columnNames={"NAME"}, name = "UNQ_CAT_NAME")})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_GENERATOR")
    @SequenceGenerator(name="CATEGORY_GENERATOR", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
    @Column(name = "ID")
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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CategoryDetail> categoryDetails;
}
