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
                print("Please Eneter Valid Option")
        return option

    #Option 1
    def displayAll():
        c.execute("SELECT * FROM Student")
        result = c.fetchall()
        for x in result:
            print(x)

    #Option 5
    def search():
        #Search by Major
        while True:
            major = input("\nSearch by Major? ('Y' / 'N') ")
            if major.upper() == 'Y':
                queryA = input("Enter major to search: ")
                c.execute("SELECT FirstName, LastName, Major FROM Student WHERE Major = ?", (queryA.upper(), ))
                result = c.fetchall()
                for x in result:
                    print(x)
            elif major.upper() == 'N':
                break
            else:
                print("Please Eneter Valid Option")
        #Search by GPA
        while True:
            gpa = input("\nSearch by GPA? ('Y' / 'N') ")
            if gpa.upper() == 'Y':
                queryB = 0.0;
                while True:
                    try:
                        queryB = float(input("Enter GPA to search: "))
                        if queryB < 0.0:
                            raise ValueError()
                        break
                    except ValueError:
                        print("Enter Valid GPA Value")
                c.execute("SELECT FirstName, LastName, GPA FROM Student WHERE GPA = ?", (queryB, ))
                result = c.fetchall()
                for x in result:
                    print(x)
            elif major.upper() == 'N':
                break
            else:
                print("Please Eneter Valid Option")
        #Search by Advisor
        while True:
            advisor = input("\nSearch by Advisor? ('Y' / 'N') ")
            if advisor.upper() == 'Y':
                queryC = input("Enter advisor to search: ")
                c.execute("SELECT FirstName, LastName, FacultyAdvisor FROM Student WHERE FacultyAdvisor = ?", (queryC, ))
                result = c.fetchall()
                for x in result:
                    print(x)
            elif major.upper() == 'N':
                break
            else:
                print("Please Eneter Valid Option")
