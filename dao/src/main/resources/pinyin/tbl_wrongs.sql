DROP TABLE IF EXISTS tbl_wrongs;
CREATE TABLE tbl_wrongs
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `user_id`   VARCHAR(64) NULL COMMENT '用户id',
    `character`      VARCHAR(1) NULL COMMENT '汉字',
    `pinyin`       VARCHAR(1) NULL COMMENT '汉语拼音',
    PRIMARY KEY (id)
) COMMENT = '账号信息';

CREATE INDEX idx_userId ON tbl_wrongs(user_id);
