DROP TABLE IF EXISTS tbl_share;
CREATE TABLE tbl_share
(
    `id`          BIGINT NOT NULL COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `type`        TINYINT(1) COMMENT '作品类型',
    `item_id`     VARCHAR(255) COMMENT '作品id',
    `tag`         VARCHAR(255) COMMENT '分享标签',
    `share_type`  TINYINT(1) COMMENT '分享类型',
    `biz_status`  TINYINT(1) COMMENT '审核状态',
    `target`      VARCHAR(255) COMMENT '分享目的地',
    PRIMARY KEY (id)
) COMMENT = '作品分享';

CREATE INDEX idx_item_id ON tbl_share(item_id);
CREATE INDEX idx_tag ON tbl_share(tag);
CREATE INDEX idx_share ON tbl_share(share_type, target);