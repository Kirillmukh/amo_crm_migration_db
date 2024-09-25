package com.example.dbeaver.criteria.conditions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record JoinCondition<K, T>(Root<K> leftRoot, Root<T> rightRoot, String columnParam, String foreignFieldName) {

    public Predicate toPredicate(CriteriaBuilder cb) {
        return cb.equal(leftRoot.get(columnParam), rightRoot.get(foreignFieldName).get("id"));
    }
}
