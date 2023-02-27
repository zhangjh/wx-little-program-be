DROP TABLE IF EXISTS tbl_api_keys;

CREATE TABLE tbl_api_keys
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `api_key`   VARCHAR(64) NULL COMMENT 'api_key',
    `email`      VARCHAR(255) NULL COMMENT '注册邮箱',
    PRIMARY KEY (id)
) COMMENT = 'OpenAI keys';

INSERT INTO tbl_api_keys (api_key, email) values ("", "");

update  tbl_api_keys set api_key = "" where id = ?;