create database wx character set utf8mb4;

DROP TABLE IF EXISTS tbl_order_item;
CREATE TABLE tbl_order_item
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `code`   VARCHAR(64) NOT NULL COMMENT '购买项code',
    `product`      VARCHAR(10) NOT NULL COMMENT '所属产品',
    `price`       BIGINT NULL COMMENT '购买项单价',
    `desc`   VARCHAR(30) NOT NULL COMMENT '购买项描述',
    PRIMARY KEY (id)
) COMMENT = '购买项信息';

INSERT INTO tbl_order_item (code, product, price, `desc`)values ('chat_200', 'CHATGPT', 500, '5元200次');
INSERT INTO tbl_order_item (code, product, price, `desc`) values ('chat_500', 'CHATGPT', 1000, '10元500次');

INSERT INTO tbl_order_item (code, product, price, `desc`) values ('draw_10', 'CHATGPT', 1000, '5元10张');
INSERT INTO tbl_order_item (code, product, price, `desc`) values ('draw_20', 'CHATGPT', 1000, '10元30张');
