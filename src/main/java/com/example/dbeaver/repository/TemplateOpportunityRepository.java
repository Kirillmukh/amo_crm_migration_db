package com.example.dbeaver.repository;

import com.example.dbeaver.entity.opportunity.Opportunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TemplateOpportunityRepository extends PagingAndSortingRepository<Opportunity, String> {
    Page<Opportunity> findAll(Pageable pageable);
}
