package com.example.dbeaver.repository.queries;

import com.example.dbeaver.criteria.Criteria;
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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpportunityCriteriaRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Contact> getContactsByCompany(Criteria<Contact> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> root = cq.from(Contact.class);
        root.join("account");

        CriteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<Contact> query = em.createQuery(cq);

        CriteriaHelper.configureTypedQuery(query, criteria);

        return query.getResultList();
    }

    public List<Opportunity> getSellsByCompany(Criteria<Opportunity> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Opportunity> cq = cb.createQuery(Opportunity.class);
        Root<Opportunity> root = cq.from(Opportunity.class);
        root.join("account", JoinType.INNER);
        root.join("opportunityStage", JoinType.INNER);

        CriteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<Opportunity> query = em.createQuery(cq);

        CriteriaHelper.configureTypedQuery(query, criteria);

        return query.getResultList();
    }

    public List<Activity> getActivitiesByCompany(Criteria<Activity> criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Activity> cq = cb.createQuery(Activity.class);
        Root<Activity> root = cq.from(Activity.class);
        root.join("account");
        root.join("activityStatus");

        CriteriaHelper.configureCriteriaQuery(root, cb, cq, criteria);

        TypedQuery<Activity> query = em.createQuery(cq);

        CriteriaHelper.configureTypedQuery(query, criteria);
        return query.getResultList();
    }
}
