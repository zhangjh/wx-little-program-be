DROP TABLE IF EXISTS tbl_order;
CREATE TABLE tbl_order
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `user_id`   VARCHAR(64) NULL COMMENT '用户id',
    `order_id`       VARCHAR(32) NULL COMMENT '订单id',
    PRIMARY KEY (id)
) COMMENT = '账号信息';

CREATE INDEX idx_userId ON tbl_order(user_id);
CREATE INDEX idx_orderId ON tbl_order(order_id);
