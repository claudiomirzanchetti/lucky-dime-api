CREATE TABLE financial_asset (
	id BIGINT NOT NULL,
	name VARCHAR (200) NOT NULL,
	ticker_symbol VARCHAR (20),
	investment_company_id BIGINT NOT NULL,
	asset_category_id BIGINT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT FALSE,
    announced_return VARCHAR (200),
    maturity_date DATE,
    reference VARCHAR (200),
    note VARCHAR (2000),
    CONSTRAINT pk_financial_asset PRIMARY KEY (id),
    CONSTRAINT uk_financial_asset_name UNIQUE (name),
    CONSTRAINT uk_financial_asset_ticker_symbol UNIQUE (ticker_symbol)
);
CREATE SEQUENCE financial_asset_seq;