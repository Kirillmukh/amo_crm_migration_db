package com.example.dbeaver.dto.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactByCompanyDTO {
    private String name;
    private String jobTitle;
    private String mobilePhone;
    private String phone;
}
