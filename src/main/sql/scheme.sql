drop database if exists seckill;
/*创建数据库，并设置编码*/
create database seckill default character set utf8;
use seckill;
CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  name VARCHAR(120) NOT NULL COMMENT '商品名称',
  number int NOT NULL COMMENT '商品数量',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT = '秒杀库存表';

  insert into
  seckill (name ,number,start_time,end_time)
  values
('1000ip6',100,'2017-05-16 00:00:00','2017-05-20 00:00:00'),
('1000ipad2',200,'2017-02-05 00:00:00','2017-02-05 00:00:00'),
('1000huawei',300,'2017-02-05 00:00:00','2017-02-05 00:00:00'),
('1000msi',400,'2017-02-05 00:00:00','2017-02-05 00:00:00');

CREATE TABLE success_killed(
  seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
  uesr_phone BIGINT NOT NULL COMMENT '用户手机号',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '',
  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,uesr_phone),/*联合主键*/
key idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '秒杀成功表'