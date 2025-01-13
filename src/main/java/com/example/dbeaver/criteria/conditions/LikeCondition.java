package com.example.dbeaver.criteria.conditions;
import com.example.dbeaver.criteria.Condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Path;

public class LikeCondition<T> implements Condition<T> {
    private String fieldName;
    private String pattern;

    public LikeCondition(String fieldName, String pattern) {
        this.fieldName = fieldName;
        this.pattern = pattern;
    }

    @Override
    public Predicate toPredicate(Path<T> root, CriteriaBuilder cb) {
        return cb.like(root.get(fieldName), pattern);
    }
}