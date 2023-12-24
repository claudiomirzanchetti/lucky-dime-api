ALTER TABLE investment_position ADD COLUMN number_of_shares INTEGER DEFAULT 0;
ALTER TABLE investment_position ADD COLUMN share_price NUMERIC (12, 3) DEFAULT 0;
