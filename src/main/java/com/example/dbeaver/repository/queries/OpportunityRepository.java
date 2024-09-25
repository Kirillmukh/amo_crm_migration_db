package com.example.dbeaver.repository.queries;

import com.example.dbeaver.criteria.Condition;
import com.example.dbeaver.criteria.conditions.EqualCondition;
import com.example.dbeaver.criteria.conditions.JoinCondition;
import com.example.dbeaver.dto.util.OpportunityByCompanyDTO;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.entity.opportunity.OpportunityStage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("criteriaOpportunityRepository")
public class OpportunityRepository {
    @PersistenceContext
    private EntityManager em;
    public List<OpportunityByCompanyDTO> getSellsByAccount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Opportunity> root = cq.from(Opportunity.class);
        root.join("account", JoinType.INNER);
        root.join("opportunityStage", JoinType.INNER);
        Root<Account> accountRoot = cq.from(Account.class);
        Root<OpportunityStage> stageRoot = cq.from(OpportunityStage.class);

//        Condition<Opportunity> joinAccount = new EqualCondition<>("account", "id");
//        Condition<Opportunity> joinOpportunity = new EqualCondition<>("opportunityStage", "id");
//
//        Condition<Opportunity> joinAccount = new EqualCondition<>("account", accountRoot.getModel());
//        Condition<Opportunity> joinOpportunity = new EqualCondition<>("opportunityStage", stageRoot.getModel());

        JoinCondition<Opportunity, Account> joinAccount = new JoinCondition<>(root, accountRoot, "account", "id");
        JoinCondition<Opportunity, OpportunityStage> joinOpportunity = new JoinCondition<>(root, stageRoot, "opportunityStage", "id");

//        cq.where(joinOpportunity.toPredicate(root, cb), joinAccount.toPredicate(root, cb));
        cq.where(joinOpportunity.toPredicate(cb), joinAccount.toPredicate(cb));

        Path<Object> opportunityTitlePath = root.get("title");
        Path<Object> stage = stageRoot.get("name");
        Path<Object> amount = root.get("amount");

        cq.multiselect(cb.array(opportunityTitlePath, stage, amount));

//        cq.select(cb.array(opportunityTitlePath, stage, amount));
        TypedQuery<Object[]> query = em.createQuery(cq);
        List<OpportunityByCompanyDTO> result = query.getResultList().stream().map(
                        objects ->
                                new OpportunityByCompanyDTO(
                                        (String) objects[0],
                                        (String) objects[1],
                                        (Double) objects[2]
                                ))
                .toList();
        return result;
    }
}
