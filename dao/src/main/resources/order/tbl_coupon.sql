create database wx character set utf8mb4;

DROP TABLE IF EXISTS tbl_coupon;
CREATE TABLE tbl_coupon
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `code`   VARCHAR(8) NOT NULL COMMENT '优惠券码',
    `used`   TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已用',
    `type`      VARCHAR(10) NOT NULL COMMENT '券类型',
    `discount`       TINYINT(1) NULL COMMENT '券折扣',
    PRIMARY KEY (id)
) COMMENT = '优惠券';

select * from   tbl_coupon;