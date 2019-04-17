##### Chase Mitchell
##### 002274657
##### Mitch213@mail.chapman.edu
##### CPSC-408-01
##### Assignment3

### Fake Data Generator Using Faker
Generates fake data using the [Python faker library](https://github.com/joke2k/faker).  Source is located under the Python folder.

##### Run in the command line with two parameters:
- File name
- Number of rows to generate

###### Example:
```
python fake.csv 20
```

###### Generated Data Headers:
```
FirstName,LastName,DateOfBirth,SSN,Address,Email,Country,Job,JobAddress,UserName,Password,PID
```

### Database Inserter And Normalizer
Using a JDBC connection, the data from the csv file is normalized and inserted into the database.  Connection is configured for use in Google Cloud Platform MySQL Database
Project is run through [Main.java](https://github.com/C-Mitch/Database-Management/tree/master/Assignment%203/Java) and the CSV should be configured to run there, 
i.e. in the same directory.  This can be manually changed in the Main under the filePath variable.

#### Notes:
- Project created in Intellij
- Uses Maven MySQL connector
- If run using Intellij, CSV should be in root directory of project

	