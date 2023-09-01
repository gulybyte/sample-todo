CREATE TABLE todo (
	id bigserial NOT NULL,
	created_date timestamp(6) NULL,
	description varchar(350) NULL,
	done bool NULL,
	done_date timestamp(6) NULL,
	order_todo timestamp(6) NULL,
	CONSTRAINT todo_pkey PRIMARY KEY (id)
);
