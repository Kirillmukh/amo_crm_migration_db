package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.ActivityByCompanyDTO;
import com.example.dbeaver.entity.account.Activity;
import org.springframework.stereotype.Component;

@Component
public class OpportunityActivityMapper {
    public ActivityByCompanyDTO mapToDTO(Activity activity) {
        ActivityByCompanyDTO dto = new ActivityByCompanyDTO();
        dto.setTitle(activity.getTitle());
        dto.setDueDate(activity.getDueDate());
        dto.setStatus(activity.getActivityStatus().getName());
        return dto;
    }
}
