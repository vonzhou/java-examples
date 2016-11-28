 CREATE TABLE `profile` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `username` varchar(64) NOT NULL,
   `sex` varchar(10) DEFAULT '',
   `city` varchar(45) DEFAULT '',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人资料';