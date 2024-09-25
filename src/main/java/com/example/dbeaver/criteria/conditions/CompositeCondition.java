package com.example.dbeaver.criteria.conditions;


import com.example.dbeaver.criteria.Condition;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Getter
@Setter
public class CompositeCondition<T, K extends Comparable<K>> implements Condition<T> {
    private List<Condition<T>> conditions;
    private LogicalOperator operator;

    public enum LogicalOperator {
        AND,
        OR
    }

    @SafeVarargs
    public CompositeCondition(LogicalOperator operator, Condition<T>... conditions) {
        this.conditions = List.of(conditions);
        this.operator = operator;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = conditions.stream()
                .map(condition -> condition.toPredicate(root, cb))
                .toList();

        return switch (operator) {
            case AND -> cb.and(predicates.toArray(new Predicate[0]));
            case OR -> cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}