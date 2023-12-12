CREATE TABLE investment_position (
	id BIGINT NOT NULL,
	financial_asset_id BIGINT NOT NULL,
	position_date DATE NOT NULL,
	position_value NUMERIC (12, 3) NOT NULL DEFAULT 0,
    CONSTRAINT pk_investment_position PRIMARY KEY (id),
    CONSTRAINT fk_investment_position_financial_asset_id FOREIGN KEY (financial_asset_id) REFERENCES financial_asset (id) ON DELETE CASCADE,
    CONSTRAINT uk_investment_position UNIQUE (financial_asset_id, position_date)
);
CREATE SEQUENCE investment_position_seq;