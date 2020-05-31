package com.sandesh.ekinmelservices.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoryDetailId implements Serializable {

    private String name;
    private String catgName;

    public CategoryDetailId() {}
    public CategoryDetailId(String name, String catgName) {
        this.name = name;
        this.catgName = catgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    @Override
    public String toString() {
        return "CategoryDetailId{" +
                "name='" + name + '\'' +
                ", catgName='" + catgName + '\'' +
                '}';
    }
}
