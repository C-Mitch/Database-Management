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
        print("\nMenu:")
        print('''
        1. Display All Students\n
        2. Add Student\n
        3. Update Student\n
        4. Delete Student\n
        5. Search Student\n
        6. Quit''')
        while True:
            try:
                option = int(input("\nEnter Option: "))
                if option > 6 or option < 1:
                    raise Exception()
                break
            except Exception:
                print("Please Eneter Valid option")
        return option

    #Option 1
    def displayAll():
        c.execute("SELECT * FROM Student")
        result = c.fetchall()
        for x in result:
            print(x)

    #Option 5
    def search():
        getMajor()
        getGPA()
        getAdvisor()


    def getMajor():
        c.execute("SELECT FirstName, LastName, Major FROM Student WHERE Major = ?", ('CPSC', ))
        result = c.fetchall()
        for x in result:
            print(x)

    def getGPA():
        c.execute("SELECT FirstName, LastName, GPA FROM Student WHERE GPA >= ?", (3.5, ))
        result = c.fetchall()
        for x in result:
            print(x)

    def getAdvisor():
        c.execute("SELECT FirstName, LastName, FacultyAdvisor FROM Student WHERE FacultyAdvisor = ?", ('Rene', ))
        result = c.fetchall()
        for x in result:
            print(x)
