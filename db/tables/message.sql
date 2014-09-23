CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `reply_content` varchar(500) DEFAULT NULL,
  `post_date` datetime,
  `reply_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_ip` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) default charset=utf8;