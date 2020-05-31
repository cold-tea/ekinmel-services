package com.sandesh.ekinmelservices.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BASE_FILTER")
public class BaseFilter extends Filter implements Serializable {

    @OneToMany(mappedBy = "baseFilter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<BaseFilterDetail> baseFilterDetails;

    public List<BaseFilterDetail> getBaseFilterDetails() {
        return baseFilterDetails;
    }

    public void setBaseFilterDetails(List<BaseFilterDetail> baseFilterDetails) {
        this.baseFilterDetails = baseFilterDetails;
    }
}
