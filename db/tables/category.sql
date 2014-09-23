create table category(
	id int(11) not null primary key auto_increment, 
	name varchar(64) not null, 
	total int(11) default 0 
) default charset=utf8;