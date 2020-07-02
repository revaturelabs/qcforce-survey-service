CREATE SCHEMA qcforce_survey;

CREATE TABLE qcforce_survey.response (
	response_id 			int PRIMARY KEY,
	form_id 				int NOT NULL,
	batch_name				varchar NOT NULL,
	response_ts				timestamp NOT NULL,
	response_week			varchar NOT NULL
);

CREATE TABLE qcforce_survey.form (
	form_id					serial PRIMARY KEY,
	source_id				varchar NOT NULL
);

CREATE TABLE qcforce_survey.notification (
	notification_id			serial PRIMARY KEY,
	batch_name				varchar NOT NULL,
	notification_ts			timestamp NOT null
);

CREATE TABLE qcforce_survey.question (
	question_id				serial PRIMARY KEY,
	question_string			varchar NOT NULL,
	form_id					int NOT NULL,
	question_type			varchar NOT NULL
);

CREATE TABLE qcforce_survey.answer (
	answer_id				serial PRIMARY KEY,
	answer_string			varchar,
	response_id				int NOT NULL,
	question_id				int NOT null
);

alter table qcforce_survey.response add constraint FK_response_form_id
	foreign key (form_id) references qcforce_survey.form (form_id) on delete cascade on update cascade;

alter table qcforce_survey.question add constraint FK_question_form_id
	foreign key (form_id) references qcforce_survey.form (form_id) on delete cascade on update cascade;

alter table qcforce_survey.answer add constraint FK_answer_response_id
	foreign key (response_id) references qcforce_survey.response (response_id) on delete cascade on update cascade;

alter table qcforce_survey.answer add constraint FK_answer_question_id
	foreign key (question_id) references qcforce_survey.question (question_id) on delete cascade on update cascade;

CREATE SCHEMA qcforce_training;

CREATE TABLE qcforce_training.batch (
	batch_name				varchar NOT NULL PRIMARY KEY,
	batch_id 				varchar NOT NULL,
	batch_start_date		timestamp NOT NULL,
	batch_end_date 			timestamp NOT NULL,
	batch_skill				varchar NOT NULL,
	batch_location 			varchar NOT NULL,
	batch_type				varchar NOT NULL,
	batch_good_grade		int,
	batch_passing_grade		int,
	batch_current_week		int
);

CREATE TABLE qcforce_training.associate (
	associate_id			serial PRIMARY KEY,
	training_status			varchar,
	associate_email			varchar NOT NULL,
	salesforce_id			int,
	associate_first_name	varchar NOT NULL,
	associate_last_name		varchar NOT NULL,
	flag					varchar,
	associate_start_date	date,
	associate_end_date		date,
	active					boolean,
	batch_name				varchar NOT NULL
);

CREATE TABLE qcforce_training.employee (
	employee_id				serial PRIMARY KEY,
	employee_email			varchar NOT NULL,
	employee_first_name		varchar NOT NULL,
	employee_last_name		varchar NOT NULL
);

CREATE TABLE qcforce_training.employee_batch (
	employee_id				int NOT NULL,
	batch_name				varchar NOT NULL,
	employee_role			varchar,
	deleted_at				date,
	PRIMARY KEY(employee_id, batch_name)
);

alter table qcforce_training.employee_batch add constraint FK_employee_batch_employee_id
	foreign key (employee_id) references qcforce_training.employee (employee_id) on delete cascade on update cascade;

alter table qcforce_training.employee_batch add constraint FK_employee_batch_batch_name
	foreign key (batch_name) references qcforce_training.batch (batch_name) on delete cascade on update cascade;

alter table qcforce_training.associate add constraint FK_associate_batch_name
	foreign key (batch_name) references qcforce_training.batch (batch_name) on delete cascade on update cascade;