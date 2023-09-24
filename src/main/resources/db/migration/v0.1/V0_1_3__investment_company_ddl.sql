CREATE TABLE investment_company (
	id BIGINT NOT NULL,
	name VARCHAR (200) NOT NULL,
    CONSTRAINT pk_investment_company PRIMARY KEY (id),
    CONSTRAINT uk_investment_company_name UNIQUE (name)
);
CREATE SEQUENCE investment_company_seq;