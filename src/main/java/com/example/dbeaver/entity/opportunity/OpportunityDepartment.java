package com.example.dbeaver.entity.opportunity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "opportunitydepartment", schema = "dbo")
@Getter
@Setter
public class OpportunityDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
