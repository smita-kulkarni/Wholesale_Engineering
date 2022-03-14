package com.example.demo.entity;

import com.example.demo.enums.AccountType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "account_number", nullable = false)
  private Integer accountNumber;

  @Column(name = "account_name", nullable = false)
  private String accountName;

  @Column(name = "account_type", nullable = false)
  private String accountType;

  @Column(name = "balance_date")
  private LocalDate balanceDate;

  @Column(name = "currency")
  private String currency;

  @Column(name = "opening_balance")
  private BigDecimal openingBalance;

  @OneToMany(mappedBy = "accountEntity")
  private List<TransactionsEntity> transactionsEntities;

  @ManyToOne()
  @JoinColumn(name = "id")
  private BankUser bankUser;

}
