package com.example.dbeaver.repository.lead;

import com.example.dbeaver.entity.lead.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, String> {
    @Query(value = "select top(20) * from Lead", nativeQuery = true)
    List<Lead> findAll();
}
