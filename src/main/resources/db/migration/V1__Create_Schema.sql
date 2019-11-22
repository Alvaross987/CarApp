CREATE SCHEMA IF NOT EXISTS cars AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS cars."user" (
	id serial NOT NULL,
	created_at timestamp NULL,
	email varchar(255) NOT NULL,
	firstname varchar(255) NOT NULL,
	isadmin int4 NOT NULL,
	last_updated timestamp NULL,
	lastname varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	"token" varchar(255) NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT uk_sb8bbouer5wak8vyiiy4pf2bx UNIQUE (username),
	CONSTRAINT user_pkey PRIMARY KEY (id)
);
ALTER TABLE cars."user" OWNER TO postgres;
GRANT ALL ON TABLE cars."user" TO postgres;


CREATE TABLE IF NOT EXISTS cars.brand (
	brand_id serial NOT NULL,
	created_at timestamp NULL,
	last_updated timestamp NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT brand_pkey PRIMARY KEY (brand_id)
);
ALTER TABLE cars.brand OWNER TO postgres;
GRANT ALL ON TABLE cars.brand TO postgres;


CREATE TABLE IF NOT EXISTS cars.country (
	country_id serial NOT NULL,
	created_at timestamp NULL,
	last_updated timestamp NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT country_pkey PRIMARY KEY (country_id)
);
ALTER TABLE cars.country OWNER TO postgres;
GRANT ALL ON TABLE cars.country TO postgres;



CREATE TABLE IF NOT EXISTS cars.car (
	id serial NOT NULL,
	created_at timestamp NULL,
	last_updated timestamp NULL,
	checked int4 NOT NULL,
	color varchar(255) NULL,
	model varchar(255) NULL,
	registration timestamp NOT NULL,
	country_id int4 NOT NULL,
	brand_id int4 NOT NULL,
	CONSTRAINT car_pkey PRIMARY KEY (id),
	CONSTRAINT fk7coo9i18j692gp7loi29t13x9 FOREIGN KEY (country_id) REFERENCES cars.country(country_id),
	CONSTRAINT fkj1mws2ruu9q6k2sa4pwlxthxn FOREIGN KEY (brand_id) REFERENCES cars.brand(brand_id)
);
ALTER TABLE cars.car OWNER TO postgres;
GRANT ALL ON TABLE cars.car TO postgres;