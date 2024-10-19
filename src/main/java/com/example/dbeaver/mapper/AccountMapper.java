package com.example.dbeaver.mapper;

import com.example.dbeaver.dto.AccountDTO;
import com.example.dbeaver.entity.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    @Mapping(target = "type", source = "accountType.name")
    @Mapping(target = "category", source = "accountCategory.name")
    @Mapping(target = "industry", source = "accountIndustry.name")
    AccountDTO mapToDTO(Account account);
}
