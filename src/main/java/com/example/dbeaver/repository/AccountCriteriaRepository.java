package com.example.dbeaver.repository;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.CriteriaHelper;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.opportunity.Opportunity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class AccountCriteriaRepository extends CriteriaRepository<Account, String> {
    @PersistenceContext
    private EntityManager em;

    public AccountCriteriaRepository(Class<Account> entityClass) {
        super(entityClass);
    }

    public List<Opportunity> getOpportunityByAccount(Criteria<Opportunity> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Opportunity> cq = cb.createQuery(Opportunity.class);
        Root<Opportunity> root = cq.from(Opportunity.class);
        root.join("account");
        root.join("opportunityStage");
        root.join("event");

        CriteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<Opportunity> query = em.createQuery(cq);

        CriteriaHelper.configureTypedQuery(query, criteria);

        return query.getResultList();
    }
}
