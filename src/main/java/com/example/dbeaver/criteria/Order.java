package com.example.dbeaver.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;

public class Order<T> {
    private String fieldName;
    private boolean asc = true;

    public Order(String fieldName, boolean asc) {
        this.fieldName = fieldName;
        this.asc = asc;
    }

    public Order(String fieldName) {
        this.fieldName = fieldName;
    }

    public jakarta.persistence.criteria.Order toOrder(Root<T> root, CriteriaBuilder cb) {
        if (asc) {
            return cb.asc(root.get(fieldName));
        } else {
            return cb.desc(root.get(fieldName));
        }
    }
}
