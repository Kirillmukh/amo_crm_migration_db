package com.example.dbeaver.criteria.conditions;

import com.example.dbeaver.criteria.Condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Path;
import java.util.Collection;

public class InCondition<T> implements Condition<T> {
    private String fieldName;
    private Collection<?> values;

    public InCondition(String fieldName, Collection<?> values) {
        this.fieldName = fieldName;
        this.values = values;
    }

    @Override
    public Predicate toPredicate(Path<T> root, CriteriaBuilder cb) {
        return root.get(fieldName).in(values);
    }
}