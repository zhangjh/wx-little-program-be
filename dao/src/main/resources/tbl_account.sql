create database chatGptHelper  character set utf8mb4;

DROP TABLE IF EXISTS tbl_account;
CREATE TABLE tbl_account
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `avatar`      VARCHAR(255) NOT NULL COMMENT '头像',
    `extId`       VARCHAR(64) NOT NULL COMMENT '外部系统id',
    `extType`     TINYINT(1) NOT NULL COMMENT '外部系统类型',
    `mobile`      VARCHAR(11) NOT NULL COMMENT '手机',
    PRIMARY KEY (id)
) COMMENT = '账号信息';

CREATE INDEX idx_extId ON tbl_account(extType, extId);
