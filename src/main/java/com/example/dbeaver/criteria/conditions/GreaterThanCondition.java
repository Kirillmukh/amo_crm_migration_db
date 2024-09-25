package com.example.dbeaver.criteria.conditions;

import com.example.dbeaver.criteria.Condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GreaterThanCondition<T, V extends Comparable<V>> implements Condition<T> {
    private final String fieldName;
    private final V value;

    public GreaterThanCondition(String fieldName, V value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
        return cb.greaterThan(root.get(fieldName), value);
    }
}