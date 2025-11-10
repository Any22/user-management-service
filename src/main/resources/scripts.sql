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

  "firstName": "George",
  "lastName": "Mayo",
  "postalAddress": {
    "addressLine": "123 Main St",
    "city": "Springfield",
    "postalCode": 6270,
    "state": "SA"
  },
    "contactNumber": 4439383903,
      "accountType": "SAVINGS",
  "emailAddress": "abriel.mayo@example.com",
   "userPassword": "gdj39end"


}


{
  "firstName": "John",
  "lastName": "Doe",
  "postalAddress": {
    "addressLine": "67 Zoe St",
    "city": "Abbotsfield",
    "postalCode": 22370,
    "state": "NSW"
  },

  "emailAddress": "john.doe@example.com",
  "contactNumber": 4357544474,
  "accountType": "CURRENT",
  "userPassword": "johnLik3edc",
}


{
 "userName": "mason",
 "password": "maskd5ksd4"
}
I have run these two queries to check how many duplicate contact numbers I have then update the field to carry unique contact numbers
SELECT contact_number, COUNT(*)
FROM user_data
GROUP BY contact_number
HAVING COUNT(*) > 1;


ALTER TABLE user_data
ADD CONSTRAINT unique_contact_number UNIQUE (contact_number);





--//    public String getUserPassword() {
--//        return "*".repeat(userPassword.length()); // Always return masked value
--//    }
--//
--//
--//    // Optional: If you need the actual password internally
--//    public String getActualPassword() {
--//        return userPassword;
--//    }














































































































































































































