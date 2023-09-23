CREATE TABLE investment_company (
	id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	name VARCHAR(200),
	CONSTRAINT investment_company_pk PRIMARY KEY (id),
	CONSTRAINT investment_company_uk UNIQUE (name)
);