package com.example.dbeaver.entity.opportunity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "opportunitytype", schema = "dbo")
@Getter
@Setter
public class OpportunityType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
