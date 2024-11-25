CREATE TABLE proshared.tenant (
    tenant_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    timestmp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tenant_name VARCHAR(40) NOT NULL UNIQUE,
    tenant_status VARCHAR(26) NOT NULL
);


