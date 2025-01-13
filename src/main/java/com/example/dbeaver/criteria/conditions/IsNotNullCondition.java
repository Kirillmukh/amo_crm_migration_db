package com.example.dbeaver.criteria.conditions;

import com.example.dbeaver.criteria.Condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Path;

public class IsNotNullCondition<T> implements Condition<T> {
    private String fieldName;

    public IsNotNullCondition(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Predicate toPredicate(Path<T> root, CriteriaBuilder cb) {
        return cb.isNotNull(root.get(fieldName));
    }
}
