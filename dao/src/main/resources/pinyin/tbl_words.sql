DROP TABLE IF EXISTS tbl_words;
CREATE TABLE tbl_words
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `name`   VARCHAR(64) NULL COMMENT '昵称',
    `character`      VARCHAR(1) NULL COMMENT '一个汉字',
    `pinyin`       VARCHAR(10) NULL COMMENT '一个汉字的拼音',
    `user_id`     VARCHAR(64) NOT NULL COMMENT '用户id',
    PRIMARY KEY (id)
) COMMENT = '账号信息';

CREATE INDEX idx_userId ON tbl_words(user_id);
