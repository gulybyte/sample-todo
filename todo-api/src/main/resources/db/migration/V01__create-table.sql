CREATE TABLE todo (
	id bigserial NOT NULL,
	created_date timestamp(6) NULL,
	description varchar(350) NULL,
	done bool NULL,
	done_date timestamp(6) NULL,
	order_todo timestamp(6) NULL,
	CONSTRAINT todo_pkey PRIMARY KEY (id)
);

INSERT INTO todo (created_date,description,done,done_date,order_todo) VALUES
	('2022-11-11 12:03:03.604757','Welcome!.',false,NULL,'2022-11-11 12:03:03.604757');
