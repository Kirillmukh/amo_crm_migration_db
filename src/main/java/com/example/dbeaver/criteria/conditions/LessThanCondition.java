package com.example.dbeaver.criteria.conditions;

import com.example.dbeaver.criteria.Condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Path;

public class LessThanCondition<T, V extends Comparable<V>> implements Condition<T> {
    private String fieldName;
    private V value;

    public LessThanCondition(String fieldName, V value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Path<T> root, CriteriaBuilder cb) {
        return cb.lessThan(root.get(fieldName), value);
    }
}