package com.example.dbeaver.criteria.conditions;

import com.example.dbeaver.criteria.Condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GreaterThanOrEqualCondition<T, V extends Comparable<V>> implements Condition<T> {
    private String fieldName;
    private V value;

    public GreaterThanOrEqualCondition(String fieldName, V value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
        return cb.greaterThanOrEqualTo(root.get(fieldName), value);
    }
}
