ALTER TABLE financial_asset ADD COLUMN name_in_statement VARCHAR (200);
ALTER TABLE financial_asset ADD COLUMN order_in_statement INTEGER DEFAULT 0;
