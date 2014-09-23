CREATE TABLE `footer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) DEFAULT NULL,
  `access_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;