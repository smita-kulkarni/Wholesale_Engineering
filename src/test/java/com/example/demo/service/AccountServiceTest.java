package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.BankUser;
import com.example.demo.entity.TransactionsEntity;
import com.example.demo.enums.AccountType;
import com.example.demo.enums.TransactionType;
import com.example.demo.respository.AccountsRepository;
import com.example.demo.respository.UserRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private AccountsRepository accountsRepository;

  @InjectMocks
  private AccountService accountService;


  @Test
  void fetchAllAccounts() {
    BankUser bankUser = new BankUser();
    bankUser.setId(new Random().nextInt());
    bankUser.setUserName("Jack");
    AccountEntity accountEntity = getAccountEntity();
    TransactionsEntity transactionsEntity = getTransactionEntity();

    bankUser.setAccounts(Collections.singletonList(accountEntity));

    when(userRepository.findByUserName(any(String.class))).thenReturn(Optional.of(bankUser));

    List<AccountDTO> accountDTOS =  accountService.fetchAllAccounts("Jack");
    assertThat(accountDTOS.isEmpty()).isFalse();
    assertThat(accountDTOS.size()).isEqualTo(1);

    AccountDTO accountDTO = accountDTOS.get(0);
    assertThat(accountDTO.getTransactionsDTOS().size()).isEqualTo(1);
    assertThat(accountDTO.getAccountNumber()).isEqualTo(accountEntity.getAccountNumber());
    assertThat(accountDTO.getAccountName()).isEqualTo(accountEntity.getAccountName());
    assertThat(accountDTO.getOpeningBalance()).isEqualTo(accountEntity.getOpeningBalance());

    TransactionsDTO transactionsDTO = accountDTO.getTransactionsDTOS().get(0);
    assertThat(transactionsDTO.getAmount()).isEqualTo(transactionsEntity.getAmount());
    assertThat(transactionsDTO.getCurrency()).isEqualTo(transactionsEntity.getCurrency());
    assertThat(transactionsDTO.getTransactionType()).isEqualTo(transactionsEntity.getTransactionType());
  }

  @Test
  void getAllTransactionsForAccountNumber() {
    AccountEntity accountEntity = getAccountEntity();
    when(accountsRepository.findById(any(Integer.class))).thenReturn(Optional.of(accountEntity));
    List<TransactionsDTO> transactionsDTOS = accountService.getAllTransactionsForAccountNumber(accountEntity.getAccountNumber());

    TransactionsEntity transactionsEntity = getTransactionEntity();
    assertThat(transactionsDTOS.isEmpty()).isFalse();
    TransactionsDTO transactionsDTO = transactionsDTOS.get(0);
    assertThat(transactionsDTO.getAmount()).isEqualTo(transactionsEntity.getAmount());
    assertThat(transactionsDTO.getCurrency()).isEqualTo(transactionsEntity.getCurrency());
    assertThat(transactionsDTO.getTransactionType()).isEqualTo(transactionsEntity.getTransactionType());
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
