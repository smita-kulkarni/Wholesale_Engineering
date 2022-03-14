package com.example.demo.mapper;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.TransactionsEntity;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionsEntityMapper {
  public static TransactionsDTO fromEntity(TransactionsEntity transactionsEntity){
    return TransactionsDTO.builder()
        .amount(transactionsEntity.getAmount())
        .currency(transactionsEntity.getCurrency())
        .remarks(transactionsEntity.getRemarks())
        .transactionTime(transactionsEntity.getTransactionTime())
        .transactionType(transactionsEntity.getTransactionType())
        .build();
  }

  public static List<TransactionsDTO> fromEntities(List<TransactionsEntity> transactionsEntityList){
    return transactionsEntityList == null ? null : transactionsEntityList.stream().map(TransactionsEntityMapper::fromEntity).collect(Collectors.toList());
  }

  public static TransactionsEntity toEntity(TransactionsDTO transactionsDTO){
    TransactionsEntity transactionsEntity = new TransactionsEntity();
    transactionsEntity.setAmount(transactionsDTO.getAmount());
    transactionsEntity.setCurrency(transactionsDTO.getCurrency());
    transactionsEntity.setRemarks(transactionsDTO.getRemarks());
    transactionsEntity.setTransactionTime(ZonedDateTime.now());
    transactionsEntity.setTransactionType(transactionsDTO.getTransactionType());
    return  transactionsEntity;
  }

}
