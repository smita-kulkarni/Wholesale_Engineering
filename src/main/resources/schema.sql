DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS bank_user;
DROP TABLE IF EXISTS accounts;

CREATE TABLE bank_user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
);

CREATE TABLE accounts (
  account_number INT AUTO_INCREMENT PRIMARY KEY,
  account_name VARCHAR(250) NOT NULL,
  account_type VARCHAR(250) NOT NULL,
  balance_date date,
  currency VARCHAR(3),
  opening_balance NUMERIC(20,10),
  bank_user INT NOT NULL,
  CONSTRAINT FK_USER FOREIGN KEY (bank_user) REFERENCES bank_user(id)
);

CREATE TABLE transactions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_number NUMERIC NOT NULL,
  transaction_type VARCHAR(250) NOT NULL,
  transaction_time timestamp,
  remarks VARCHAR(250),
  amount numeric(20,10),
  currency VARCHAR(3),
  CONSTRAINT FK_ACCOUNT_NUMBER FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

