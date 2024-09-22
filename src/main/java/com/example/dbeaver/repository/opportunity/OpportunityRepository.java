package com.example.dbeaver.repository.opportunity;

import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity, String> {
    @Query(value = "select top(20) * from Opportunity", nativeQuery = true)
    List<Opportunity> findAll();
}
