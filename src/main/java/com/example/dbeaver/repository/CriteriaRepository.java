package com.example.dbeaver.repository;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.EqualCondition;
import com.example.dbeaver.criteria.CriteriaHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CriteriaRepository<T, K> {
    @PersistenceContext
    private EntityManager em;
    protected final Class<T> entityClass;
    public List<T> findAll(Criteria<T> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        CriteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);
        TypedQuery<T> query = em.createQuery(cq);
        CriteriaHelper.configureTypedQuery(query, criteria);

        return query.getResultList();
    }

    public Optional<T> findById(K id, Criteria<T> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        criteria.addCondition(new EqualCondition<>("id", id));

        CriteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);
        TypedQuery<T> query = em.createQuery(cq);
        CriteriaHelper.configureTypedQuery(query, criteria);

        return Optional.ofNullable(query.getSingleResult());
    }
}
