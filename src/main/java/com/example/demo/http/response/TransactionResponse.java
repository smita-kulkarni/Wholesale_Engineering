package com.example.demo.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionResponse {
  private Integer accountNumber;

  private String transactionType;

  private ZonedDateTime transactionTime;

  private String remarks;

  private BigDecimal amount;

  private String currency;

}
