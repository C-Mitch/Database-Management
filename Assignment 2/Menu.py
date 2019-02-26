#Chase Mitchell
#002274657
#Mitch213@mail.chapman.edu
#CPSC-408-01
#Assignment2

import sqlite3

conn = sqlite3.connect('StudentDB.db')
c = conn.cursor()

class Menu:

    def menu():
        print("\nMenu:\n")
        print('''1. Display Students\n
        2. Add Student\n
        3. Update Student\n
        4. Delete Student\n
        5. Search Student\n
        6. Quit''')
        while True:
            try:
                option = int(input("Enter Option: "))
                if option > 6 or option < 1:
                    raise Excep()
                break
            except Excep:
                print("Please Eneter Valid Input")
        return option

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


conn.commit()

conn.close()
