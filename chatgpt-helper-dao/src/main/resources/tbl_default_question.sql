DROP TABLE IF EXISTS tbl_default_question;
CREATE TABLE tbl_default_question
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `question`    VARCHAR(1024) COMMENT '问题',
    PRIMARY KEY (id)
) COMMENT = '默认问题';

INSERT INTO tbl_default_question (question) values ("找对象为什么这么难？");
INSERT INTO tbl_default_question (question) values ("俄乌冲突什么时候能结束？");
INSERT INTO tbl_default_question (question) values ("怎么看待詹姆斯获得NBA得分王？");
INSERT INTO tbl_default_question (question) values ("给我来一点Java八股文？");
INSERT INTO tbl_default_question (question) values ("给我来几个富有诗意的男孩名字？");
INSERT INTO tbl_default_question (question) values ("给我来首中晚唐的诗歌？");
INSERT INTO tbl_default_question (question) values ("以和谐为主题帮我写一段讲话稿？");
INSERT INTO tbl_default_question (question) values ("什么是斐波拉契数列？");
INSERT INTO tbl_default_question (question) values ("给我讲几个笑话？");
INSERT INTO tbl_default_question (question) values ("一句话总结一下《甄嬛传》说了什么？");
INSERT INTO tbl_default_question (question) values ("推荐几部好看的电影？");
INSERT INTO tbl_default_question (question) values ("推荐几本值得一看的书？");
INSERT INTO tbl_default_question (question) values ("如何制作精美的PPT？");
INSERT INTO tbl_default_question (question) values ("架构师应该掌握什么样的技能？");
INSERT INTO tbl_default_question (question) values ("如何评价马斯克收购推特的行为？");
INSERT INTO tbl_default_question (question) values ("普通人如何应对通货膨胀？");
INSERT INTO tbl_default_question (question) values ("有哪些普通人可以进行的投资理财方式？");
INSERT INTO tbl_default_question (question) values ("推荐一些中国值得旅游的景点？");
INSERT INTO tbl_default_question (question) values ("推荐一些欧洲值得旅游的景点？");
INSERT INTO tbl_default_question (question) values ("周杰伦有哪些专辑？");
INSERT INTO tbl_default_question (question) values ("怎样学习人工智能？");
INSERT INTO tbl_default_question (question) values ("如何计算笛卡尔积？");
INSERT INTO tbl_default_question (question) values ("怎样制作原子弹？");
INSERT INTO tbl_default_question (question) values ("如何快速学习英语？");
INSERT INTO tbl_default_question (question) values ("Chatgpt会取代哪些工作吗？");
INSERT INTO tbl_default_question (question) values ("A股为什么总在3000点徘徊？");

