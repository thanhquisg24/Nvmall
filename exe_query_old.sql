ALTER TABLE tb_order_detail
ADD property VARCHAR(245);
ALTER TABLE tb_order_detail
ADD color VARCHAR(245);
ALTER TABLE tb_cookie_product
ADD property VARCHAR(245);
ALTER TABLE tb_cookie_product
ADD color VARCHAR(245);
ALTER TABLE tb_cookie_product
DROP PRIMARY KEY;
ALTER TABLE tb_cookie_product
ADD CONSTRAINT pk_Product PRIMARY KEY (member_id,customer_id,product_id,property,color);
ALTER TABLE tb_order_detail
DROP PRIMARY KEY;
ALTER TABLE tb_order_detail
ADD CONSTRAINT pk_Detail PRIMARY KEY (order_id,customer_id,product_id,property,color);
ALTER TABLE tb_customer
ADD phone varchar(225);
CREATE TABLE `tb_product_view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(225) DEFAULT NULL,
  `product_id` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tb_about` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `lang` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

INSERT INTO `tb_about` (`description`, `lang`) VALUES ('<p style=\"text-align:justify\">Tốc độ ph&aacute;t triển nhanh ch&oacute;ng của c&aacute;c mạng viễn th&ocirc;ng c&ugrave;ng sự ph&aacute;t triển c&ocirc;ng nghệ ng&agrave;y c&agrave;ng cao trong những năm gần đ&acirc;y<img alt=\"\" src=\"http://vivmall.vn/Nvivmall/upload/ckeditor/IMG_4205.jpg\" style=\"float:right; height:111px; width:166px\" />&nbsp;đang tạo ra một k&ecirc;nh b&aacute;n h&agrave;ng l&yacute; tưởng cho nhiều doanh nghiệp, v&agrave; thuận lợi cho Qu&yacute; kh&aacute;ch h&agrave;ng c&oacute; nhu cầu mua sắm tại nh&agrave;.&nbsp;V&igrave; thế, ch&uacute;ng t&ocirc;i đ&atilde; thiết lập một k&ecirc;nh mua sắm trực tuyến mang t&ecirc;n Vivmall (Vietnam Internet Virtual Mall) với đa dạng sản phẩm chất lượng v&agrave; gi&aacute; cả ph&ugrave; hợp.</p>\n\n<p style=\"text-align:justify\">Từ c&aacute;c d&ograve;ng sản phẩm được gia c&ocirc;ng từ gỗ dừa như đồ d&ugrave;ng nh&agrave; bếp, đ&egrave;n ngủ&hellip; với thiết kế tinh tế, độc đ&aacute;o tạo kh&ocirc;ng gian ấm c&uacute;ng cho gia đ&igrave;nh nh&agrave; bạn.</p>\n\n<p style=\"text-align:justify\"><img alt=\"\" src=\"http://vivmall.vn/Nvivmall/upload/ckeditor/maybay.jpg\" style=\"float:left; height:111px; width:168px\" /></p>\n\n<p style=\"text-align:justify\">Đến c&aacute;c sản phẩm c&ocirc;ng nghệ cao như loa Bluetooth với &acirc;m thanh trung thực, sống động; đồng hồ LED với d&acirc;y đeo l&agrave;m bằng cao su mềm, dẻo v&agrave; bền, nhiều m&agrave;u sắc thời trang.</p>\n\n<p style=\"text-align:justify\">Ngo&agrave;i ra ch&uacute;ng t&ocirc;i c&ograve;n cung cấp tay cầm c&oacute; thể chơi được h&acirc;̀u h&ecirc;́t các game tr&ecirc;n h&ecirc;̣ đi&ecirc;̀u hành Android và iOS, m&aacute;y bay điều khiển từ xa được trang bị camera HD để c&oacute; h&igrave;nh ảnh v&agrave; video trong khi đang bay&hellip;</p>\n\n<p style=\"text-align:justify\">&nbsp;</p>\n\n<p style=\"text-align:justify\"><strong>Mua sắm dễ d&agrave;ng v&agrave; thuận tiện:</strong></p>\n\n<p style=\"text-align:justify\">Kh&ocirc;ng c&ograve;n phải lo kẹt xe, đ&ocirc;ng đ&uacute;c v&agrave; xếp h&agrave;ng d&agrave;i chờ đợi! Giờ đ&acirc;y, bạn c&oacute; thể mua sắm bất cứ khi n&agrave;o, ở bất cứ đ&acirc;u tr&ecirc;n m&aacute;y t&iacute;nh v&agrave; điện thoại di động của m&igrave;nh.</p>\n\n<p style=\"text-align:justify\">Với dịch vụ chuyển h&agrave;ng nhanh ch&oacute;ng v&agrave; đ&aacute;ng tin cậy, bạn chỉ cần ngồi thư gi&atilde;n tại nh&agrave; v&agrave; chờ nhận h&agrave;ng.</p>\n\n<p style=\"text-align:justify\"><strong>Bảo đảm về chất lượng v&agrave; t&iacute;nh x&aacute;c thực:</strong></p>\n\n<p style=\"text-align:justify\">Tất cả c&aacute;c sản phẩm được giao dịch tr&ecirc;n Vivmall đều được đảm bảo l&agrave; sản phẩm ch&iacute;nh h&atilde;ng, mới, kh&ocirc;ng khiếm khuyết hay hư hỏng.</p>\n\n<p style=\"text-align:justify\"><strong>MỤC TI&Ecirc;U VIVMALL:</strong></p>\n\n<ul>\n	<li style=\"text-align:justify\">Chất lượng, Uy t&iacute;n v&agrave; Chuy&ecirc;n nghiệp.</li>\n	<li style=\"text-align:justify\">Mang đến cho kh&aacute;ch h&agrave;ng sự tin tưởng v&agrave; h&agrave;i l&ograve;ng khi mua sắm tại nh&agrave;.</li>\n	<li style=\"text-align:justify\">K&ecirc;nh cung cấp th&ocirc;ng tin v&agrave; tư vấn sản phẩm tốt nhất cho kh&aacute;ch h&agrave;ng.</li>\n	<li style=\"text-align:justify\">Mở rộng phạm vi b&aacute;n h&agrave;ng tr&ecirc;n to&agrave;n quốc.</li>\n</ul>\n\n<p style=\"text-align:justify\">Vivmall lu&ocirc;n cập nhật những sản phẩm đa dạng mẫu m&atilde; đ&aacute;p ứng thị hiếu ti&ecirc;u d&ugrave;ng của Qu&yacute; kh&aacute;ch, ch&uacute;ng t&ocirc;i cam kết về chất lượng sản phẩm v&agrave; lu&ocirc;n lu&ocirc;n phục vụ kh&aacute;ch h&agrave;ng một c&aacute;ch tốt nhất!</p>\n', 'VN');

