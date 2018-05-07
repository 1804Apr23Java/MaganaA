

--DROP USER BANK CASCADE;
/*******************************************************************************
   Create database
********************************************************************************/
/*
CREATE USER BANK
IDENTIFIED BY BANK123
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to BANK;
GRANT resource to BANK;
GRANT create session TO BANK;
GRANT create table TO BANK;
GRANT create view TO BANK;


*/
--DDL CREATING TABLES
CREATE TABLE BANK_ACCOUNT(
    BANK_ACCOUNT_ID INTEGER PRIMARY KEY,
    BANK_ACCOUNT_NAME VARCHAR2(20),
    ACCOUNT_BALANCE NUMBER(8,2) DEFAULT 0.00);
/
CREATE TABLE LOGIN_INFO(
    USERNAME VARCHAR2(16) PRIMARY KEY,
    PASSWORD VARCHAR2(16));
/
CREATE TABLE USER_INFO(
    USER_ID INTEGER PRIMARY KEY,
    USERNAME VARCHAR2(16),
    BANK_ACCOUNT_ID INTEGER);
/


--DDL CONSTRAINTS 
ALTER TABLE USER_INFO 
ADD CONSTRAINT FK_LOGIN_USER_USERNAME
FOREIGN KEY (USERNAME) REFERENCES LOGIN_INFO(USERNAME);
/
ALTER TABLE USER_INFO 
ADD CONSTRAINT FK_BANK_USER_USERNAME
FOREIGN KEY (BANK_ACCOUNT_ID) REFERENCES BANK_ACCOUNT(BANK_ACCOUNT_ID);
/

--DML ADDING DATA  TO LOGIN_INFO

INSERT INTO "BANK"."LOGIN_INFO" (USERNAME, PASSWORD) VALUES ('AND', 'AND123');
/

CREATE SEQUENCE BANK_ACCOUNT_ID_PK
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;

--add triggers for primary keys
CREATE OR REPLACE TRIGGER "TR_INSERT_BANK_ACCOUNT"
BEFORE INSERT ON BANK_ACCOUNT
FOR EACH ROW
BEGIN
    SELECT BANK_ACCOUNT_ID_PK.NEXTVAL INTO : NEW.BANK_ACCOUNT_ID FROM DUAL;
END;
/
CREATE SEQUENCE user_id_pk
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;


create or replace trigger "TR_INSERT_USER_INFO"
before insert on user_info
for each row
begin
    select user_id_pk.nextval into :new.user_id from dual;
end;
create or replace trigger "TR_INSERT_BANK_USER"
after insert on BANK_ACCOUNT
begin
    select user_id_pk.nextval into :new.user_info(bank_account_id) values bank_account_id_pk;
end;