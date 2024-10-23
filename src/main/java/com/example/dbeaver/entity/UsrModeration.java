package com.example.dbeaver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usrmoderation", schema = "dbo")
@Getter
@Setter
public class UsrModeration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
