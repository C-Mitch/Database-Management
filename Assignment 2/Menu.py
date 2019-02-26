#Chase Mitchell
#002274657
#Mitch213@mail.chapman.edu
#CPSC-408-01
#Assignment2

import sqlite3

conn = sqlite3.connect('StudentDB.db')
c = conn.cursor()
class Menu:



    def getAll(x):
        c.execute("SELECT * FROM Student")
        result = c.fetchall()
        for x in result:
            print(x)

    def getMajor(x):
        c.execute("SELECT FirstName, LastName, Major FROM Student WHERE Major = ?", ('CPSC', ))
        result = c.fetchall()
        for x in result:
            print(x)

    def getGPA(x):
        c.execute("SELECT FirstName, LastName, GPA FROM Student WHERE GPA >= ?", (3.5, ))
        result = c.fetchall()
        for x in result:
            print(x)

    def getAdvisor(x):
        c.execute("SELECT FirstName, LastName, FacultyAdvisor FROM Student WHERE FacultyAdvisor = ?", ('Rene', ))
        result = c.fetchall()
        for x in result:
            print(x)
