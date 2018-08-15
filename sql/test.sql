/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.20-log : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `t_mean` */

DROP TABLE IF EXISTS `t_mean`;

CREATE TABLE `t_mean` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_mean` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `name` varchar(50) NOT NULL,
  `age` int(10) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `addr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`name`,`age`,`sex`,`tel`,`email`,`addr`) values ('Jack Huang',24,'男','564987','231354','5449687'),('Tom',33,'男','45654','2432','32玩儿'),('凤姐',22,'女','95463','930012617@qq.com','qwesdadasd'),('华盛顿',100,'男','1855','943003097@qq.com','asdas'),('史珍香',25,'女','185523423','943003097@qq.com','aasd'),('周杰伦',43,'男','18552342354','943003097@qq.com','asd'),('波多野结衣',28,'女','9564','6532','325698'),('潮汐海灵',55,'男','35654987','1312','234234234'),('牛耀伦',88,'男','18552342354','943003097@qq.com','asdasdqwe'),('肾结石',60,'男','18552342354','943003097@qq.com','qweqw'),('诺克萨斯',523,'男','795465','24234234','234234234'),('马化腾',50,'男','18552342354','943003097@qq.com','vfdgsdf');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
