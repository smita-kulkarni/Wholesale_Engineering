package com.example.demo.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.dto.AccountDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.TransactionsEntity;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class AccountEntityMapperTest {

  @Test
  void fromEntity() {
    AccountEntity accountEntity = getAccountEntity();
    AccountDTO accountDTO = AccountEntityMapper.fromEntity(accountEntity);

    assertThat(accountDTO.getTransactionsDTOS().size()).isEqualTo(1);
    assertThat(accountDTO.getAccountNumber()).isEqualTo(accountEntity.getAccountNumber());
    assertThat(accountDTO.getAccountName()).isEqualTo(accountEntity.getAccountName());
    assertThat(accountDTO.getOpeningBalance()).isEqualTo(accountEntity.getOpeningBalance());
  }

  @Test
  void fromEntities() {
    AccountEntity accountEntity = getAccountEntity();
    List<AccountEntity> accountEntityList = Collections.singletonList(accountEntity);
    List<AccountDTO> accountDTOS =  AccountEntityMapper.fromEntities(accountEntityList);

    assertThat(accountDTOS.isEmpty()).isFalse();
    assertThat(accountDTOS.size()).isEqualTo(1);

    AccountDTO accountDTO = accountDTOS.get(0);
    assertThat(accountDTO.getTransactionsDTOS().size()).isEqualTo(1);
    assertThat(accountDTO.getAccountNumber()).isEqualTo(accountEntity.getAccountNumber());
    assertThat(accountDTO.getAccountName()).isEqualTo(accountEntity.getAccountName());
    assertThat(accountDTO.getOpeningBalance()).isEqualTo(accountEntity.getOpeningBalance());
  }


  private AccountEntity getAccountEntity(){
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setAccountName("Jack accounts");
    accountEntity.setAccountNumber(1000200);
    accountEntity.setOpeningBalance(BigDecimal.valueOf(1000.00));
    accountEntity.setCurrency("AUD");
    accountEntity.setBalanceDate(LocalDate.now());
    accountEntity.setAccountType(AccountType.SAVINGS.name());
    accountEntity.setTransactionsEntities(Collections.singletonList(getTransactionEntity()));
    return  accountEntity;
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