package com.example.dbeaver.repository.queries;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.entity.opportunity.Opportunity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountCriteriaRepository {
    @PersistenceContext
    private EntityManager em;

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