CREATE TABLE `tb_location` (
  `location_id` varchar(50) NOT NULL,
  `location_name` varchar(45) DEFAULT NULL,
  `parent` varchar(45) DEFAULT NULL,
  `isvisible` bit(1) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE tb_order
ADD location varchar(225);
ALTER TABLE tb_member
ADD location varchar(225);
CREATE TABLE `tb_fee` (
  `id_fee` varchar(50) NOT NULL,
  `fee_name` varchar(45) DEFAULT NULL,
  `fee` float DEFAULT NULL,
  `Moption` float DEFAULT NULL,
  PRIMARY KEY (`id_fee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE tb_order
ADD fee_type varchar(225);
ALTER TABLE tb_order
ADD amount FLOAT;
ALTER TABLE tb_order
ADD fee FLOAT;
ALTER TABLE tb_order
ADD total_amount FLOAT;
ALTER TABLE tb_payment_method
ADD type varchar(225);
INSERT INTO `tb_payment_method` VALUES ('01','Thanh toán tiền mặt khi nhận hàng','thanh toan truc tiep','OFFLINE'),('02','Thẻ ATM đăng ký internet banking',NULL,'ONLINE'),('03','Thanh toán bằng thẻ quốc tế Visa, MasterCard ..',NULL,'ONLINE');
CREATE TABLE `tb_config` (
  `id` varchar(50) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `isvisible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into tb_config(id,name,isvisible)
values('PUBLISH','ONLINE || OFFLINE',FALSE);

CREATE TABLE `tb_product_later` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `customer_id` varchar(45) DEFAULT NULL,
  `product_id` varchar(45) DEFAULT NULL,
  `property` varchar(245),
  `color` varchar(45),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
CREATE TABLE `dbvmall`.`tb_nation` (
  `nation_id` VARCHAR(50) NOT NULL,
  `nation_name` VARCHAR(225) NULL,
  `isvisible` BIT NULL,
  `isdelete` BIT NULL,
  PRIMARY KEY (`nation_id`));
  ALTER TABLE `dbvmall`.`tb_customer` 
ADD COLUMN `nation` VARCHAR(245) NULL AFTER `phone`,
ADD COLUMN `address` TEXT NULL AFTER `nation`,
ADD COLUMN `product_description` TEXT NULL AFTER `address`;


CREATE TABLE `tb_faq` (
  `id`  VARCHAR(20) NOT NULL,
  `content` text,
  `isvisible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `dbvmall`.`tb_faq` (`id`, `content`, `isvisible`) VALUES ('QUESTION', '<b>Question</b>', 1);
INSERT INTO `dbvmall`.`tb_faq` (`id`, `content`, `isvisible`) VALUES ('GUIDE_BUY', '<b>GUIDE_BUY</b>', 1);
INSERT INTO `dbvmall`.`tb_faq` (`id`, `content`, `isvisible`) VALUES ('SMART_BUY', '<b>SMART_BUY</b>', 1);
INSERT INTO `dbvmall`.`tb_faq` (`id`, `content`, `isvisible`) VALUES ('SAFE_BUY', '<b>SAFE_BUY</b>', 1);
INSERT INTO `dbvmall`.`tb_faq` (`id`, `content`, `isvisible`) VALUES ('PAY_METHOD', '<b>PAY_METHOD</b>', 1);

