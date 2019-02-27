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

    #Option 2
    def insert():
        print()
        #Loop until user confirms info
        confirm = False
        info = []
        while not confirm:
            #Input data into list
            info.append(input("Enter First Name: "))
            info.append(input("Enter Last Name: "))
            x = 0.0;
            while True:
                try:
                    x = float(input("Enter GPA: "))
                    if x < 0.0:
                        raise ValueError()
                    break
                except ValueError:
                    print("Enter Valid GPA Value")
            info.append(x)
            m = input("Enter Major: ")
            info.append(m.upper())
            info.append(input("Enter Faculty Advisor: "))

            #Ask for confirmation
            print()
            print("First Name: ", info[0],"\nLast Name: ", info[1],
             "\nGPA: ", info[2], "\nMajor: ", info[3], "\nFaculty Advisor: ", info[4])
            print()
            while True:
                valid = input("Are these values correct? ('Y' / 'N') ")
                if valid.upper() == "Y":
                    confirm = True
                    c.execute("INSERT INTO Student(FirstName, LastName, GPA, Major, FacultyAdvisor) "
                                "VALUES(?,?,?,?,?);", info)
                    conn.commit()
                    print("Student entered successfully!")
                    break
                elif valid.upper() == "N":
                    print()
                    print("Please reenter values!")
                    info.clear()
                    break
                else:
                    print("Invalid option!")

    #Option 3
    def update():
        id = 0
        while True:
            try:
                id = int(input("Enter ID to update: "))
                break
            except ValueError:
                print("Enter Valid ID Value")
        c.execute("SELECT StudentId from Student WHERE StudentId = {}".format(id))
        update = c.fetchall()
        if update == []:
            print('\nUpdate Failed - Student Does Not Exist')
        else:
            choice = ''
            while True:
                choice = input("Update Major or Advisor? ('Major' / 'Advisor') ")
                if choice.upper() == 'MAJOR':
                    choice.capitalize()
                    break
                elif choice.upper() == 'ADVISOR':
                    choice = "FACULTYADVISOR"
                    break
                else:
                    print("Please Enter Valid Option")
            val = input('\nInput New Value: ')

            c.execute("UPDATE Student SET {0} = '{1}' WHERE StudentId = {2}".format(choice, val, id))
            conn.commit()
            print('Student Updated Successfully')


    #Option 4
    def delete():
        id = 0
        while True:
            try:
                id = int(input("Enter ID to delete: "))
                break
            except ValueError:
                print("Enter Valid ID Value")
        c.execute("SELECT StudentId from Student WHERE StudentId = {}".format(id))
        deletion = c.fetchall()
        if deletion == []:
            print('\nDeletion Failed - Student Does Not Exist')
        else:
            c.execute("SELECT * from Student WHERE StudentId = {}".format(id))
            print('\n', c.fetchall())
            choice = input("\nDelete Student? ('Y' / 'N') ")
            if choice.upper() == 'Y':
                c.execute("DELETE FROM Student WHERE StudentId = {}".format(id))
                conn.commit()
                print('\nStudent Deleted')
            elif choice.upper() == 'N':
                print('\nDeletion Cancled')
            else:
                print("Please Enter Valid Option")

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
                print("Please Enter Valid Option")
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
                print("Please Enter Valid Option")
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
                print("Please Enter Valid Option")
