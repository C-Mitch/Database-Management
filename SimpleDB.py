#Chase Mitchell
#002274657
#Mitch213@mail.chapman.edu
#CPSC-408-01
#Assignment2

import sqlite3

def getAll(x):
    c.execute("SELECT * FROM Student")
    result = c.fetchall()
    for x in result:
        print(x)

def getMajor(x):
    c.execute("SELECT FirstName, LastName, Major FROM Student WHERE Major = 'CPSC'")
    result = c.fetchall()
    for x in result:
        print(x)

def getGPA(x):
    c.execute("SELECT FirstName, LastName, GPA FROM Student WHERE GPA >= 3.5")
    result = c.fetchall()
    for x in result:
        print(x)

def getAdvisor(x):
    c.execute("SELECT FirstName, LastName, FacultyAdvisor FROM Student WHERE FacultyAdvisor = 'Rene'")
    result = c.fetchall()
    for x in result:
        print(x)

conn = sqlite3.connect('StudentDB.db')
c = conn.cursor()

c.execute("SELECT name FROM sqlite_master WHERE type='table'")
test = c.fetchone()
if test[0] != 'Student':
    c.execute('''CREATE TABLE Student (
                StudentId integer primary key autoincrement,
                FirstName varchar(25),
                LastName varchar(25),
                GPA Numeric,
                Major varchar(10),
                FacultyAdvisor varchar(25)
                )''')
else:
    print('Table [Student] Created')

x = ''
getAll(x)
getGPA(x)
getMajor(x)
getAdvisor(x)





conn.commit()

conn.close()
