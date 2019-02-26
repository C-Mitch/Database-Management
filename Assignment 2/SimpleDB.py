#Chase Mitchell
#002274657
#Mitch213@mail.chapman.edu
#CPSC-408-01
#Assignment2

import sqlite3
from Menu import Menu

conn = sqlite3.connect('StudentDB.db')
c = conn.cursor()

c.execute('''CREATE TABLE IF NOT EXISTS Student (
            StudentId integer primary key autoincrement not null,
            FirstName varchar(25) not null,
            LastName varchar(25) not null,
            GPA Numeric real not null,
            Major varchar(10) not null,
            FacultyAdvisor varchar(25)
            )''')
print('Table [Student] Connected')

x = ''
Menu.getAll(x)
Menu.getGPA(x)
Menu.getMajor(x)
Menu.getAdvisor(x)





conn.commit()

conn.close()
