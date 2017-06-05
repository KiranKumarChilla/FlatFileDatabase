# FlatFileDatabase
Developed a file systems Database using Java which handles all the DML commands as in SQL like Create, Update, Insert and Delete

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
