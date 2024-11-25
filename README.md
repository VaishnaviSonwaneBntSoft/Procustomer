# ProCUSTOMER
ProCUSTOMER Customer Information System

## Purpose

This project enables customer information management capabilities.

## Features

### Service Features:

* **API Management for :**
  * CoreIdentity
  * MailAddress
  * PhoneNumber
  * EmailAddress
  * NationalIdentity
  * DemographicData

## Build Process

### Prerequisites

- Microsoft Open Jdk 21

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/NimbusPay-Technologies-Inc/ProCUSTOMER.git 
    ```
2. Checkout development branch
   ```bash
    git checkout development
   ```
3. Build the project using Gradle:
    * Windows :
    ```bash
    gradlew.bat clean build
    ```
   * Linux :
   ```bash
   gradlew clean build
   ```

### FrontEnd
1. Change to app directory:
    ```bash
    cd app
    ```
2. Start application
   ```bash
   npm run start
   ```
3. Access Portal
   ```bash
   http://localhost:1234/
   ```

### Usage

1. Access the application at [https://localhost:8080](https://localhost:8080)

   Endpoints:
    * Core Identity
        * `POST    {{Host-URL}}/api/core-identity`
        * `GET     {{Host-URL}}/api/core-identity`
        * `GET     {{Host-URL}}/api/core-identity/{customer-number}`
        * `PUT     {{Host-URL}}/api/core-identity/{customer-number}`
        * `DELETE  {{Host-URL}}/api/core-identity/{customer-number}`
        * `GET     {{Host-URL}}/api/core-identity/customer-data/{customer-number}`

    * Demographic Data
        * `POST    {{Host-URL}}/api/demographic-data`
        * `GET     {{Host-URL}}/api/demographic-data`
        * `GET     {{Host-URL}}/api/demographic-data/{customer-number}`
        * `PUT     {{Host-URL}}/api/demographic-data/{customer-number}`
        * `DELETE  {{Host-URL}}/api/demographic-data/{customer-number}`

    * Email Address
        * `POST    {{Host-URL}}/api/email-address`
        * `GET     {{Host-URL}}/api/email-address`
        * `GET     {{Host-URL}}/api/email-address/{customer-number}`
        * `PUT     {{Host-URL}}/api/email-address/{customer-number}`
        * `DELETE  {{Host-URL}}/api/email-address/{customer-number}`

    * Mail Address
        * `POST    {{Host-URL}}/api/mail-address`
        * `GET     {{Host-URL}}/api/mail-address`
        * `GET     {{Host-URL}}/api/mail-address/{customer-number}`
        * `PUT     {{Host-URL}}/api/mail-address/{customer-number}`
        * `DELETE  {{Host-URL}}/api/mail-address/{customer-number}`

    * National Identity
        * `POST    {{Host-URL}}/api/national-identity`
        * `GET     {{Host-URL}}/api/national-identity`
        * `GET     {{Host-URL}}/api/national-identity/{customer-number}`
        * `PUT     {{Host-URL}}/api/national-identity/{customer-number}`
        * `DELETE  {{Host-URL}}/api/national-identity/{customer-number}`

    * Phone Number
        * `POST    {{Host-URL}}/api/phone-number`
        * `GET     {{Host-URL}}/api/phone-number`
        * `GET     {{Host-URL}}/api/phone-number/{customer-number}`
        * `PUT     {{Host-URL}}/api/phone-number/{customer-number}`
        * `DELETE  {{Host-URL}}/api/phone-number/{customer-number}`


## API Collection
- Download Collection [ProCUSTOMER-Postman-Collection](https://github.com/NimbusPay-Technologies-Inc/NimbusPay-Project-Artifacts/tree/1aac2cfbdfeea33b9c72f7284d6251afe7bb5906/ProCUSTOMER/Postman-Collection)
- Import into Postman
- Change {Host-URL} variable to actual host

## Swagger
- Url:
```bash
    {Host-URL}/swagger-ui/index.html
 ```
- change {Host-URL} to actual host address.eg
```bash
    https://localhost:8080/swagger-ui/index.html
 ```

## Database script
- To check changes in Database follow below script
- <Project-Root>/src/main/resources/db/migration

## TLS settings
- Add JavaKeyStore (JKS) file in project structure at src->main->resources folder.
- STEPS 
  - Generate JKS file from JDK using below command.
  ```bash 
  keytool -genkey -alias procustomer -storetype JKS -keyalg RSA -keysize 2048 -validity 3650 -keystore procustomerkey.jks
  ```
  - Note that the name 'procustomerkey.jks' should match with value of 'server.ssl.key-store' in application.properties file 
  - Add generated file into src->main->resources folder.

-----