CREATE TABLE investment_company (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	description TEXT(200),
	CONSTRAINT investment_company_uk UNIQUE (description)
);