package com.example.dbeaver.entity.lead;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leadmedium", schema = "dbo")
@Getter
@Setter
public class LeadMedium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
