package com.example.demo.controller;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.http.mapper.AccountMapper;
import com.example.demo.http.mapper.TransactionsMapper;
import com.example.demo.http.response.AccountResponse;
import com.example.demo.http.response.AccountsResponse;
import com.example.demo.http.response.TransactionResponse;
import com.example.demo.http.response.TransactionsResponse;
import com.example.demo.service.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@Validated
public class AccountTransactionsController {

  private final AccountService accountService;

  @GetMapping(value = "/accounts/{user}")
  public ResponseEntity<AccountsResponse> displayAccounts(@PathVariable String user){
    List<AccountDTO> accountDTOS = accountService.fetchAllAccounts(user);
    List<AccountResponse> accountResponseList = AccountMapper.fromDomain(accountDTOS);
    AccountsResponse accountsResponse = AccountsResponse.builder().accounts(accountResponseList).build();
    return ResponseEntity.ok(accountsResponse);
  }

  @GetMapping(value = "/{accountNumber}/transactions")
  public ResponseEntity<TransactionsResponse> getTransactionsForAccount(@PathVariable final Integer accountNumber) {
    List<TransactionsDTO> transactions = accountService.getAllTransactionsForAccountNumber(accountNumber);
    List<TransactionResponse> transactionsResponses = TransactionsMapper.fromDomain(transactions);
    TransactionsResponse transactionsResponse = TransactionsResponse.builder().transactions(transactionsResponses).build();
    return ResponseEntity.ok(transactionsResponse);
  }
}