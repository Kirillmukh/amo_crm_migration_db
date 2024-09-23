package com.example.dbeaver.entity.lead;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leadtype", schema = "dbo")
@Getter
@Setter
public class LeadType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
