package com.example.dbeaver.repository;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.CriteriaHelper;
import com.example.dbeaver.criteria.conditions.EqualCondition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CriteriaRepository<T, K> {
    @PersistenceContext
    private EntityManager em;
    protected final Class<T> entityClass;
    @Autowired
    private CriteriaHelper criteriaHelper;
    @Transactional(readOnly = true)
    public List<T> findAll(Criteria<T> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        criteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);
        TypedQuery<T> query = em.createQuery(cq);
        criteriaHelper.configureTypedQuery(query, criteria);

        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<T> findById(K id, Criteria<T> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        criteria.addCondition(new EqualCondition<>("id", id));

        criteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);
        TypedQuery<T> query = em.createQuery(cq);
        criteriaHelper.configureTypedQuery(query, criteria);

        return Optional.ofNullable(query.getSingleResult());
    }
    @Transactional(readOnly = true)
    public long countAll(Criteria<T> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);

        criteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        cq.select(cb.count(root));

        TypedQuery<Long> query = em.createQuery(cq);
        return query.getSingleResult();
    }
}
