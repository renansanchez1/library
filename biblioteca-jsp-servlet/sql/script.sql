CREATE TABLE usuario(
	id bigserial NOT NULL,
	active boolean,
	cpf character varying(30),
	date_birth date,
	email character varying(50),
	login character varying(50),
	name character varying(255),
	password character varying(50),
	CONSTRAINT user_pkey PRIMARY KEY (id)	
);