create table article(
	id int(11) not null primary key auto_increment,
	title varchar(256) not null,
	content text,
	key_words varchar(128),
	previous_url varchar(128),
	next_url varchar(128),
	download_url varchar(128),
	related_articles varchar(1024),
	summary_view_url varchar(256),
	post_date datetime,
	author varchar(32),
	category varchar(64),
	update_date timestamp default 0,
	total int(11) default 0
) default charset=utf8;