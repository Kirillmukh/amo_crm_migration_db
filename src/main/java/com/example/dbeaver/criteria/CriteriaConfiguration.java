package com.example.dbeaver.criteria;

import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.lead.Lead;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.repository.criteria.AccountCriteriaRepository;
import com.example.dbeaver.repository.criteria.ContactCriteriaRepository;
import com.example.dbeaver.repository.criteria.LeadCriteriaRepository;
import com.example.dbeaver.repository.criteria.OpportunityCriteriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriteriaConfiguration {
    @Bean
    public AccountCriteriaRepository accountCriteriaRepository() {
        return new AccountCriteriaRepository(Account.class);
    }
    @Bean
    public ContactCriteriaRepository criteriaRepository() {
        return new ContactCriteriaRepository(Contact.class);
    }
    @Bean
    public LeadCriteriaRepository leadCriteriaRepository() {
        return new LeadCriteriaRepository(Lead.class);
    }
    @Bean
    public OpportunityCriteriaRepository opportunityCriteriaRepository() {
        return new OpportunityCriteriaRepository(Opportunity.class);
    }
}
