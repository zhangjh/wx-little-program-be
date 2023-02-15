DROP TABLE IF EXISTS tbl_draw;
CREATE TABLE tbl_draw
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `user_id`     VARCHAR(255) COMMENT '用户id',
    `term`        VARCHAR(1024) COMMENT '关键词',
    `url`         VARCHAR(1024) COMMENT '图片url',
    PRIMARY KEY (id)
) COMMENT = 'AI作画';

CREATE INDEX idx_user_id ON tbl_draw(user_id);

INSERT INTO tbl_draw (user_id, term, url) values (-1, "海边散步的情侣 动漫风格 高清画质", "https://wx2.sinaimg.cn/bmiddle/62d95157ly1hb4gl6pkfkj20sg0sg1kx.jpg");
INSERT INTO tbl_draw (user_id, term, url) values (-1, "给流浪地球制作一款精美的科幻风格海报", "https://wx2.sinaimg.cn/bmiddle/62d95157ly1hb4h8cwsjgj20sg0sgb29.jpg");
INSERT INTO tbl_draw (user_id, term, url) values (-1, "一只火红的狐狸在吃紫色的葡萄，油画风格", "https://wx4.sinaimg.cn/bmiddle/62d95157ly1hb4go2zg0qj20sg0sge81.jpg");
INSERT INTO tbl_draw (user_id, term, url) values (-1, "Rainforests inside a glass house,lush trees,vines twing around the shelves,greg rutkowski and james gurney and Thomas kinkade", "https://wx4.sinaimg.cn/bmiddle/62d95157ly1hb4h32kokjj20sg0sgkjl.jpg");
INSERT INTO tbl_draw (user_id, term, url) values (-1, "orbital space station,science and technology,Jonn Berkey,highly detailed,artstation", "https://wx3.sinaimg.cn/bmiddle/62d95157ly1hb4h365x97j20sg0sghdt.jpg");
INSERT INTO tbl_draw (user_id, term, url) values (-1, "great chinese palace above clouds,morning,distance waterfalls flow down the mountians,fantasy,high-definition,super wide angle,artstation", "https://wx2.sinaimg.cn/bmiddle/62d95157ly1hb4h3ait7hj20sg0sg7wh.jpg");
INSERT INTO tbl_draw (user_id, term, url) values (-1, "来一张中国水墨画风格的素描，内容是一名渔夫在船上垂钓", "https://wx1.sinaimg.cn/bmiddle/62d95157ly1hb4h3cikthj20sg0sg4qp.jpg");
INSERT INTO tbl_draw (user_id, term, url) values (-1, "可爱的黄色小猫咪", "https://wx1.sinaimg.cn/bmiddle/62d95157ly1hb4hushdpej20sg0sg4qp.jpg");

