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
            GPA real not null,
            Major varchar(10) not null,
            FacultyAdvisor varchar(25)
            )''')
print('Table [Student] Connected')


while True:
    select = Menu.menu()

    if select == 1:
        Menu.displayAll()
    elif select == 2:
        Menu.insert()
    elif select == 3:
        Menu.update()
    elif select == 4:
        Menu.delete()
    elif select == 5:
        Menu.search()
    elif select == 6:
        break




conn.commit()

conn.close()
