package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "IMAGE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"POST_DETAIL_ID", "IMAGE_URL"}, name = "UNQ_IMAGE_POSTDETAILID_IMAGEURL")
})
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_GENERATOR")
    @SequenceGenerator(name="IMAGE_GENERATOR", sequenceName = "SEQ_IMAGE", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "IMAGE_URL")
    private String image_url;

    @Column(name = "THUMBNAIL")
    private Character thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_DETAIL_ID", referencedColumnName = "ID")
    @JsonBackReference
    private PostDetail postDetail;
}
