this project contains jdbc crud operations 
DB details 
tables :
Employee (empId varchar2(7) primary key,empName varchar2(15) not null,designation varchar2(15) not null)
Address(zipcode varchar2(10) primary key,street varchar2(15),area varchar2(15),district varchar2(15) not null)
EmployeeAddress(empId varchar2(7) ,zipcode varchar2(10),primary key(empId,zipcode))

I used maven for building this app  

i created lib folder and added ojdbc14.jar because if added this jar as maven dependency in pom.xml, due to some licence 
problems ,it is not downloading to project class path.
i configured ojdbc14 jar manually  