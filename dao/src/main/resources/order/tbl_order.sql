create database wx character set utf8mb4;

DROP TABLE IF EXISTS tbl_order;
CREATE TABLE tbl_order
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `user_id`   VARCHAR(64) NOT NULL COMMENT '用户id',
    `order_id`      VARCHAR(64) NOT NULL COMMENT '订单id',
    `item_code`       VARCHAR(64) NULL COMMENT '订单项code',
    `pay_status`    TINYINT(1) NULL DEFAULT 0 COMMENT '支付状态',
    `price`       INTEGER     NULL COMMENT '订单金额，单位分',
    `coupon_code`    VARCHAR(8) NULL COMMENT '如果使用了优惠券，记录优惠券code',
    PRIMARY KEY (id)
) COMMENT = '购买订单';

CREATE INDEX idx_userId ON tbl_order(user_id);
CREATE INDEX idx_orderId ON tbl_order(order_id);

select * from  tbl_order;