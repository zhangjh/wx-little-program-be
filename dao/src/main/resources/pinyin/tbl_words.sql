DROP TABLE IF EXISTS tbl_words;
CREATE TABLE tbl_words
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `name`   VARCHAR(64) NULL COMMENT '昵称',
    `user_id`     VARCHAR(64) NOT NULL COMMENT '用户id',
    PRIMARY KEY (id)
) COMMENT = '账号信息';

CREATE INDEX idx_userId_name ON tbl_words(user_id, name);

INSERT INTO tbl_words (name,  user_id) values ('声母',  0);
INSERT INTO tbl_words (name,  user_id) values ('单韵母', 0);
INSERT INTO tbl_words (name,  user_id) values ('复韵母', 0);
INSERT INTO tbl_words (name,  user_id) values ('整体认读音节', 0);

select * from tbl_words;