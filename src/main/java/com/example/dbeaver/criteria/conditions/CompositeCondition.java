package com.example.dbeaver.criteria.conditions;


import com.example.dbeaver.criteria.Condition;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;

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
    public CompositeCondition(LogicalOperator operator, List<Condition<T>> conditions) {
        this.conditions = conditions;
        this.operator = operator;
    }

    @Override
    public  Predicate toPredicate(Path<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = conditions.stream()
                .map(condition -> condition.toPredicate(root, cb))
                .toList();

        return switch (operator) {
            case AND -> cb.and(predicates.toArray(new Predicate[0]));
            case OR -> cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}