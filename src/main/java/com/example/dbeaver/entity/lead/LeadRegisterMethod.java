package com.example.dbeaver.entity.lead;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leadregistermethod", schema = "dbo")
@Getter
@Setter
public class LeadRegisterMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
