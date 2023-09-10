CREATE TABLE asset_category (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	description TEXT(200),
	CONSTRAINT asset_category_uk UNIQUE (description)
);