DROP USER Week2DB CASCADE;


/*******************************************************************************
   Create database
********************************************************************************/
CREATE USER Week2DB
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to Week2DB;
GRANT resource to Week2DB;
GRANT create session TO Week2DB;
GRANT create table TO Week2DB;
GRANT create view TO Week2DB;



--conn Week2DB/p4ssw0rd
/

/*******************************************************************************
   Create Tables
********************************************************************************/
CREATE TABLE EMPLOYEE
(
    EMPLOYEE_ID INTEGER NOT NULL,
    EMP_FIRSTNAME VARCHAR2(16),
	EMP_LASTNAME VARCHAR2(16),
    DEPARTMENT_ID NUMBER NOT NULL,
	SALARY NUMBER,
	EMP_EMAIL VARCHAR2(50),
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY  (EMPLOYEE_ID)
);
/

CREATE TABLE DEPARTMENT 
(
	DEPARTMENT_ID INTEGER NOT NULL,
	DEPARTMENT_NAME VARCHAR2(30),
	CONSTRAINT PK_DEPARTMENT PRIMARY KEY  (DEPARTMENT_ID)
);
/

CREATE SEQUENCE EMPLOYEE_seq
  MINVALUE 0
  START WITH 0
  INCREMENT BY 1
  CACHE 20;
/

CREATE SEQUENCE DEPARTMENT_seq
  MINVALUE 0
  START WITH 0
  INCREMENT BY 1
  CACHE 20;
/ 
 

CREATE OR REPLACE TRIGGER TR_PK_EMPLOYEE
BEFORE INSERT
   ON EMPLOYEE
    FOR EACH ROW 
BEGIN
   -- trigger code
   SELECT EMPLOYEE_seq.nextval into :new.EMPLOYEE_ID from dual;
END;
/

CREATE OR REPLACE TRIGGER TR_PK_DEPARTMENT
BEFORE INSERT
   ON DEPARTMENT
    FOR EACH ROW 
BEGIN
   -- trigger code
   SELECT DEPARTMENT_seq.nextval into :new.DEPARTMENT_ID from dual;   
   
END;
/  
--Insert at least six employees and three departments.

INSERT INTO DEPARTMENT VALUES (0,'SCIENCE');
/
INSERT INTO DEPARTMENT VALUES (0,'MATH');
/
INSERT INTO DEPARTMENT VALUES (0,'READING');
/



  