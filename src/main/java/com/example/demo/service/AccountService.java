package com.example.demo.service;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.TransactionsDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.BankUser;
import com.example.demo.entity.TransactionsEntity;
import com.example.demo.exception.AccountsException;
import com.example.demo.mapper.AccountEntityMapper;
import com.example.demo.mapper.TransactionsEntityMapper;
import com.example.demo.respository.AccountsRepository;
import com.example.demo.respository.UserRepository;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class AccountService {

  private final AccountsRepository accountsRepository;

  private final UserRepository userRepository;

  public List<AccountDTO> fetchAllAccounts(String user) {
    BankUser bankUser = userRepository.findByUserName(user)
        .orElseThrow(() -> new AccountsException("No accounts found for the given user"));
    List<AccountDTO> accountsList = Collections.emptyList();

    if (!CollectionUtils.isEmpty(bankUser.getAccounts())) {
      accountsList = AccountEntityMapper.fromEntities(bankUser.getAccounts());
    }

    return accountsList;

  }

  public List<TransactionsDTO> getAllTransactionsForAccountNumber(Integer accountNumber) {
    AccountEntity accountEntity = accountsRepository.findById(accountNumber)
        .orElseThrow(() -> new AccountsException("Account not found for given ID"));

    List<TransactionsEntity> transactionsEntities = accountEntity.getTransactionsEntities();
    List<TransactionsDTO> transactionsList = Collections.emptyList();

    if (!CollectionUtils.isEmpty(transactionsEntities)) {
      transactionsList = TransactionsEntityMapper.fromEntities(transactionsEntities);
    }

    return transactionsList;
  }

}
