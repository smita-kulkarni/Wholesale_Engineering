insert into bank_user(user_name) values ('Jack'), ('Jenny');

insert into accounts(account_name, account_type, balance_date, currency, opening_balance, bank_user)
values ('Jack account','SAVINGS',CURRENT_DATE, 'AUD', '1000.00', (select id from bank_user where user_name='Jack')),
('Jenny account','SAVINGS',CURRENT_DATE, 'AUD', '100.00', (select id from bank_user where user_name='Jenny'));

insert into transactions(account_number,transaction_type, transaction_time,remarks,amount, currency)
values ((select account_number from accounts where account_name='Jack account'), 'CREDIT', CURRENT_TIMESTAMP, 'Test data', '25.00','AUD'),
((select account_number from accounts where account_name='Jack account'), 'CREDIT', CURRENT_TIMESTAMP, 'Test data', '20.00','AUD'),
((select account_number from accounts where account_name='Jack account'), 'CREDIT', CURRENT_TIMESTAMP, 'Test data', '15.00','AUD'),
((select account_number from accounts where account_name='Jack account'), 'CREDIT', CURRENT_TIMESTAMP, 'Test data', '25.00','AUD');