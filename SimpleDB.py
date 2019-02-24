#Chase Mitchell
#002274657
#Mitch213@mail.chapman.edu
#CPSC-408-01
#Assignment2

import sqlite3
conn = sqlite3.connect('StudentDB.db')
c = conn.cursor()

c.execute('CREATE TABLE Student (
            StudentId PK INT,
            FirstName varchar(25),
            LastName varchar(25),
            GPA Numeric,
            Major varchar(10),
            FacultyAdvisor varchar(25)
            )')

conn.commit()

conn.close()
