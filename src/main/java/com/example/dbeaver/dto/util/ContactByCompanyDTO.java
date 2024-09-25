package com.example.dbeaver.dto.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ContactByCompanyDTO {
    private String name;
    private String jobTitle;
    private String mobilePhone;
    private String phone;
}
