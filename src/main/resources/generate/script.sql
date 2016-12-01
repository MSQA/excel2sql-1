/*==============================================================*/
/* Table: T_URM_HELLO                                           */
/*==============================================================*/
DROP TABLE IF EXISTS T_URM_HELLO;
create table T_URM_HELLO  ( 
   URM_ID                   VARCHAR(20)      NOT NULL  comment '客户编号',
   AGR_ID                   VARCHAR(32)      comment '协议ID',
   EFF_DT                   VARCHAR(8)       comment '协议签订日期',
   EXP_DT                   VARCHAR(8)       comment '协议到期日期',
   AGR_RMK                  VARCHAR(60)      comment '协议备注',
   AGR_TYP                  VARCHAR(2)       comment '协议类型',
   AGR_FILE_ID              VARCHAR(16)      comment '协议文件ID',
   PRIMARY KEY (URM_ID)
);

alter table T_URM_HELLO comment '测试表';

