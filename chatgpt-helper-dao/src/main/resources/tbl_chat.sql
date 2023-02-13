DROP TABLE IF EXISTS tbl_chat;
CREATE TABLE tbl_chat
(
    `id`          BIGINT NOT NULL COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `user_id`     VARCHAR(255) COMMENT '用户id',
    `question`    VARCHAR(255) COMMENT '问题',
    `answer`      VARCHAR(255) COMMENT '回答',
    PRIMARY KEY (id)
) COMMENT = 'AI问答';

CREATE INDEX idx_user_id ON tbl_chat(user_id);
