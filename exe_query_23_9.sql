


ALTER TABLE `dbvivmallt`.`tb_order_detail` 
ADD COLUMN `property` VARCHAR(245) NULL DEFAULT '' AFTER `old_price`,
ADD COLUMN `color` VARCHAR(245) NULL DEFAULT '' AFTER `property`;



CREATE TABLE `dbvivmallt`.`tb_vivmall_account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

  
  CREATE TABLE dbvmall.`tb_news` (
  `id` varchar(25) NOT NULL,
  `title` varchar(125) DEFAULT NULL,
  `short_description` text,
  `content` varchar(45) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `modifyer` varchar(45) DEFAULT NULL,
  `news_catgory` int(11) DEFAULT NULL,
  `isvisible` bit(1) DEFAULT b'1',
  `isdelete` bit(1) DEFAULT b'0',
  `img`  VARCHAR(245) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dbvmall`.`tb_catgorynews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `isvisible` BIT(1) NULL DEFAULT 1,
  `create_date` DATETIME NULL DEFAULT NULL,
  `modify_date` DATETIME NULL DEFAULT NULL,
  `creator` VARCHAR(45) NULL DEFAULT NULL,
  `modifyer` VARCHAR(45) NULL DEFAULT NULL,
  `img`  VARCHAR(245) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));