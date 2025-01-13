package com.example.dbeaver.repository;

import com.example.dbeaver.criteria.Condition;
import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.CriteriaHelper;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.contact.Contact;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class ContactCriteriaRepository extends CriteriaRepository<Contact, String> {
    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaHelper criteriaHelper;
    public ContactCriteriaRepository(Class<Contact> entityClass, CriteriaHelper criteriaHelper) {
        super(entityClass);
        this.criteriaHelper = criteriaHelper;
    }
    @Transactional(readOnly = true)
    public List<Contact> findContactsJoinAccounts(Criteria<Contact> contactCriteria, Criteria<Account> accountCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(entityClass);
        Root<Contact> root = cq.from(entityClass);
        Join<Contact, Account> accountJoin = root.join("account");

        List<Predicate> contactPredicates = contactCriteria.getConditions().stream().map(condition -> condition.toPredicate(root, cb)).collect(Collectors.toList());
        List<Predicate> accountPredicates = accountCriteria.getConditions().stream().map(condition -> condition.toPredicate(accountJoin, cb)).toList();

        contactPredicates.addAll(accountPredicates);

        cq.select(root).where(cb.and(contactPredicates.toArray(new Predicate[0])));

        TypedQuery<Contact> query = entityManager.createQuery(cq);

        criteriaHelper.configureTypedQuery(query, contactCriteria);

        return query.getResultList();
    }
    @Transactional(readOnly = true)
    public long countContactsJoinAccounts(Criteria<Contact> contactCriteria, Criteria<Account> accountCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Contact> root = cq.from(entityClass);
        Join<Contact, Account> accountJoin = root.join("account");

        List<Predicate> predicates = joinConditions(cb, root, accountJoin, contactCriteria, accountCriteria);

        cq.select(cb.count(root)).where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(cq).getSingleResult();
    }
    private <T, V> List<Predicate> joinConditions(CriteriaBuilder cb, Path<T> root, Path<V> join,  Criteria<T> criteria1, Criteria<V> criteria2) {
        List<Predicate> predicates = toPredicate(cb, root, criteria1.getConditions());
        predicates.addAll(toPredicate(cb, join, criteria2.getConditions()));

        return predicates;
    }
    private <T> List<Predicate> toPredicate(CriteriaBuilder cb, Path<T> root, List<Condition<T>> conditions) {
        return conditions.stream().map(condition -> condition.toPredicate(root, cb)).collect(Collectors.toList());
    }
}
