#Contact Management

1.	*Build code*
-	mvn clean install
2.	*Start the code*
-	mvn spring-boot:run
3.	*Access to Swagger API documentation*
http://localhost:8080/restaurant/swagger-ui.html
4.	*Consume Contact Rest APIs:*
a.	**Get all contacts with pagination support.**
URL: http://<ip>:<port>/contact?page=<page number>&size=<page size>
HTTP Method: GET
EX: http://localhost:8080/contact?page=0&size=5

b.	**Get a contact by ID**
URL: http://<ip>:<port>/contact/<contact id>
HTTP Method: GET
EX : http://localhost:8080/contact/1

c.	**Create a contact**
URL: http://<ip>:<port>/contact
HTTP Method: POST
Body:
{
  "id": Number,
  "name": String,
  "emailAddress": String,
  "postalAddress": String,
  "telephoneNumber": String
}
EX: http://localhost:8080/contact
{
  "id": 0,
  "name": "Nguyen Ngoc Bao",
  "emailAddress": "ngocbao@gmail.com",
  "postalAddress": "District 7",
  "telephoneNumber": "0342340787"
}

d.	**Update a contact**
URL: http://<ip>:<port>/contact/<contact id>
HTTP Method: PUT
Body:
{
  "id": Number,
  "name": String,
  "emailAddress": String,
  "postalAddress": String,
  "telephoneNumber": String
}
EX: http://localhost:8080/contact/1
{
  "id": 1,
  "name": "Nguyen Ngoc Bao",
  "emailAddress": "ngocbao@gmail.com",
  "postalAddress": "District 7",
  "telephoneNumber": "0342340787"
}

e.	**Delete a contact item by ID**
URL: : http://<ip>:<port>/contact/<contact id>
HTTP Method: DELETE
EX: : http://localhost:8080/contact/1


 
