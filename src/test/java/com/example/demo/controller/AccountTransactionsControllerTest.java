package com.example.demo.controller;

import static com.example.demo.enums.AccountType.SAVINGS;
import static com.example.demo.enums.TransactionType.CREDIT;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.enums.AccountType;
import com.example.demo.service.AccountService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AccountTransactionsControllerTest {

  @Mock
  private AccountService accountService;

  @InjectMocks
  private AccountTransactionsController accountTransactionsController;

  private MockMvc mvc;

  @BeforeEach
  public void init() {
    mvc = MockMvcBuilders.standaloneSetup(accountTransactionsController).build();
  }

  @Test
  void displayAccounts() throws Exception {
    AccountDTO accountDTO = getAccountDTO();
    when(accountService.fetchAllAccounts(ArgumentMatchers.any(String.class))).thenReturn(Collections.singletonList(accountDTO));

    mvc.perform(get("/accounts/"+1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accounts", hasSize(1)))
        .andExpect(jsonPath("$.accounts[0].accountType",Matchers.is(SAVINGS.toString())))
        .andExpect(jsonPath("$.accounts[0].accountName", Matchers.is("Jack account")));

  }

  @Test
  void getTransactionsForAccount() throws Exception {
    TransactionsDTO transactionsDTO = getTransactionDTO();
    when(accountService.getAllTransactionsForAccountNumber(anyInt())).thenReturn(Collections.singletonList(transactionsDTO));
    mvc.perform(get("/"+1+"/transactions"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.transactions", hasSize(1)))
        .andExpect(jsonPath("$.transactions[0].transactionType",Matchers.is(CREDIT.toString())))
        .andExpect(jsonPath("$.transactions[0].amount", Matchers.is(20.00)));


  }

  private AccountDTO getAccountDTO(){
    return AccountDTO.builder()
        .userId(1)
        .accountType(AccountType.SAVINGS)
        .balanceDate(LocalDate.now())
        .accountName("Jack account")
        .currency("AUD")
        .openingBalance(new BigDecimal("25.00"))
        .accountNumber(1000200)
        .transactionsDTOS(Collections.singletonList(getTransactionDTO()))
        .build();
  }

  private TransactionsDTO getTransactionDTO(){
    return TransactionsDTO.builder().transactionType(CREDIT.name())
        .transactionTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")))
        .remarks("Test transaction")
        .currency("AUD")
        .amount(new BigDecimal("20.00")).build();
  }
}