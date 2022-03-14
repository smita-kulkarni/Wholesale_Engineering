package com.example.demo.http.mapper;

import com.example.demo.dto.TransactionsDTO;
import com.example.demo.http.response.TransactionResponse;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionsMapper {
  public static List<TransactionResponse> fromDomain(List<TransactionsDTO> transactionsDTOList){
    return transactionsDTOList == null ? null : transactionsDTOList.stream().map(TransactionsMapper::fromDomain).collect(Collectors.toList());
  }

  public static TransactionResponse fromDomain(TransactionsDTO transactionsDTO){
    return TransactionResponse.builder()
        .accountNumber(transactionsDTO.getAccountNumber())
        .amount(transactionsDTO.getAmount())
        .currency(transactionsDTO.getCurrency())
        .remarks(transactionsDTO.getRemarks())
        .transactionTime(transactionsDTO.getTransactionTime())
        .transactionType(transactionsDTO.getTransactionType())
        .build();
  }
}
