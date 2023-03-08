create database wx character set utf8mb4;

DROP TABLE IF EXISTS tbl_order_item;
CREATE TABLE tbl_order_item
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `name`   VARCHAR(50) NOT NULL COMMENT '购买项名称',
    `code`   VARCHAR(64) NOT NULL COMMENT '购买项code',
    `product`      VARCHAR(10) NOT NULL COMMENT '所属产品',
    `price`       BIGINT NULL COMMENT '购买项单价',
    `amount`      INTEGER NULL COMMENT '购买项次数',
    `desc`   VARCHAR(100) NOT NULL COMMENT '购买项内容描述',
    PRIMARY KEY (id)
) COMMENT = '购买项信息';

INSERT INTO tbl_order_item (amount, name, code, product, price, `desc`)values (10, '0.01元10次测试对话资源包','chat_1',
                                                                               'CHATGPT', 1,
                                                                       '测试对话资源包');
INSERT INTO tbl_order_item (amount, name, code, product, price, `desc`)values (10, '1元10次对话资源包','chat_100', 'CHATGPT',
                                                                               100,
                                                                       '1元10次ChatGpt对话次数，成功回答扣减次数，不成功不扣减');

INSERT INTO tbl_order_item (amount, name, code, product, price, `desc`)values (100, '5元100次对话资源包','chat_500',
                                                                               'CHATGPT', 500,
                                                                       '5元100次ChatGpt对话次数，成功回答扣减次数，不成功不扣减');
INSERT INTO tbl_order_item (amount, name, code, product, price, `desc`) values (300, '10元300次对话资源包','chat_1000',
                                                                                'CHATGPT', 1000,
                                                                        '10元300次ChatGpt对话次数，成功回答扣减次数，不成功不扣减');
INSERT INTO tbl_order_item (amount, name, code, product, price, `desc`) values (500, '15元500次对话资源包','chat_1500',
                                                                                'CHATGPT', 1500,
                                                                        '15元500次ChatGpt对话次数，成功回答扣减次数，不成功不扣减');

INSERT INTO tbl_order_item (amount, name, code, product, price, `desc`) values (10, '5元10张作画资源包','draw_500', 'CHATGPT',
                                                                                500,
                                                                        '5元10张ChatGpt作画次数，成功出图扣减次数，不成功不扣减');
INSERT INTO tbl_order_item (amount, name, code, product, price, `desc`) values (30, '10元30张作画资源包', 'draw_1000',
                                                                                'CHATGPT', 1000,
                                                                        '10元30张ChatGpt作画次数，成功出图扣减次数，不成功不扣减');
select * from tbl_order_item;