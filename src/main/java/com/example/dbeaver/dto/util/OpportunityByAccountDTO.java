package com.example.dbeaver.dto.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OpportunityByAccountDTO {
    private String title; // opportunity -> title
    private String stage; // opportunityStage -> name
    private String event; // usreventinopportid -> name (Event)
    private double amount; // opportunity -> amount
}
