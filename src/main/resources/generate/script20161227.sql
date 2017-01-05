/*==============================================================*/
/* Table: T_APC_APP                                             */
/*==============================================================*/
DROP TABLE IF EXISTS T_APC_APP;
create table T_APC_APP  ( 
   UUID                     VARCHAR(32)      NOT NULL  comment 'UUID',
   APP_ID                   VARCHAR(32)      comment '应用编号',
   APP_NAME                 VARCHAR(64)      comment '应用名称',
   APPGRP_ID                VARCHAR(32)      comment '分组编号',
   SUB_TITLE                VARCHAR(64)      comment '子标题',
   REMARK                   VARCHAR(512)     comment '描述',
   ICON                     VARCHAR(128)     comment 'ICON图标地址',
   APP_TYPE                 VARCHAR(2)       comment '01:H5, 02:Native',
   APP_ADDRESS              VARCHAR(128)     comment '应用地址',
   INBORN                   VARCHAR(1)       comment 'Y原装自带，N非',
   UNINSTALL                VARCHAR(1)       comment 'Y可以卸载，N不可以',
   ORDER_ID                 INTEGER          comment '排序ID',
   CRT_OPER_ID              VARCHAR(32)      comment '创建时间',
   CRT_TIME                 DATETIME         comment '创建人',
   MDF_OPER_ID              VARCHAR(32)      comment '修改时间',
   MDF_TIME                 DATETIME         comment '修改人',
   PRIMARY KEY (UUID)
);

alter table T_APC_APP comment '应用信息表';

