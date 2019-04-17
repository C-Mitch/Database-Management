#Chase Mitchell
#002274657
#Mitch213@mail.chapman.edu
#CPSC-408-01
#Assignment3

import sys
import csv
from faker import Faker

if len(sys.argv) == 3:
	if sys.argv[2].isdigit():
		rows = int(sys.argv[2])
		with open(sys.argv[1], 'w', newline='') as file:
			faker = Faker()
			columns = ['FirstName', 'LastName', 'DateOfBirth', 'SSN',
			'Address', 'Email', 'Country', 'Job', 'UserName', 'Password']
			csvWriter = csv.DictWriter(file, fieldnames=columns)
			
			csvWriter.writeheader()
			while rows > 0:
			
				csvWriter.writerow({
                'FirstName': faker.first_name(),
                'LastName': faker.last_name(),
				'DateOfBirth': faker.date(pattern="%Y-%m-%d", end_datetime="-10y"),
                'SSN': faker.itin(),
                'Address': faker.street_address(),
				'Email': faker.email(),
                'Country': faker.country(),
                'Job': faker.job().replace(",", ''),
                'UserName': faker.user_name(),
                'Password': faker.password()
                })
				rows -= 1
				
		print("File Generated Successfully")
	else:
		print("Please Pass An Integer Value As The Second Argument At Execution")

else:
	print("Please Input A File Name And Number Of Rows To Be Generated At Execution")