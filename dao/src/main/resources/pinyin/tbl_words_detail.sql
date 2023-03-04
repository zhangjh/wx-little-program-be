DROP TABLE IF EXISTS tbl_words_detail;
CREATE TABLE tbl_words_detail
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`  TINYINT(1) DEFAULT 0  NOT NULL COMMENT '逻辑删除',
    `character`      VARCHAR(5) NULL COMMENT '一个汉字',
    `pinyin`       VARCHAR(10) NULL COMMENT '一个汉字的拼音',
    `words_id`     BIGINT NOT NULL COMMENT '生字本id',
    PRIMARY KEY (id)
) COMMENT = '生字详情';

CREATE INDEX idx_words_id_char ON tbl_words_detail(words_id, `character`);

select * from tbl_words_detail where  words_id = 5 order by id;

delete from tbl_words_detail where words_id = 5;

INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('b', 'b', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('p', 'p', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('m', 'm', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('f', 'f', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('d', 'd', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('t', 't', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('n', 'n', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('l', 'l', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('g', 'g', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('k', 'k', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('h', 'h', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('j', 'j', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('q', 'q', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('x', 'x', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('zh', 'zh', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ch', 'ch', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('sh', 'sh', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('z', 'z', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('c', 'c', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('s', 's', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('r', 'r', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('y', 'y', 1);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('w', 'w', 1);

INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('a', 'a', 2);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('o', 'o', 2);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('e', 'e', 2);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('i', 'i', 2);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('u', 'u', 2);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ü', 'ü', 2);

INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ai', 'ai', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ei', 'ei', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ui', 'ui', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ao', 'ao', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ou', 'ou', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('iu', 'iu', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ie', 'ie', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('üe', 'üe', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('er', 'er', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('an', 'an', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('en', 'en', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('in', 'in', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('un', 'un', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ün', 'ün', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ang', 'ang', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('eng', 'eng', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ing', 'ing', 3);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ong', 'ong', 3);

INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('zhi', 'zhi', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('chi', 'chi', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('shi', 'shi', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ri', 'ri', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('zi', 'zi', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ci', 'ci', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('si', 'si', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('yi', 'yi', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('wu', 'wu', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('yu', 'yu', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ye', 'ye', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('yue', 'yue', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('yin', 'yin', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('yun', 'yun', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('yuan', 'yuan', 4);
INSERT INTO tbl_words_detail ( `character`, pinyin, words_id) values ('ying', 'ying', 4);
