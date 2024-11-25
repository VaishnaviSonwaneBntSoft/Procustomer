CREATE TABLE procustomer.core_identity (
    consumer_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_number BIGINT NOT NULL UNIQUE,
    tenant_name VARCHAR(40),
    first_name VARCHAR(40),
    middle_name1 VARCHAR(40),
    middle_name2 VARCHAR(40),
    middle_name3 VARCHAR(40),
    family_surname VARCHAR (40),
    date_of_birth DATE,
    place_of_birth VARCHAR(40),
    mothers_maiden_name VARCHAR(40),
    deleted bool NOT NULL,
    FOREIGN KEY (tenant_name) REFERENCES proshared.tenant(tenant_name)
);
 
CREATE TABLE procustomer.mail_address (
    address_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address_type VARCHAR(25) NOT NULL,
    customer_number BIGINT NOT NULL,
    building_number VARCHAR(10),
    address_line1 VARCHAR(40),
    address_line2 VARCHAR(40),
    address_line3 VARCHAR(40),
    place_location VARCHAR(40),
    state_county VARCHAR(40),
    country VARCHAR(40),
    deleted bool NOT NULL,
    FOREIGN KEY (customer_number) REFERENCES procustomer.core_identity(customer_Number)
);
 
CREATE TABLE procustomer.phone_number (
    phone_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_number BIGINT NOT NULL,
    primary_phone_number VARCHAR(18),
    primary_phone_status VARCHAR(10),
    secondary_phone_number VARCHAR(18),  
    is_mobile BOOLEAN,
    deleted bool NOT NULL,
    FOREIGN KEY (customer_number) REFERENCES procustomer.core_identity(customer_number)
);
 
CREATE TABLE procustomer.email_address (
    email_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_number BIGINT NOT NULL,
    email_address VARCHAR(50),
    email_status VARCHAR(10),
    is_active BOOLEAN,
    deleted bool NOT NULL,
    FOREIGN KEY (customer_number) REFERENCES procustomer.core_identity(customer_number)
);
 
 
CREATE TABLE procustomer.national_identity (
    national_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_number BIGINT NOT NULL,
    passport_number VARCHAR(18),
    driving_license_number VARCHAR(20),
    national_tax_id VARCHAR(20),
    deleted bool NOT NULL,
    FOREIGN KEY (customer_number) REFERENCES procustomer.core_identity(customer_number)
);
 
 
CREATE TABLE procustomer.demographic_data (
    demographic_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    customer_number BIGINT NOT NULL,
    marital_status VARCHAR(10),
    declared_annual_income VARCHAR(10),
    occupation VARCHAR(25),
    bal_opening_debt INT,
    num_loans SMALLINT,
    num_credit_cards SMALLINT,
    tot_exist_credit_limit INT,
    deleted bool NOT NULL,
    FOREIGN KEY (customer_number) REFERENCES procustomer.core_identity(customer_number)
);