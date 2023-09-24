CREATE TABLE asset_category (
	id BIGINT NOT NULL,
	description VARCHAR (200) NOT NULL,
    CONSTRAINT pk_asset_category PRIMARY KEY (id),
    CONSTRAINT uk_asset_category_description UNIQUE (description)
);
CREATE SEQUENCE asset_category_seq;