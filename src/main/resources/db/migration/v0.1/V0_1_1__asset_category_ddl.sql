CREATE TABLE asset_category (
	id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	description VARCHAR(200),
	CONSTRAINT asset_category_pk PRIMARY KEY (id),
	CONSTRAINT asset_category_uk UNIQUE (description)
);