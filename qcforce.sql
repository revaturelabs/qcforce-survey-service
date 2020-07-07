CREATE SCHEMA qcforce_survey;

drop table qcforce_survey.response cascade;
drop table qcforce_survey.form cascade;
drop table qcforce_survey.notification cascade;
drop table qcforce_survey.question cascade;
drop table qcforce_survey.answer cascade;

CREATE TABLE qcforce_survey.response (
	id 			int PRIMARY KEY,
	form_id 				int,
	response_ts				timestamp
);

CREATE TABLE qcforce_survey.form (
	id					serial PRIMARY KEY,
	source_id				varchar,
	created_time       timestamp
);

CREATE TABLE qcforce_survey.notification (
	id			serial PRIMARY KEY,
	batch_name				varchar,
	notification_ts			timestamp
);

CREATE TABLE qcforce_survey.question (
	id				serial PRIMARY KEY,
	question_string			varchar,
	form_id					int
);

CREATE TABLE qcforce_survey.answer (
	id				serial PRIMARY KEY,
	answer_string			varchar,
	response_id				int,
	question_id				int,
	weight                  numeric(10,2)
);

alter table qcforce_survey.response add constraint FK_response_form_id
	foreign key (form_id) references qcforce_survey.form (id) on delete cascade on update cascade;

alter table qcforce_survey.question add constraint FK_question_form_id
	foreign key (form_id) references qcforce_survey.form (id) on delete cascade on update cascade;

alter table qcforce_survey.answer add constraint FK_answer_response_id
	foreign key (response_id) references qcforce_survey.response (id) on delete cascade on update cascade;

alter table qcforce_survey.answer add constraint FK_answer_question_id
	foreign key (question_id) references qcforce_survey.question (id) on delete cascade on update cascade;

--INSERT INTO qcforce_survey.answer (answer_string, question_id)
--VALUES ('2005Nick', 1);
--INSERT INTO qcforce_survey.answer (answer_string, question_id)
--VALUES ('1905Nick', 1);
--INSERT INTO qcforce_survey.answer (answer_string, question_id)
--VALUES ('2005Nick', 1);
--INSERT INTO qcforce_survey.question (question_string)
--VALUES ('What batch are you in?');
--
--
--select distinct(answer_string) from qcforce_survey.answer a where a.question_id = (select id from qcforce_survey.question q where q.question_string = 'What batch are you in?');
--
-- 
select * from qcforce_survey.answer where response_id=66;



