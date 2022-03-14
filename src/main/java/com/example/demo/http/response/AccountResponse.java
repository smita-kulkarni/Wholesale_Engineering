package com.example.demo.http.response;

import com.example.demo.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountResponse {

  private Integer accountNumber;

  private String accountName;

  private AccountType accountType;

  private LocalDate balanceDate;

  private String currency;

  private BigDecimal openingBalance;
}
