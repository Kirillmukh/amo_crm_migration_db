package com.example.dbeaver.repository.queries;

import com.example.dbeaver.criteria.Condition;
import com.example.dbeaver.criteria.Criteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@Repository
@RequiredArgsConstructor
public class CriteriaRepository<T> {
    @PersistenceContext
    protected EntityManager em;
    private final Class<T> entityClass;
    void method() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        TypedQuery<T> realQuery = em.createQuery(cq);

        realQuery.getResultList();


    }

    public List<T> findAll(Criteria<T> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<T> query = em.createQuery(cq);

        configureTypedQuery(query, criteria);
        return query.getResultList();
    }
    private void configureCriteriaQuery(Root<T> root, CriteriaBuilder cb, CriteriaQuery<T> cq, Criteria<T> criteria) {
        if (criteria == null) return;
        cq.distinct(criteria.isDistinct());

        List<Predicate> predicateList = new ArrayList<>(criteria.getConditions().size());
        for (Condition<T> condition : criteria.getConditions()) {
            predicateList.add(condition.toPredicate(root, cb));
        }
        if (!predicateList.isEmpty()) {
            cq.where(predicateList.toArray(new Predicate[0]));
        }

        List<Order> orderList = new ArrayList<>(criteria.getOrders().size());
        for (com.example.dbeaver.criteria.Order<T> order : criteria.getOrders()) {
            orderList.add(order.toOrder(root, cb));
        }
        if (!orderList.isEmpty()) {
            cq.orderBy(orderList.toArray(new Order[0]));
        }
    }
    private void configureTypedQuery(TypedQuery<T> query, Criteria<T> criteria) {
        if (criteria == null) return;
        if (criteria.getLimit() > 0) {
            query.setMaxResults(criteria.getLimit());
        }
        query.setFirstResult(criteria.getOffset());
    }

}
