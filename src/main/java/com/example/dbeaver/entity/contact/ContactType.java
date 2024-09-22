package com.example.dbeaver.entity.contact;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacttype", schema = "dbo")
@Getter
@Setter
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
