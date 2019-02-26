#Chase Mitchell
#002274657
#Mitch213@mail.chapman.edu
#CPSC-408-01
#Assignment2

from Menu import Menu

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
