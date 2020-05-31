package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_DETAIL")
public class CategoryDetail {

    @EmbeddedId
    private CategoryDetailId categoryDetailId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CODE")
    private String code;

    @Column(name = "ENABLED")
    private char enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CATG_NAME", referencedColumnName = "NAME")
    @JsonBackReference
    @MapsId(value = "catgName")
    private Category category;

    public CategoryDetail() {}
    public CategoryDetail(CategoryDetailId categoryDetailId, String description, String code, char enabled) {
        this.categoryDetailId = categoryDetailId;
        this.description = description;
        this.code = code;
        this.enabled = enabled;
    }

    public CategoryDetailId getCategoryDetailId() {
        return categoryDetailId;
    }

    public void setCategoryDetailId(CategoryDetailId categoryDetailId) {
        this.categoryDetailId = categoryDetailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getEnabled() {
        return enabled;
    }

    public void setEnabled(char enabled) {
        this.enabled = enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryDetail{" +
                "categoryDetailId=" + categoryDetailId +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
