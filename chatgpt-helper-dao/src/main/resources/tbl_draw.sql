DROP TABLE IF EXISTS tbl_draw;
CREATE TABLE tbl_draw
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `user_id`     VARCHAR(255) COMMENT '用户id',
    `term`        VARCHAR(255) COMMENT '关键词',
    `url`         VARCHAR(255) COMMENT '图片url',
    PRIMARY KEY (id)
) COMMENT = 'AI作画';

CREATE INDEX idx_user_id ON tbl_draw(user_id);
