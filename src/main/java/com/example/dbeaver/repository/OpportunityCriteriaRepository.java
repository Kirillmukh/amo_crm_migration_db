package com.example.dbeaver.repository;

import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.CriteriaHelper;
import com.example.dbeaver.entity.account.Activity;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.opportunity.Opportunity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class OpportunityCriteriaRepository extends CriteriaRepository<Opportunity, String> {
    @PersistenceContext
    private EntityManager em;
    private final CriteriaHelper criteriaHelper;

    public OpportunityCriteriaRepository(Class<Opportunity> entityClass, CriteriaHelper criteriaHelper) {
        super(entityClass);
        this.criteriaHelper = criteriaHelper;
    }
    public List<Contact> getContactsByCompany(Criteria<Contact> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> root = cq.from(Contact.class);
        root.join("account");

        criteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<Contact> query = em.createQuery(cq);

        criteriaHelper.configureTypedQuery(query, criteria);

        return query.getResultList();
    }

    public List<Opportunity> getOpportunitiesByCompany(Criteria<Opportunity> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Opportunity> cq = cb.createQuery(Opportunity.class);
        Root<Opportunity> root = cq.from(Opportunity.class);
        root.join("account", JoinType.INNER);
        root.join("opportunityStage", JoinType.INNER);

        criteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<Opportunity> query = em.createQuery(cq);

        criteriaHelper.configureTypedQuery(query, criteria);

        return query.getResultList();
    }

    public List<Activity> getActivitiesByCompany(Criteria<Activity> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Activity> cq = cb.createQuery(Activity.class);
        Root<Activity> root = cq.from(Activity.class);
        root.join("account");
        root.join("activityStatus");

        criteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<Activity> query = em.createQuery(cq);

        criteriaHelper.configureTypedQuery(query, criteria);
        return query.getResultList();
    }
}
