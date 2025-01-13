package com.example.dbeaver.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;


public interface Condition<T> {
    Predicate toPredicate(Path<T> root, CriteriaBuilder criteriaBuilder);
}
