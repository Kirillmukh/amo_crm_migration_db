package com.example.dbeaver.criteria;

import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.lead.Lead;
import com.example.dbeaver.entity.opportunity.Opportunity;
import com.example.dbeaver.repository.AccountCriteriaRepository;
import com.example.dbeaver.repository.ContactCriteriaRepository;
import com.example.dbeaver.repository.LeadCriteriaRepository;
import com.example.dbeaver.repository.OpportunityCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CriteriaConfiguration {
    private final CriteriaHelper criteriaHelper;
    @Bean
    public AccountCriteriaRepository accountCriteriaRepository() {
        return new AccountCriteriaRepository(Account.class, criteriaHelper);
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
        return new OpportunityCriteriaRepository(Opportunity.class, criteriaHelper);
    }
}
