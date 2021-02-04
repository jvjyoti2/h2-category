# internetBankingApplication


Internet Banking Application -

Prerequisite- 
Maven should be installed.

Follow the below commands-
1. Clone the project from https://github.com/jvjyoti2/h2-category.git
2. Open as new project using pom.xl under application on the IDE of your choice.
3. Go to IDE terminal and run "mvn clean install". This command will create a jar under .m2 folder.
4. Run the application using class file "H2CategoryApplication". Now the application is hosted on localserver.
5. Below are the exposed endpoints-
	a. localhost:8080/transaction/{category} - for all the transactions for a given category- latest first
	b. localhost:8080/outgoings - for Total outgoing per category
	c. localhost:8080/transaction/avg/{category} - Monthly average spend in a given category
	d. localhost:8080/transaction/high/{category}/{year} - Highest spend in a given category, for a given year
	e. localhost:8080/transaction/low/{category}/{year} - Lowest spend in a given category, for a given year