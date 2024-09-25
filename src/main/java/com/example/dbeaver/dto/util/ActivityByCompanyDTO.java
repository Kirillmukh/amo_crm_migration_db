package com.example.dbeaver.dto.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ActivityByCompanyDTO {
    private String title; // activity -> title
    private LocalDateTime dueDate; // activity -> dueDate
    private String status; // activityStatus -> name
}
