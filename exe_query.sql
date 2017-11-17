insert tb_session_product;


ALTER TABLE `dbvmall`.`tb_cookie_product` 
ADD COLUMN `session_id` VARCHAR(45) NULL AFTER `id`;
