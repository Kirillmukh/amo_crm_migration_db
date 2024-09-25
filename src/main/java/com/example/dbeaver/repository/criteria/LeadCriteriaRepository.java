package com.example.dbeaver.repository.criteria;

import com.example.dbeaver.entity.lead.Lead;

public class LeadCriteriaRepository extends CriteriaRepository<Lead, String> {
    public LeadCriteriaRepository(Class<Lead> entityClass) {
        super(entityClass);
    }
}
