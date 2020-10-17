create table blacklist
(
	id int auto_increment
		primary key,
	login varchar(20) null,
	constraint blacklist_login_uindex
		unique (login)
);

create table roles
(
	id int not null
		primary key,
	name varchar(10) not null,
	constraint name
		unique (name)
);

create table statuses
(
	id int not null
		primary key,
	name varchar(10) not null,
	constraint name
		unique (name)
);

create table tours
(
	id int auto_increment
		primary key,
	type varchar(10) null,
	hotel varchar(20) null,
	price int unsigned null,
	human_amount tinyint unsigned null,
	isFire tinyint(1) null,
	status int null,
	discount tinyint unsigned null,
	user_id int null,
	constraint tours_statuses_id_fk
		foreign key (status) references statuses (id)
			on delete cascade
);

create table users
(
	id int auto_increment
		primary key,
	login varchar(20) not null,
	password varchar(64) not null,
	role_id int not null,
	locale varchar(10) default 'en' null,
	constraint login
		unique (login),
	constraint users_roles__fk
		foreign key (role_id) references roles (id)
			on delete cascade
);

create table users_info
(
	id int auto_increment
		primary key,
	name varchar(10) null,
	surname varchar(20) null,
	gender varchar(10) null,
	email varchar(64) null,
	city varchar(10) null,
	constraint users_info_users_id_fk
		foreign key (id) references users (id)
			on delete cascade
);

