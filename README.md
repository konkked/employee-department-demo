<h1>employee-department-demo</h1>

<p>
This is an application representing relationships between models - provides some statistics on department-wise employee data  like expenses per department, average salary per department, employees count per department, etc.	

There are two teams working on employee-department-demo application. The backend team is responsible for maintaining microservices backend and frontend team is responsible for developing different UIs required. 
	
The application aims to able to provide support for 

		- Add / Update / Delete Departments 
		
		- Add / Update / Delete Employees 
		
		- Employee salary management
		
		- Graphical representation of department-wise dashboards like expenses per department, average salary per department, employees count per department, etc.
		
<h4>Salary Calculation Logic:</h4>

SalaryComponent is dependent upon base salary and can be calculated as baseSalary multiplied by respective factor. i.e. 
 		
<code> SalaryComponent_amount = baseSalary * factor; </code>
	 	
Actual salary can be calculated as sum of all SalaryComponent amounts.

<code> Salary_amount = Sum of all SalaryComponent_amounts; </code>

</p>

<br/>

<h3>PRE-REQUISITES:</h3>

Setup you local environment to work on the codebase:
1. Java 8
2. Git
3. Maven 
4. Any IDE (IntelliJ / Eclipse / VS Code)

<br/>

<h3>INSTRUCTIONS:</h3>

*Note:* 
All required dependencies (ex. spring boot, data jpa, h2, etc) are already added to the POM. The candidate need not add any additional dependency for this assessment.


<h4>Steps:</h4>

	1. Clone from git@github.com:svpmirashi/employee-department-demo.git
	
	2. Import the project to your IDE and build it
	
	3. Work on tasks mentioned Assignments / ToDos section
	
	4. Run the spring boot application - you may see exceptions - fix them
	
	5. The spring boot application is in running state without any exceptions

	6. Open h2 console and login to the same (basic h2 properties have been set in the application)



<h4>Assignment / TODOs:</h4>

As part of backend team, you need to write REST APIs to

    - Get average salary to be paid to given department
    
    - Compare two salaries component-wise
    
    - Update base salary


You will need to do below tasks:

	1. Apply required annotations / add required methods to all repositories
	
	2. Apply required annotations / add required methods to all services
	
	3. Update / re-factor Controller layer 
	
	4. Write basic unit tests to test implemented functionalities

Feel free to re-factor the existing code in case required.

<br/>

Build: 
_mvn clean install_

Run tests: 
_mvn clean test_