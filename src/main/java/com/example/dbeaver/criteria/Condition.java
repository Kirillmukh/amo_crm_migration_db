package com.example.dbeaver.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public interface Condition<T> {
    Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);
}
