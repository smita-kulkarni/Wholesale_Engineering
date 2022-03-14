package com.example.demo.http.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.dto.TransactionsDTO;
import com.example.demo.enums.TransactionType;
import com.example.demo.http.response.TransactionResponse;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class TransactionsMapperTest {

  @Test
  void fromDomain() {
    TransactionsDTO transactionsDTO = getTransactionDTO();
    TransactionResponse transactionResponse = TransactionsMapper.fromDomain(transactionsDTO);
    assertThat(transactionResponse.getAmount()).isEqualTo(transactionsDTO.getAmount());
    assertThat(transactionResponse.getCurrency()).isEqualTo(transactionsDTO.getCurrency());
    assertThat(transactionResponse.getTransactionType()).isEqualTo(transactionsDTO.getTransactionType());
  }

  @Test
  void testFromDomain() {
    TransactionsDTO transactionsDTO = getTransactionDTO();
    List<TransactionsDTO> transactionsDTOList = Collections.singletonList(transactionsDTO);
    List<TransactionResponse> transactionResponseList = TransactionsMapper.fromDomain(transactionsDTOList);

    assertThat(transactionResponseList.isEmpty()).isFalse();
    assertThat(transactionResponseList.size()).isEqualTo(1);

    TransactionResponse transactionResponse = transactionResponseList.get(0);
    assertThat(transactionResponse.getAmount()).isEqualTo(transactionsDTO.getAmount());
    assertThat(transactionResponse.getCurrency()).isEqualTo(transactionsDTO.getCurrency());
    assertThat(transactionResponse.getTransactionType()).isEqualTo(transactionsDTO.getTransactionType());


  }

  private TransactionsDTO getTransactionDTO(){
    return TransactionsDTO.builder().transactionType(TransactionType.CREDIT.name())
        .transactionTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")))
        .remarks("Test transaction")
        .currency("AUD")
        .amount(new BigDecimal("20.00")).build();
  }
}