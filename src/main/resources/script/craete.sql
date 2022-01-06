create table if not exists days
(
	id bigint not null
		constraint days_pkey
			primary key,
	name varchar(10) not null
);

alter table days owner to postgres;

create table if not exists schedules
(
	id bigint not null
		constraint class_schedule_pkey
			primary key,
	day_id bigint not null
		constraint days_id_fkey
			references days,
	group_id bigint not null
);

alter table schedules owner to postgres;

create table if not exists schedules_subjects
(
	schedule_id bigint not null
		constraint schedules_id_fkey
			references schedules,
	subject_id bigint not null
);

alter table schedules_subjects owner to postgres;

create table if not exists classrooms
(
	id bigint not null
		constraint classrooms_pkey
			primary key,
	number varchar(255)
);

alter table classrooms owner to postgres;

create table if not exists groups
(
	id bigint not null
		constraint groups_pkey
			primary key,
	name varchar(255)
);

alter table groups owner to postgres;

create table if not exists students
(
	id bigint not null
		constraint students_pkey
			primary key,
	first_name varchar(255),
	second_name varchar(255),
	group_id bigint
		constraint fkmsev1nou0j86spuk5jrv19mss
			references groups
);

alter table students owner to postgres;

create table if not exists subjects
(
	id bigint not null
		constraint subjects_pkey
			primary key,
	name varchar(255),
	classroom_id bigint
		constraint fkerwwo9vivn43v020xukwla4iw
			references classrooms
);

alter table subjects owner to postgres;
