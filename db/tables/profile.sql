create table profile(
	id int(11) not null primary key auto_increment,
	username varchar(256),
	realname varchar(256),
	birthday varchar(32),
	party varchar(32),
	current_location varchar(32),
	career varchar(64),
	book varchar(64),
	hoppy varchar(64),
	qq varchar(64),
	qq_group varchar(64),
	mail_to varchar(64),
	img_url varchar(128),
	about_me text,
	about_blog text,
	copy_right varchar(256),
	years varchar(16) default '4+ years',
	total_access int(11) default 0

) default charset=utf8;