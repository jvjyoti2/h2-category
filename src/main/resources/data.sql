insert into TRANSACTIONS
(transaction_Date, vendor, type, amount, category)
values
(TO_DATE('01-11-2020','dd-mm-yyyy'), 'Morrisons', 'card', 10.40, 'Groceries'),
(TO_DATE('28-10-2020','dd-mm-yyyy'), 'CYBG', 'direct debit', 600, 'MyMonthlyDD'),
(TO_DATE('28-10-2020','dd-mm-yyyy'), 'PureGym', 'direct debit', 40, 'MyMonthlyDD'),
(TO_DATE('01-10-2020','dd-mm-yyyy'), 'M&S', 'card', 5.99, 'Groceries'),
(TO_DATE('30-09-2020','dd-mm-yyyy'), 'McMillan', 'internet', 10, 'Groceries')
;
