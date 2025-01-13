package com.example.dbeaver.dto.response;

import com.example.dbeaver.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseContactWithoutCompanyDTO {
    private ContactDTO contactDTO;
}
