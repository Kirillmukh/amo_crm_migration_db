package com.example.dbeaver.criteria.conditions;

import com.example.dbeaver.criteria.Condition;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class BetweenCondition<T, V extends Comparable<V>> implements Condition<T> {
    private String fieldName;
    private V lowerBound;
    private V upperBound;

    public BetweenCondition(String fieldName, V lowerBound, V upperBound) {
        this.fieldName = fieldName;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Predicate toPredicate(Path<T> root, CriteriaBuilder cb) {
        return cb.between(root.get(fieldName), lowerBound, upperBound);
    }
}
