INSERT INTO CUSTOMER (LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER)
VALUES ('Kyrkos', 'Marios', 'SSN-1111111111');
INSERT INTO CUSTOMER (LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER)
VALUES ('Doe', 'John', 'SSN-2222222222');
INSERT INTO CUSTOMER (LAST_NAME, FIRST_NAME, SOCIAL_SECURITY_NUMBER)
VALUES ('Connor', 'Maria', 'SSN-3333333333');

INSERT INTO ACCOUNT (CUSTOMER_ID, BALANCE, ACCOUNT_TYPE)
VALUES (1, 5500, 'INVESTMENT');
INSERT INTO ACCOUNT (CUSTOMER_ID, BALANCE, ACCOUNT_TYPE)
VALUES (1, 500, 'SAVINGS');
INSERT INTO ACCOUNT (CUSTOMER_ID, BALANCE, ACCOUNT_TYPE)
VALUES (2, 100000, 'SAVINGS');
INSERT INTO ACCOUNT (CUSTOMER_ID, BALANCE, ACCOUNT_TYPE)
VALUES (3, 0, 'SAVINGS');

INSERT INTO TRANSACTION (ACCOUNT_ID, AMOUNT)
VALUES (1, 5000);
INSERT INTO TRANSACTION (ACCOUNT_ID, AMOUNT)
VALUES (1, -3500);
INSERT INTO TRANSACTION (ACCOUNT_ID, AMOUNT)
VALUES (1, 4000);
INSERT INTO TRANSACTION (ACCOUNT_ID, AMOUNT)
VALUES (2, 500);
INSERT INTO TRANSACTION (ACCOUNT_ID, AMOUNT)
VALUES (3, 100000);