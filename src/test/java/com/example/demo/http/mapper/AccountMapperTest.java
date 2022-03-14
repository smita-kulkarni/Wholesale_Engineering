package com.example.demo.http.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.entity.TransactionsEntity;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.TransactionType;
import com.example.demo.http.response.AccountResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AccountMapperTest {

  @Test
  void fromDomain() {
    AccountDTO accountDTO = getAccountDTO();
    AccountResponse accountResponse = AccountMapper.fromDomain(accountDTO);
    assertThat(accountResponse.getAccountNumber()).isEqualTo(accountDTO.getAccountNumber());
    assertThat(accountResponse.getAccountName()).isEqualTo(accountDTO.getAccountName());
    assertThat(accountResponse.getOpeningBalance()).isEqualTo(accountDTO.getOpeningBalance());

    TransactionsDTO transactionsDTO = accountDTO.getTransactionsDTOS().get(0);
    assertThat(transactionsDTO.getAmount()).isEqualTo(new BigDecimal("20.00"));
    assertThat(transactionsDTO.getCurrency()).isEqualTo("AUD");
    assertThat(transactionsDTO.getTransactionType()).isEqualTo(TransactionType.CREDIT.toString());
  }

  @Test
  void testFromDomain() {
    AccountDTO accountDTO = getAccountDTO();
    List<AccountDTO> accountDTOList = Collections.singletonList(accountDTO);
    List<AccountResponse> accountResponseList= AccountMapper.fromDomain(accountDTOList);

    assertThat(accountResponseList.size()).isEqualTo(1);
    AccountResponse accountResponse = accountResponseList.get(0);
    assertThat(accountResponse.getAccountNumber()).isEqualTo(accountDTO.getAccountNumber());
    assertThat(accountResponse.getAccountName()).isEqualTo(accountDTO.getAccountName());
    assertThat(accountResponse.getOpeningBalance()).isEqualTo(accountDTO.getOpeningBalance());

    TransactionsDTO transactionsDTO = accountDTO.getTransactionsDTOS().get(0);
    assertThat(transactionsDTO.getAmount()).isEqualTo(new BigDecimal("20.00"));
    assertThat(transactionsDTO.getCurrency()).isEqualTo("AUD");
    assertThat(transactionsDTO.getTransactionType()).isEqualTo(TransactionType.CREDIT.toString());
  }

  private AccountDTO getAccountDTO(){
    return AccountDTO.builder()
        .userId(1)
        .accountType(AccountType.SAVINGS)
        .balanceDate(LocalDate.now())
        .accountName("Test")
        .currency("AUD")
        .openingBalance(new BigDecimal("25.00"))
        .accountNumber(1000200)
        .transactionsDTOS(Collections.singletonList(getTransactionDTO()))
        .build();
  }

  private TransactionsDTO getTransactionDTO(){
    return TransactionsDTO.builder().transactionType(TransactionType.CREDIT.name())
    .transactionTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")))
    .remarks("Test transaction")
    .currency("AUD")
    .amount(new BigDecimal("20.00")).build();
  }
}