package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Column;
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
public class TransactionsDTO {
  private Integer accountNumber;

  private String transactionType;

  private ZonedDateTime transactionTime;

  private String remarks;

  private BigDecimal amount;

  private String currency;

}
