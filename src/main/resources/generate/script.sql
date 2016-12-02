/*==============================================================*/
/* Table: T_STAFF                                               */
/*==============================================================*/
DROP TABLE IF EXISTS T_STAFF;
create table T_STAFF  ( 
   STAFF_ID                 VARCHAR(32)      NOT NULL  comment 'UUID',
   STAFF_NAME               VARCHAR(16)      comment '员工姓名',
   PRIMARY KEY (STAFF_ID)
);

alter table T_STAFF comment '员工表';

