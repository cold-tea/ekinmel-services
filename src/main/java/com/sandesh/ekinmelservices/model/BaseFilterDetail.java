package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BASE_FILTER_DETAIL")
public class BaseFilterDetail implements Serializable {
    @EmbeddedId
    private BaseFilterDetailId baseFilterDetailId;
    @Column(name = "CODE")
    private String code;

    @Column(name = "ENABLED")
    private char enabled;

    @ManyToOne
    @JoinColumn(name = "BASE_NAME", referencedColumnName = "NAME")
    @JsonBackReference
    @MapsId(value = "baseName")
    private BaseFilter baseFilter;

    public BaseFilterDetail() {
    }
    public BaseFilterDetail(BaseFilterDetailId filterId, String code, char enabled) {
        this.baseFilterDetailId = filterId;
        this.code = code;
        this.enabled = enabled;
    }

    public BaseFilterDetailId getBaseFilterDetailId() {
        return baseFilterDetailId;
    }

    public void setBaseFilterDetailId(BaseFilterDetailId baseFilterDetailId) {
        this.baseFilterDetailId = baseFilterDetailId;
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

    public BaseFilter getBaseFilter() {
        return baseFilter;
    }

    public void setBaseFilter(BaseFilter baseFilter) {
        this.baseFilter = baseFilter;
    }

    @Override
    public String toString() {
        return "BaseFilterDetail{" +
                "baseFilterDetailId=" + baseFilterDetailId +
                ", code='" + code + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
