#用户主表
create table consumer(
	consumer_id varchar(11) unique not null comment '用户id',
    consumer_name varchar(32)  not null comment '用户昵称',
    ctype enum('1','2') not null comment '1是买家，2是卖家',
    password char(32) not null comment '密码',
    primary key pk_consumer (consumer_id)
);
insert into consumer values
('20200617001','张三','1','123123'),
('20200518002','李四','1','456456'),
('20200407011','王五','1','789789'),
('20200114101','小王','2','123456'),
('20200323005','小黄','2','456789'),
('20200329078','小叶','2','111222');


#买家表
create table customer(
	customer_id varchar(11) unique  not null comment '买家id',
    costomer_phone varchar(13)  not null comment '联系电话',
    #costomer_sex enum('男','女')  comment '用户性别',
    primary key pk_customer (customer_id),
	foreign key fk_customer(customer_id) references consumer(consumer_id)
    );
insert into customer values
('20200617001','1354561111'),
('20200518002','13677899111'),
('20200407011','214322411');



 #卖家表   
create table vendor(
	vendor_id varchar(11) unique not null comment '卖家id',
    vendor_phone varchar(13)  null comment '联系电话',
    #address varchar(50) not null comment '卖家所在地址',
    #sales int comment '店铺销量',
    primary key pk_vendor (vendor_id),
    foreign key fk_customer(vendor_id) references consumer(consumer_id)
);
insert into vendor values
('20200114101','2344566434'),
('20200323005','112445648'),
('20200329078','5675322577');


#商品表，单纯只有书而已，book_id是主键,vendor_id是外键，表示属于哪个卖家
create table book(
	book_id varchar(10) not null  comment '编号',
	book_name char(20) not null comment '书名',
    ISBN char(20) not null comment '书号',
    vendor_id varchar(11)  not null comment '所属店家id',
    book_type enum('言情','科幻','教程','侦探','诗歌','其他') comment '书籍类别',
    price decimal(8,2) not null comment '价格',
    product MediumBlob null comment'图片',
    sales int comment '销量' default 0,
    primary key pk_book (book_id),
    foreign key fk_book (vendor_id) references vendor(vendor_id)
);
insert into book values
('B00001','镇魂','787221150622','20200114101','言情',89.10,null,30),
('B00002','金阁寺','9787020121021','20200323005','其他',42.0,null,330),
('B00003','白夜行','9787544291163','20200329078','侦探',59.50,null,405),
('B00004','嫌疑人X的献身','9787544267618','20200329078','侦探',24.20,null,607),
('B00005','三体','9787229124410','20200329078','科幻',245.20,null,189),
('B00006','操作系统概念','9787111604365','20200323005','教程',85.60,null,387),
('B00007','算法导论','9787111407010','20200323005','教程',110.70,null,345),
('B00008','数据库系统概念','9787111375296','20200323005','教程',90.10,null,234),
('B00009','唐诗三百首','9787550243644','20200323005','诗歌',8.20,null,980),
('B00010','北岛诗精编','9787535475145','20200114101','诗歌',29.30,null,980),
('B00011','挪威的森林','9787532742929','20200114101','其他',18.00,null,980);


create table order_card(
	order_card_id varchar(11) not null  comment '购物车id',
    customer_id varchar(11) not null comment '购物车用户id',
    book_id  varchar(10)not null comment '书籍id',
    book_number int unsigned not null default 1 comment '加入购物车的数量',
    price decimal(8,2) not null comment '价格',
    primary key pk_order_card (order_card_id)
);
-- book_id的类型修改成varchar(10)
insert into order_card values
('c001','20200617001','B00001','2',89.10),
('c002','20200518002','B00002','4',42.0),
('c003','20200407011','B00003','5',59.50);

