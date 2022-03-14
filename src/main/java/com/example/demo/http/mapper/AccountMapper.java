package com.example.demo.http.mapper;

import com.example.demo.dto.AccountDTO;
import com.example.demo.http.response.AccountResponse;
import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {

  public static AccountResponse fromDomain(AccountDTO accountDTO){
    return AccountResponse.builder().accountName(accountDTO.getAccountName())
        .accountNumber(accountDTO.getAccountNumber())
        .accountType(accountDTO.getAccountType())
        .balanceDate(accountDTO.getBalanceDate())
        .currency(accountDTO.getCurrency())
        .openingBalance(accountDTO.getOpeningBalance())
        .build();
  }

  public static List<AccountResponse> fromDomain(List<AccountDTO> accountDTO){
    return accountDTO == null ? null : accountDTO.stream().map(AccountMapper::fromDomain).collect(Collectors.toList());
  }

}
