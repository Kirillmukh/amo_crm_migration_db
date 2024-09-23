package com.example.dbeaver.repository.account;

import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.entity.account.Activity;
import com.example.dbeaver.entity.contact.Contact;
import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = "select top(20) * from Account", nativeQuery = true)
    List<Account> findAll();
    @Query("select o from Account a inner join Opportunity o on o.account = a where a.id = :accountId")
    List<Opportunity> findAccountOpportunities(@Param("accountId") String accountId);

    // Для OpportunityDTO
    @Query("select c from Account a inner join Contact c on c.account = a where a.id = :accountId")
    List<Contact> findContacts(@Param("accountId") String accountId);
    @Query("select o from Account a inner join Opportunity o on o.account = a where a.id = :accountId")
    List<Opportunity> findOpportunities(@Param("accountId") String accountId);
    @Query("select ac from Account a inner join Activity ac on ac.account = a where a.id = :accountId")
    List<Activity> findActivities(@Param("accountId") String accountId);

}
