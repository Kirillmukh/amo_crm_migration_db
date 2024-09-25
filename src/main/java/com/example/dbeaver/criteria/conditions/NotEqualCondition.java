package com.example.dbeaver.criteria.conditions;

import com.example.dbeaver.criteria.Condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class NotEqualCondition<T> implements Condition<T> {
    private String fieldName;
    private Object value;

    public NotEqualCondition(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
        return cb.notEqual(root.get(fieldName), value);
    }
}