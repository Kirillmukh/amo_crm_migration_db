package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.util.ActivityByCompanyDTO;
import com.example.dbeaver.entity.account.Activity;
import org.springframework.stereotype.Component;

@Component
public class OpportunityActivityMapper {
    public ActivityByCompanyDTO mapToDTO(Activity activity) {
        return new ActivityByCompanyDTO(
                activity.getTitle(),
                activity.getDueDate(),
                activity.getActivityStatus().getName()
        );
    }
}
