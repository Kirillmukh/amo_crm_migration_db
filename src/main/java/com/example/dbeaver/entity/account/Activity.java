package com.example.dbeaver.entity.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity", schema = "dbo")
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "createdon")
    private LocalDateTime createdOn;
    @Column(name = "modifiedon")
    private LocalDateTime modifiedOn;
    private String title;
    @Column(name = "duedate")
    private LocalDateTime dueDate;
    @ManyToOne
    @JoinColumn(name = "statusid")
    private ActivityStatus activityStatus;
    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;
}
