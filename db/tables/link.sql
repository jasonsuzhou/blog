create table link(
	id int(11) not null primary key auto_increment,
	name varchar(256) not null,
	url varchar(256) not null
) default charset=utf8;
