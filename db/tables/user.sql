create table `user` (
	username varchar(64) not null primary key,
	password varchar(64) not null,
	verify_code varchar(64) not null,
	last_login_time datetime,
	input_error_times int(11) default 0,
	locked varchar(2) default 'N'
) default charset=utf8;
