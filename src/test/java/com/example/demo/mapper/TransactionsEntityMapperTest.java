package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.dto.TransactionsDTO;
import com.example.demo.entity.TransactionsEntity;
import com.example.demo.enums.TransactionType;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class TransactionsEntityMapperTest {

  @Test
  void fromEntity() {
    TransactionsEntity transactionsEntity = getTransactionEntity();
    TransactionsDTO transactionsDTO = TransactionsEntityMapper.fromEntity(transactionsEntity);
    assertThat(transactionsDTO.getAmount()).isEqualTo(transactionsEntity.getAmount());
    assertThat(transactionsDTO.getCurrency()).isEqualTo(transactionsEntity.getCurrency());
    assertThat(transactionsDTO.getTransactionType()).isEqualTo(transactionsEntity.getTransactionType());
  }

  @Test
  void fromEntities() {
    TransactionsEntity transactionsEntity = getTransactionEntity();
    List<TransactionsEntity> transactionsEntityList = Collections.singletonList(transactionsEntity);
    List<TransactionsDTO> transactionsDTOList = TransactionsEntityMapper.fromEntities(transactionsEntityList);

    assertThat(transactionsDTOList.isEmpty()).isFalse();
    assertThat(transactionsDTOList.size()).isEqualTo(1);

    TransactionsDTO transactionsDTO = transactionsDTOList.get(0);
    assertThat(transactionsDTO.getAmount()).isEqualTo(transactionsEntity.getAmount());
    assertThat(transactionsDTO.getCurrency()).isEqualTo(transactionsEntity.getCurrency());
    assertThat(transactionsDTO.getTransactionType()).isEqualTo(transactionsEntity.getTransactionType());
  }

  private TransactionsEntity getTransactionEntity(){
    TransactionsEntity transactionsEntity = new TransactionsEntity();
    transactionsEntity.setTransactionType(TransactionType.CREDIT.name());
    transactionsEntity.setTransactionTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));
    transactionsEntity.setRemarks("Test transaction");
    transactionsEntity.setCurrency("AUD");
    transactionsEntity.setAmount(new BigDecimal("20.00"));
    transactionsEntity.setId(new Random().nextInt());

    return transactionsEntity;
  }

}