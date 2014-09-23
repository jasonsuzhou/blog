create table auditor(
	id int(11) not null primary key auto_increment,
	`type` varchar(64),
	ip varchar(64),
	is_black varchar(2),
	attemp_times int(11) default 0,
	total_attemp_times int(11) default 0,
	success varchar(2),
	remark varchar(512)
) default charset=utf8;