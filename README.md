# FlatFileDatabase
Developed a file systems Database step by step using Java which handles all the DML commands as in SQL like Create, Update, Insert and Delete

README FOR Demo.java
1.Definition to create a Table:
<Relation(Attribute1, Attribute2,…, Attributen)>
<Attributei>::= <Name(Primary key):Type | Name: Type>

2.Instruction Format
Operation: Data for field1; Data for field2;… Data for fieldn:

3.Read data from the flat-file table
Read:Attribute-value 
For example
Read:Name-John
This will return all the records of students with the name John, that is,
S1; John Smith; 1Main St, Stillwater; 5:

4.Insert new data to the flat-file table
Write:Record
For example
Write:S1; John Smith; 1 Main St, Stillwater; 5:

5.Delete a record from the flat-file table
Delete:Primary_Key
For example
Delete:S10
The whole record is deleted

6.Output the whole table
Output
The whole table is output

::Sample example of an input file for Demo.java::
STUDENT (Student_Id (Primary Key): STRING, Name: STRING, Address: STRING,
Number_Of_Credit_Hours_Completed: INTEGER)
Write: S1; John Smith; 1Main St, Stillwater; 5:
Write: S7; Ashok Doshi; 5 Maple Ave, Tulsa; 57:
Write: S4; Prithi Kumar; 256 Denton Place, Stillwater; 10:
Write: S9; Mary John; 34 Hightower Rd, Tulsa; 100:
Read: Number_Of_Credit_Hours_Completed - 10
Read: Student_Id - S7
Write: S10; Mohan Aziz; 15 Main St, Stillwater; 20:
Delete: S10
Output



README FOR Demo2.java:
This file implements  the following SQL queries on  flat-file database implemented on Demo.java.

1.Read attributes from a record
Definition to read a record
<SFW> ::= SELECT <AttrList> FROM <Relation> WHERE <Condition>
<AttrList> ::= <Attribute>, <AttrList> | <Attribute>
<Condition> ::= <Attribute> = AttrVal | <Attribute> > IntVal
For example:
SELECT Name FROM STUDENT WHERE Number_Of_Credit_Hours_Completed = 100

2.Insert or write a new record into a relation
<Insert> ::= INSERT INTO <Relation> VALUES <AttrValList>
<AttrValList> ::= <AttrVal> | <AttrValList>, <AttrVal>
For example:
INSERT INTO STUDENT VALUES 024, Jones, 1 Main St, 1/1/2009, 0

3.Delete a record
<Delete> ::= DELETE FROM <Relation> WHERE <AttrL>
<AttrL> ::= <Attribute> = AttrVal OR <AttrL> | <Attribute> = AttrVal
For example:
DELETE FROM STUDENT WHERE Student_Id = 024 OR Name = Jones, Address = 1 Main St OR Date_of_First_Enrollment = 1/1/2009 OR Number_Of_Credit_Hours_Completed = 0

4.Update a record
<Update> ::= UPDATE <Relation> SET <AttrL> WHERE <Attribute> = AttrVal
<AttrL> ::= <Attribute> = AttrVal
For example
UPDATE STUDENT SET Name = Tamarind WHERE Student_Id = 025



