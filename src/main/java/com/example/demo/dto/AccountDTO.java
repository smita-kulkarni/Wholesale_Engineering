package com.example.demo.dto;

import com.example.demo.enums.AccountType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
  private Integer accountNumber;

  private String accountName;

  private AccountType accountType;

  private LocalDate balanceDate;

  private String currency;

  private BigDecimal openingBalance;

  private List<TransactionsDTO> transactionsDTOS;

  private Integer userId;

}
