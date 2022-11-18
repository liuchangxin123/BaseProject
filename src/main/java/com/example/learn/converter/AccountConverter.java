package com.example.learn.converter;

import com.example.learn.data.dto.AccountDTO;
import com.example.learn.data.pojo.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @ClassName AccountConverter
 * @Description AccountConverter
 * @Date 2022/11/18 09:43
 * @Author pluto
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountConverter {

     @Mapping(source = "phone", target = "address")
     Account toAccountVo(AccountDTO account);

}
