package com.example.dbeaver.entity.account;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usrsegment", schema = "dbo")
@Getter
@Setter
public class UsrSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
}
