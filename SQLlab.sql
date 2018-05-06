--2     SQL Queries
--In this section you will be performing various queries against the Oracle Chinook database.
--2.1 SELECT
--Task – Select all records from the Employee table.

SELECT * FROM Employee;
/

--Task – Select all records from the Employee table where last name is King.

SELECT * FROM Employee
WHERE LastName = 'King';
/

--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.

SELECT * FROM Employee
WHERE FirstName = 'Andrew' AND ReportsTo IS NULL;
/

--2.2 ORDER BY

--Task – Select all albums in Album table and sort result set in descending order by title.

SELECT * FROM Album
ORDER BY Title DESC;
/

--Task – Select first name from Customer and sort result set in ascending order by city

SELECT FirstName FROM Customer
ORDER BY City;
/
--2.3 INSERT INTO
--Task – Insert two new records into Genre table 

INSERT INTO Genre (Name)
VALUES ('Boring');
/

INSERT INTO Genre (Name)
VALUES ('EDM');
/

--Task – Insert two new records into Employee table
INSERT INTO Employee (LastNameV, FirstName,Title)
VALUES ('Magana','Andres','Boss');
/

INSERT INTO Employee (LastNameV, FirstName,Title)
VALUES ('Higgins','Emily','Instructor');
/

--Task – Insert two new records into Customer table

INSERT INTO Customer (FirstName, LastName, City)
VALUES ('leroy', 'Jenkins', 'Tampa');
/

INSERT INTO Customer (FirstName, LastName, City)
VALUES ('lenny', 'Simens', 'Springfield');
/

--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter

UPDATE Customer
SET FirstName = 'Robert', LastName = 'Walter'
WHERE FirstName = 'Aaron' AND LastName = 'Mitchell';
/

--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE Artist
SET Name = 'CCR'
WHERE Name = 'Creedence Clearwater Revival';
/

    
--2.5 LIKE
--Task – Select all invoices with a billing address like “T%” 

SELECT * FROM Invoice
WHERE BillingAddress LIKE 'T%';
/

--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50

SELECT * FROM Invoice
WHERE Total BETWEEN 15 AND 50;
/

--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM Employee
WHERE HireDate BETWEEN #06/01/2003# AND #03/01/2004#;
/

--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).

DELETE FROM Customer
WHERE FirstName='Robert' AND LastName='Walter';
/

--SQL Functions
--In this section you will be using the Oracle system functions, as well as your own functions, to perform various actions against the database
--3.1 System Defined Functions
--Task – Create a function that returns the current time.

CREATE OR REPLACE CURRENTTIME
RETURN TIMESTAMP
IS THISTIME TIMESTAMP
BEGIN
	THISTIME := CURRENT_TIMESTAMP;
	RETURN THISTIME;

END;
/

Task – create a function that returns the length of name in MEDIATYPE table

CREATE OR REPLACE MEDIATYPE_NAME_LENGTH
RETURN INT(10)
IS MEDIA_LENGTH INT(10) 
BEGIN
	MEDIA_LENGTH := SELECT MAX (LENGTH Name)
					FROM MediaType;
	RETURN MEDIA_LENGTH ;
END;
/


--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 

CREATE OR REPLACE INVOICE_AVG
RETURN NUMBER(10,2)
IS AVERAGE NUMBER(10,2)
BEGIN
	AVERAGE := SELECT AVG( Total )
				FROM Invoice;
	RETURN AVERAGE ;
END;
/

--Task – Create a function that returns the most expensive track

CREATE OR REPLACE PRICY_TRACK
RETURN NUMBER(10,2)
IS PRICE INUMBER(10,2) 
BEGIN
	PRICE := SELECT MAX (UnitPrice)
					FROM Track;
	RETURN PRICE ;
END;
/

--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table

CREATE OR REPLACE INVOICELINE_PRICE_AVG
RETURN NUMBER(10,2)
IS AVERAGE NUMBER(10,2)
BEGIN
	AVERAGE := SELECT AVG( UnitPrice )
				FROM InvoiceLine;
	RETURN AVERAGE ;
END;


3.4 User Defined Table Valued Functions
Task – Create a function that returns all employees who are born after 1968.





4.0 Stored Procedures
 In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.
4.1 Basic Stored Procedure
Task – Create a stored procedure that selects the first and last names of all the employees.
4.2 Stored Procedure Input Parameters
Task – Create a stored procedure that updates the personal information of an employee.
Task – Create a stored procedure that returns the managers of an employee.
4.3 Stored Procedure Output Parameters
Task – Create a stored procedure that returns the name and company of a customer.

5.0 Transactions
In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
 

6.0 Triggers
In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
6.1 AFTER/FOR
Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
Task – Create an after update trigger on the album table that fires after a row is inserted in the table
Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.

--7.0 JOINS
--In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.

SELECT Customer
FROM Invoice.InvoiceLineId , Customer.FirstName, Customer.LastName
INNER JOIN Invoice ON 
Customer.CustomerId = 
Invoice.CustomerId;
/
--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.

SELECT Customer.CustomerId, Customer.FirstName, Customer.LastName, Invoice.InvoiceLineId , Invoice.Total
FROM Customer
FULL OUTER JOIN Invoice ON Customer.CustomerId = Invoice.CustomerId;
/
--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.


SELECT Artist.Name, Album.Title
FROM Album
RIGHT JOIN Artist ON Album.ArtistId = Artist.ArtistId; 
/
--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.

SELECT * 
ORDER BY Artist.Name
FROM Album 
CROSS JOIN Artist;
/
--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.

SELECT *
FROM Employee T1, Employee T2
WHERE T1.EmployeeId <> T2.EmployeeId AND T1.ReportsTo = T2.ReportsTo ; 
/
