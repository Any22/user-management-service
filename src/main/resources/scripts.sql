DROP TABLE IF EXISTS user_data;
CREATE SEQUENCE customer_id_sequence START 1;
CREATE TABLE user_data (
    user_id BIGINT DEFAULT nextval('customer_id_sequence') PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
	email_address VARCHAR(40) NOT NULL UNIQUE,
	contact_number BIGINT NOT NULL,
	address_line VARCHAR(255) NOT NULL,
    city VARCHAR(100),
	zip_code INT,
    state VARCHAR(100),
    account_type VARCHAR(50) NOT NULL,
    branch_code VARCHAR(50) NOT NULL,
    interest_rates DOUBLE PRECISION NOT NULL
);



{
 "userName": "reddy",
 "userPassword": "gdj39end",
  "firstName": "Gabriel",
  "lastName": "Mayo",
  "emailAddress": "gabriel.mayo@example.com",
  "contactNumber": 4439383903,
  "postalAddress": {
    "addressLine": "123 Main St",
    "city": "Springfield",
    "postalCode": 6270,
    "state": "VIC"
  },
  "accountType": "SAVINGS"
}



{
 "userName": "mason",
 "password": "maskd5ksd4"
}





















































































































































































































