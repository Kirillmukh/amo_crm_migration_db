package com.example.dbeaver.entity.contact;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contactdecisionrole", schema = "dbo")
@Getter
@Setter
public class ContactDecisionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
