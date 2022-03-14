package com.example.demo.mapper;

import com.example.demo.dto.AccountDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.BankUser;
import com.example.demo.enums.AccountType;
import java.util.List;
import java.util.stream.Collectors;

public class AccountEntityMapper {
  public static AccountDTO fromEntity(AccountEntity accountEntity){
    return AccountDTO.builder()
        .accountName(accountEntity.getAccountName())
        .accountType(AccountType.valueOf(accountEntity.getAccountType()))
        .accountNumber(accountEntity.getAccountNumber())
        .balanceDate(accountEntity.getBalanceDate())
        .currency(accountEntity.getCurrency())
        .openingBalance(accountEntity.getOpeningBalance())
        .transactionsDTOS(TransactionsEntityMapper.fromEntities(accountEntity.getTransactionsEntities()))
        .build();
  }

  public static List<AccountDTO> fromEntities(List<AccountEntity> accountEntityList){
    return accountEntityList == null ? null : accountEntityList.stream().map(AccountEntityMapper::fromEntity).collect(Collectors.toList());
  }

  public static AccountEntity toEntity(AccountDTO accountDTO, BankUser bankUser){
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setAccountName(accountDTO.getAccountName());
    accountEntity.setAccountNumber(accountDTO.getAccountNumber());
    accountEntity.setAccountType(accountDTO.getAccountType().name());
    accountEntity.setBalanceDate(accountDTO.getBalanceDate());
    accountEntity.setCurrency(accountDTO.getCurrency());
    accountEntity.setOpeningBalance(accountDTO.getOpeningBalance());
    accountEntity.setBankUser(bankUser);
    return  accountEntity;
  }

}
