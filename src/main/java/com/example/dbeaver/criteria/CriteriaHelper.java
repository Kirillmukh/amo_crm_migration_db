package com.example.dbeaver.criteria;

import com.example.dbeaver.criteria.Condition;
import com.example.dbeaver.criteria.Criteria;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CriteriaHelper {

    public static <T> void configureCriteriaQuery(Root<T> root, CriteriaBuilder cb, CriteriaQuery<T> cq, Criteria<T> criteria) {
        if (criteria == null) return;
        cq.distinct(criteria.isDistinct());

        criteria.unionConditionWithAnd();
        List<Predicate> predicateList = new ArrayList<>(criteria.getConditions().size());
        for (Condition<T> condition : criteria.getConditions()) {
            predicateList.add(condition.toPredicate(root, cb));
        }
        if (!predicateList.isEmpty()) {
            cq.where(predicateList.toArray(new Predicate[0]));
        }

        List<jakarta.persistence.criteria.Order> orderList = new ArrayList<>(criteria.getOrders().size());
        for (com.example.dbeaver.criteria.Order<T> order : criteria.getOrders()) {
            orderList.add(order.toOrder(root, cb));
        }
        if (!orderList.isEmpty()) {
            cq.orderBy(orderList.toArray(new jakarta.persistence.criteria.Order[0]));
        }
    }
    public static <T> void configureTypedQuery(TypedQuery<T> query, Criteria<T> criteria) {
        if (criteria == null) return;
        if (criteria.getLimit() > 0) {
            query.setMaxResults(criteria.getLimit());
        }
        query.setFirstResult(criteria.getOffset());
    }

}
