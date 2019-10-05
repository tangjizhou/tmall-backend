# create database tmall;
use tmall;
create user tmall identified by 'tmallpass';
grant all privileges on tmall.* to tmall@'%' with grant option ;
create table permission
(
    id              bigint unsigned        not null
        primary key,
    permission_code varchar(32) default '' not null comment '权限代码',
    create_time     datetime               null comment '创建时间',
    update_time     datetime               null comment '更新时间'
)
    comment '权限';


create table product
(
    id                  bigint unsigned         not null
        primary key,
    name                varchar(255) default '' not null comment '名称',
    category_id         int unsigned default 0  not null,
    state               tinyint      default 1  null comment '0:无效,1:有效,9:封杀',
    brief_introduction  varchar(255) default '' null,
    detail_introduction varchar(512) default '' null,
    original_price      decimal      default 0  null,
    promote_price       decimal      default 0  null,
    create_time         datetime                null,
    update_time         datetime                null on update CURRENT_TIMESTAMP
);

INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (1, '手机', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (2, '电视机', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (3, '平板', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (4, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (5, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (6, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (7, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (8, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (9, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (10, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (11, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (12, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
INSERT INTO tmall.product (id, name, category_id, state, brief_introduction, detail_introduction, original_price, promote_price, create_time, update_time) VALUES (13, '笔记本', 0, 1, '', '', 0, 0, null, '2019-08-26 22:37:15');
create table product_category
(
    id            bigint unsigned         not null
        primary key,
    category_name varchar(36)  default '' not null,
    state         tinyint      default 1  null comment '0:停用,1:有效',
    remark        varchar(255) default '' null,
    create_time   datetime                null,
    update_time   datetime                null on update CURRENT_TIMESTAMP
);


create table product_image
(
    id          bigint unsigned            not null
        primary key,
    product_id  int unsigned default 0     not null,
    file_name   varchar(90)  default ''    null,
    file_type   varchar(10)  default 'jpg' null,
    location    varchar(255) default ''    null,
    create_time datetime                   null,
    update_time datetime                   null on update CURRENT_TIMESTAMP
);


create table purchase_order
(
    id          bigint unsigned         not null
        primary key,
    user_id     int unsigned default 0  null,
    total_price decimal      default 0  null,
    state       tinyint      default 10 null comment '0:取消,10:已创建,20:已付款,30:已发货,40:运送中,50:派送中,60:已完成',
    address     varchar(255) default '' not null,
    remark      varchar(90)  default '' null,
    pay_num     int unsigned default 0  null,
    pay_time    timestamp               null,
    ship_time   timestamp               null,
    create_time datetime                null,
    update_time datetime                null on update CURRENT_TIMESTAMP
);


create table purchase_order_item
(
    id             bigint unsigned         not null
        primary key,
    order_id       int unsigned default 0  not null,
    product_id     int unsigned default 0  null,
    product_name   varchar(90)  default '' null,
    product_count  tinyint      default 1  null,
    original_price decimal      default 0  null,
    deal_price     decimal      default 0  null,
    state          tinyint      default 10 null comment '0:交易失败,1:交易成功',
    create_time    datetime                null,
    update_time    datetime                null on update CURRENT_TIMESTAMP
);


create table role
(
    id          bigint unsigned        not null
        primary key,
    role_name   varchar(64) default '' not null comment '角色名',
    state       tinyint(1)  default 1  not null comment '状态:1:正常,0:禁用',
    create_time datetime               null comment '创建时间',
    update_time datetime               null comment '更新时间'
)
    comment '用户角色';


create table role_permission
(
    role_id       bigint unsigned null comment '角色id',
    permission_id bigint unsigned null comment '权限id'
)
    comment '角色_权限对应';


create table sql_log
(
    id           bigint unsigned          not null
        primary key,
    executed_sql varchar(4000) default '' not null comment '执行的sql语句',
    create_time  datetime                 null comment '创建时间',
    update_time  datetime                 null comment '更新时间'
);

INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430689759219714, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:24:50', '2019-09-21 23:24:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430691076231169, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:24:50', '2019-09-21 23:24:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430692758147073, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:24:50', '2019-09-21 23:24:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430693290823682, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:24:50', '2019-09-21 23:24:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430701373247490, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:24:52', '2019-09-21 23:24:52');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430709677969410, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-21 23:24:54', '2019-09-21 23:24:54');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430806549614594, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-21 23:25:17', '2019-09-21 23:25:17');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430820436955138, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:25:21', '2019-09-21 23:25:21');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430844101218305, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'','''',''%'') and state = 1', '2019-09-21 23:25:26', '2019-09-21 23:25:26');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430846781378561, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'','''',''%'') and state = 1', '2019-09-21 23:25:27', '2019-09-21 23:25:27');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430847557324801, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'','''',''%'') and state = 1', '2019-09-21 23:25:27', '2019-09-21 23:25:27');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430855392284673, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'','''',''%'') and state = 1', '2019-09-21 23:25:29', '2019-09-21 23:25:29');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430860287037441, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'','''',''%'') and state = 1', '2019-09-21 23:25:30', '2019-09-21 23:25:30');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430897524068354, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'','''',''%'') and state = 1', '2019-09-21 23:25:39', '2019-09-21 23:25:39');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430899352784897, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'','''',''%'') and state = 1', '2019-09-21 23:25:40', '2019-09-21 23:25:40');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430927051968514, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''tangjizhou'',''%'') and state = 1', '2019-09-21 23:25:46', '2019-09-21 23:25:46');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430937055383554, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou1'',''%'') and real_name like concat(''%'',''tangjizhou1'',''%'') and state = 1', '2019-09-21 23:25:49', '2019-09-21 23:25:49');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175430956072357890, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and state = 1', '2019-09-21 23:25:53', '2019-09-21 23:25:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431099945410561, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''他'',''%'') and state = 1', '2019-09-21 23:26:27', '2019-09-21 23:26:27');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431116567437314, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''唐'',''%'') and state = 1', '2019-09-21 23:26:31', '2019-09-21 23:26:31');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431134770716673, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''唐继续'',''%'') and state = 1', '2019-09-21 23:26:36', '2019-09-21 23:26:36');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431148150546433, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''唐继洲'',''%'') and state = 1', '2019-09-21 23:26:39', '2019-09-21 23:26:39');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431150537105410, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''唐继洲'',''%'') and state = 1', '2019-09-21 23:26:39', '2019-09-21 23:26:39');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431151111725058, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''唐继洲'',''%'') and state = 1', '2019-09-21 23:26:40', '2019-09-21 23:26:40');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431151736676353, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and username like concat(''%'',''tangjizhou'',''%'') and real_name like concat(''%'',''唐继洲'',''%'') and state = 1', '2019-09-21 23:26:40', '2019-09-21 23:26:40');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431167880552449, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'',''唐继洲'',''%'') and state = 1', '2019-09-21 23:26:44', '2019-09-21 23:26:44');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431176403378178, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'',''唐继洲'',''%'') and state = 0', '2019-09-21 23:26:46', '2019-09-21 23:26:46');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431184196395009, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'',''唐继洲'',''%'') and state = 1', '2019-09-21 23:26:47', '2019-09-21 23:26:47');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431193788768258, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'',''唐继洲'',''%'') and state = 9', '2019-09-21 23:26:50', '2019-09-21 23:26:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431207936155650, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'',''唐继洲%'',''%'') and state = 9', '2019-09-21 23:26:53', '2019-09-21 23:26:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431219734732801, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'',''‘'',''%'') and state = 9', '2019-09-21 23:26:56', '2019-09-21 23:26:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431221567643650, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and real_name like concat(''%'',''‘'',''%'') and state = 9', '2019-09-21 23:26:56', '2019-09-21 23:26:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431230954496002, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-21 23:26:59', '2019-09-21 23:26:59');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431236964933633, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-21 23:27:00', '2019-09-21 23:27:00');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431245567451138, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-21 23:27:02', '2019-09-21 23:27:02');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431255533117442, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:27:04', '2019-09-21 23:27:04');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431310738546690, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:27:18', '2019-09-21 23:27:18');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431583817097218, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:28:23', '2019-09-21 23:28:23');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431585318658050, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:28:23', '2019-09-21 23:28:23');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431609444294657, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:28:29', '2019-09-21 23:28:29');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175431676376997889, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-21 23:28:45', '2019-09-21 23:28:45');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432142066376705, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:30:36', '2019-09-21 23:30:36');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432153550381057, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-21 23:30:39', '2019-09-21 23:30:39');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432190464450562, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-21 23:30:47', '2019-09-21 23:30:47');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432201705185282, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:30:50', '2019-09-21 23:30:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432246307414018, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:31:01', '2019-09-21 23:31:01');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432255270641666, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-21 23:31:03', '2019-09-21 23:31:03');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432264607162370, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:31:05', '2019-09-21 23:31:05');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432273545224193, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:31:07', '2019-09-21 23:31:07');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432451702480897, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:31:50', '2019-09-21 23:31:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432579846856706, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:32:20', '2019-09-21 23:32:20');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432776974950401, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:33:07', '2019-09-21 23:33:07');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432915990962177, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:33:40', '2019-09-21 23:33:40');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432935867768834, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:33:45', '2019-09-21 23:33:45');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175432951797735425, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:33:49', '2019-09-21 23:33:49');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433223957733378, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:34:54', '2019-09-21 23:34:54');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433235710173186, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:34:57', '2019-09-21 23:34:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433268513824770, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:35:04', '2019-09-21 23:35:04');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433501138313217, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-21 23:36:00', '2019-09-21 23:36:00');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433508599980033, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-21 23:36:02', '2019-09-21 23:36:02');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433516690792449, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:36:04', '2019-09-21 23:36:04');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433609024200705, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:36:26', '2019-09-21 23:36:26');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433629567901698, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:36:30', '2019-09-21 23:36:30');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433636765327361, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:36:32', '2019-09-21 23:36:32');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433650392621057, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:36:35', '2019-09-21 23:36:35');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433934598660097, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:43', '2019-09-21 23:37:43');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433936511262721, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:44', '2019-09-21 23:37:44');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433937194934274, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:44', '2019-09-21 23:37:44');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433938700689409, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:44', '2019-09-21 23:37:44');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433939363389441, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:44', '2019-09-21 23:37:44');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433940000923649, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:44', '2019-09-21 23:37:44');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433940684595202, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:45', '2019-09-21 23:37:45');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433941263409154, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:45', '2019-09-21 23:37:45');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433965552623617, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:51', '2019-09-21 23:37:51');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433966987075585, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:51', '2019-09-21 23:37:51');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433967490392066, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:51', '2019-09-21 23:37:51');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433968434110465, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:51', '2019-09-21 23:37:51');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433969075838977, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:51', '2019-09-21 23:37:51');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433970778726401, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:52', '2019-09-21 23:37:52');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433971512729601, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:52', '2019-09-21 23:37:52');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433972590665729, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:52', '2019-09-21 23:37:52');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433973228199937, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:52', '2019-09-21 23:37:52');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433973802819586, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:53', '2019-09-21 23:37:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433974209667073, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:53', '2019-09-21 23:37:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433974708789250, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:53', '2019-09-21 23:37:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433975254048770, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:53', '2019-09-21 23:37:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433976784969730, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:53', '2019-09-21 23:37:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433977728688129, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:53', '2019-09-21 23:37:53');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433978269753345, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:54', '2019-09-21 23:37:54');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433978705960961, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:54', '2019-09-21 23:37:54');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433979297357825, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:54', '2019-09-21 23:37:54');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433986272485378, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:55', '2019-09-21 23:37:55');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433987086180353, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:56', '2019-09-21 23:37:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433987748880385, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:56', '2019-09-21 23:37:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433988252196865, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:56', '2019-09-21 23:37:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433988789067777, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:56', '2019-09-21 23:37:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433989300772865, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:56', '2019-09-21 23:37:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433989846032385, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:56', '2019-09-21 23:37:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433990332571649, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:56', '2019-09-21 23:37:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433990940745729, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:57', '2019-09-21 23:37:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433992056430594, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:57', '2019-09-21 23:37:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433992580718594, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:57', '2019-09-21 23:37:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433993180504065, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:57', '2019-09-21 23:37:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433993671237634, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:57', '2019-09-21 23:37:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433994283606017, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:57', '2019-09-21 23:37:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433994833059842, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433995470594049, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433996099739649, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433996695330818, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433997169287170, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433997680992258, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433998100422658, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175433998708596737, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:58', '2019-09-21 23:37:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434002391195650, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:37:59', '2019-09-21 23:37:59');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434070968066049, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:38:16', '2019-09-21 23:38:16');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434116816003074, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:38:27', '2019-09-21 23:38:27');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434476255272962, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:39:52', '2019-09-21 23:39:52');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434499403636737, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:39:58', '2019-09-21 23:39:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434874928062466, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:41:27', '2019-09-21 23:41:27');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434890677673985, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-21 23:41:31', '2019-09-21 23:41:31');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434904695037953, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-21 23:41:34', '2019-09-21 23:41:34');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434917697380353, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-21 23:41:38', '2019-09-21 23:41:38');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434927000346625, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:41:40', '2019-09-21 23:41:40');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175434941168705538, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:41:43', '2019-09-21 23:41:43');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175435335835934721, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:43:17', '2019-09-21 23:43:17');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175435871981228034, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:45:25', '2019-09-21 23:45:25');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175436095579574274, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:46:18', '2019-09-21 23:46:18');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175436222344024066, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:46:49', '2019-09-21 23:46:49');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175436349225914369, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:47:19', '2019-09-21 23:47:19');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175436383032004610, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:47:27', '2019-09-21 23:47:27');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175438408285245441, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:55:30', '2019-09-21 23:55:30');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175438537213956098, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:56:01', '2019-09-21 23:56:01');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175438545841639425, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:56:03', '2019-09-21 23:56:03');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175438806203060225, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-21 23:57:05', '2019-09-21 23:57:05');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175439937092276225, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:01:34', '2019-09-22 00:01:34');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175439980809506817, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:01:45', '2019-09-22 00:01:45');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175440166441013249, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:02:29', '2019-09-22 00:02:29');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175440284997210114, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:02:57', '2019-09-22 00:02:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175440313111629826, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:03:04', '2019-09-22 00:03:04');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441149434875905, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:06:23', '2019-09-22 00:06:23');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441169856942081, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:06:28', '2019-09-22 00:06:28');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441516306452481, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:07:51', '2019-09-22 00:07:51');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441535050797058, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:07:55', '2019-09-22 00:07:55');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441598942629890, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:08:10', '2019-09-22 00:08:10');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441605313777665, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:08:12', '2019-09-22 00:08:12');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441747790090242, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:08:46', '2019-09-22 00:08:46');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441756325498882, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:08:48', '2019-09-22 00:08:48');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441792664948738, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:08:57', '2019-09-22 00:08:57');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441800072089601, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:08:58', '2019-09-22 00:08:58');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441834289221634, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:09:07', '2019-09-22 00:09:07');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441850353405953, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:09:10', '2019-09-22 00:09:10');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441854497378306, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:09:11', '2019-09-22 00:09:11');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441856015716354, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:09:12', '2019-09-22 00:09:12');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175441982453010433, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:09:42', '2019-09-22 00:09:42');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442072324362242, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:10:03', '2019-09-22 00:10:03');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442109573976065, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:10:12', '2019-09-22 00:10:12');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442225378709505, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:10:40', '2019-09-22 00:10:40');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442284648419329, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:10:54', '2019-09-22 00:10:54');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442307595456514, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:10:59', '2019-09-22 00:10:59');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442352097021954, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:11:10', '2019-09-22 00:11:10');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442719593549826, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:12:38', '2019-09-22 00:12:38');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442745954750465, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:12:44', '2019-09-22 00:12:44');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175442810903547906, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:12:59', '2019-09-22 00:12:59');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443072439373825, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:14:02', '2019-09-22 00:14:02');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443093905821698, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:14:07', '2019-09-22 00:14:07');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443128445915137, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:14:15', '2019-09-22 00:14:15');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443196305559553, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:14:31', '2019-09-22 00:14:31');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443319647457282, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:01', '2019-09-22 00:15:01');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443346751049729, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:07', '2019-09-22 00:15:07');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443379596644354, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:15', '2019-09-22 00:15:15');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443425188728834, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:26', '2019-09-22 00:15:26');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443514980388866, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:47', '2019-09-22 00:15:47');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443527349391362, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:50', '2019-09-22 00:15:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443549856026626, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:56', '2019-09-22 00:15:56');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443564422844417, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:15:59', '2019-09-22 00:15:59');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443631145832450, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:16:15', '2019-09-22 00:16:15');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443672061267970, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:16:25', '2019-09-22 00:16:25');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443778319765506, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:16:50', '2019-09-22 00:16:50');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175443834854789122, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:17:04', '2019-09-22 00:17:04');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444127537516546, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:18:13', '2019-09-22 00:18:13');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444230088249345, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:18:38', '2019-09-22 00:18:38');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444345880399873, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:19:05', '2019-09-22 00:19:05');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444450599587841, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:19:30', '2019-09-22 00:19:30');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444481071206401, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:19:38', '2019-09-22 00:19:38');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444494681722881, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:19:41', '2019-09-22 00:19:41');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444511010148353, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:19:45', '2019-09-22 00:19:45');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444690429890561, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:20:28', '2019-09-22 00:20:28');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444754728570881, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:20:43', '2019-09-22 00:20:43');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444781253349377, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:20:49', '2019-09-22 00:20:49');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444806108794881, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:20:55', '2019-09-22 00:20:55');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444841315782658, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:21:04', '2019-09-22 00:21:04');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444860781547522, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:21:08', '2019-09-22 00:21:08');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444927861051393, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-22 00:21:24', '2019-09-22 00:21:24');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175444938791407618, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-22 00:21:27', '2019-09-22 00:21:27');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175445251732623361, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1', '2019-09-22 00:22:41', '2019-09-22 00:22:41');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175445494607990785, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-22 00:23:39', '2019-09-22 00:23:39');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175445502543613954, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-22 00:23:41', '2019-09-22 00:23:41');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175445558109753345, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-22 00:23:54', '2019-09-22 00:23:54');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446192863137794, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-22 00:26:26', '2019-09-22 00:26:26');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446203764133889, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-22 00:26:28', '2019-09-22 00:26:28');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446212383428609, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-22 00:26:30', '2019-09-22 00:26:30');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446218368700418, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-22 00:26:32', '2019-09-22 00:26:32');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446225079586817, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-22 00:26:33', '2019-09-22 00:26:33');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446259032477698, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 1', '2019-09-22 00:26:42', '2019-09-22 00:26:42');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446266158600194, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 9', '2019-09-22 00:26:43', '2019-09-22 00:26:43');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446272278089730, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-22 00:26:45', '2019-09-22 00:26:45');
INSERT INTO tmall.sql_log (id, executed_sql, create_time, update_time) VALUES (1175446275327348738, 'select username, state, real_name, address, create_time, update_time from user where 1 = 1 and state = 0', '2019-09-22 00:26:45', '2019-09-22 00:26:45');
create table user
(
    id          bigint unsigned         not null
        primary key,
    username    varchar(30)  default '' not null,
    password    varchar(90)  default '' not null,
    state       tinyint      default 1  null comment '0:无效,1:有效,9:冻结',
    real_name   varchar(20)  default '' null,
    address     varchar(255) default '' null,
    create_time datetime                null,
    update_time datetime                null on update CURRENT_TIMESTAMP,
    constraint username
        unique (username)
);

INSERT INTO tmall.user (id, username, password, state, real_name, address, create_time, update_time) VALUES (1163101271044927490, 'tangjizhou', '1111111', 1, '唐继洲', '四川成都高新西区百草路金色海伦', '2019-08-18 22:52:07', '2019-08-25 23:40:25');
INSERT INTO tmall.user (id, username, password, state, real_name, address, create_time, update_time) VALUES (1165262062179356673, 'zhouwenxue', '123', 1, '周文学', '国腾园', '2019-08-24 21:58:20', '2019-08-24 21:58:20');
INSERT INTO tmall.user (id, username, password, state, real_name, address, create_time, update_time) VALUES (1165270942976348162, 'zhouw?enxue', '123', 0, '周文学', '国腾园', '2019-08-24 22:33:37', '2019-09-21 23:21:15');
INSERT INTO tmall.user (id, username, password, state, real_name, address, create_time, update_time) VALUES (1166710621160464386, 'string', '124234', 1, 'string', 'string', '2019-08-28 21:54:23', '2019-09-21 00:01:35');
create table user_role
(
    user_id bigint not null comment '用户id',
    role_id bigint not null comment '角色id',
    constraint user_role_user_id_role_id_uindex
        unique (user_id, role_id)
)
    comment '用户_角色关系对应';

