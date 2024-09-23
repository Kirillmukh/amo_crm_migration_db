package com.example.dbeaver.entity.lead;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "campaign", schema = "dbo")
@Getter
@Setter
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