create table order_detail(
	order_detail_id varchar(10) not null  comment '订单详情表',
    order_id varchar(200) not null comment '订单表id',
    book_id varchar(10) not null comment '订单书籍id',
    order_cnt int unsigned not null default 1 comment '购买数量',
    price decimal(8,2) not null comment '单价',
    price_sum decimal(8,2) not null comment '总价',
	vendor_id varchar(11)  not null comment '卖家id',
    shipment enum('1','2')  default 1 not null comment'1表示还没出货，2表示出货了' ,
    primary key pk_order_detail (order_detail_id)
);

insert into order_detail values
('OB001','OA001','B00001',1,89.10,173.1,'20200114101','1');
insert into order_detail values
('OB002','OA001','B00002',2,42.00 ,173.1,'20200323005','1');
insert into order_detail values
('OB003','OA002','B00003',3,59.50,202.7,'20200329078','1');
insert into order_detail values
('OB004','OA002','B00004',1,24.20,202.7,'20200329078','1');
insert into order_detail values
('OB005','OA003','B00005',4, 245.20,980.8,'20200329078','2');
insert into order_detail values
('OB006','OA004','B00006',1,85.60,85.60,'20200323005','2');
insert into order_detail values
('OB007','OA005','B00007',5,110.70,553.50,'20200323005','1');
insert into order_detail values
('OB008','OA006','B00008',1,90.10,90.10,'20200323005','1');
insert into order_detail values
('OB009','OA007','B00009',10,8.20,82.00,'20200323005','2');
insert into order_detail values
('OB010','OA008','B00010',1,29.30,29.30,'20200114101','1');
insert into order_detail values
('OB011','OA009','B00011',1,18.00,18.00,'20200114101','1');
insert into order_detail values
('OB012','OA010','B00011',11,18.00,198.0,'20200114101','1');


create table order_master(
	order_id varchar(200) not null  comment '订单表ID',
	customer_id varchar(11) not null comment '买家id',
    consignee varchar(20) not null comment '收货人姓名',
    tel varchar(13) not null comment '收货人的联系电话',
	address varchar(100) NOT NULL COMMENT '收货地址',
    order_money decimal(10,2) not null comment '订单总金额',
    remarks varchar(50) null comment '备注',
    primary key pk_order_master (order_id)
);

insert into order_master values
('OA001','20200617001','张三','1354561111','广东省珠海市北京理工大学珠海学院',173.1,'快点发货');
insert into order_master values
('OA002','20200617001','张三','1354561111','广东省珠海市北京师范大学珠海学院',202.7,'快点发货');
insert into order_master values
('OA003','20200617001','张三','1354561111','广东省珠海市北京吉林大学珠海学院',980.8,null);
insert into order_master values
('OA004','20200518002','李四','13677899111','广东省珠海市北京理工大学',85.60,null);
insert into order_master values
('OA005','20200518002','李四','13677899111','广东省珠海市北京师范大学',553.50,null);
insert into order_master values
('OA006','20200407011','王五','214322411','广东省珠海市北京理工大学珠海学院',90.10,null);
insert into order_master values
('OA007','20200407011','王五','214322411','广东省珠海市北京理工大学珠海学院',82.00,null);


create table recommend
(
  customer_id varchar(11) NOT NULL COMMENT '买家id',
  Ynum int default 0 comment '言情数量',
  Jnum int default 0 comment '教程数量',
  Knum int default 0 comment '教程数量',
  Znum int default 0 comment '侦探数量',
  Snum int default 0 comment '诗歌数量',
   Qnum int default 0 comment '其他数量',
   foreign key fk_customer(customer_id) references consumer(consumer_id)
  );
  select * from recommend;
  insert into recommend values
('20200617001',0,0,0,0,0,0),
('20200518002',0,0,0,0,0,0),
('20200407011',0,0,0,0,0,0),
('20200114101',0,0,0,0,0,0),
('20200323005',0,0,0,0,0,0),
('20200329078',0,0,0,0,0,0);

drop table order_detail;
drop table order_master