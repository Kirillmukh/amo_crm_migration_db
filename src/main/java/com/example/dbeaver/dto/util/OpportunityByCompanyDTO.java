package com.example.dbeaver.dto.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OpportunityByCompanyDTO {
    private String title; // opportunity -> title
    private String stage; // opportunityStage -> name
    private double amount; // opportunity -> amount
}
