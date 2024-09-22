package com.example.dbeaver.entity.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accountcategory", schema = "dbo")
@Getter
@Setter
public class AccountCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
