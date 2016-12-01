/*==============================================================*/
/* Table: T_URM_BUSI                                            */
/*==============================================================*/
DROP TABLE IF EXISTS T_URM_BUSI;
create table T_URM_BUSI  ( 
   URM_ID                   VARCHAR(20)      NOT NULL  comment '客户编号',
   BUS_ID                   VARCHAR(32)      NOT NULL  comment '营业ID',
   BUS_LIC_IMG              VARCHAR(128)     comment '0-个人；1-企业',
   PSN_CRP_FLG              VARCHAR(1)       comment '银联标准的行业类别',
   MCC_CD                   VARCHAR(4)       comment '银盛商户类型',
   MCC_SUB_CD               VARCHAR(6)       comment '商户行业类别(细类)',
   MERC_TRD_DESC            VARCHAR(60)      comment '商户行业描述',
   TAX_REG_NO               VARCHAR(27)      comment '税务登记号',
   REG_ID                   VARCHAR(60)      comment '营业执照编号',
   ORG_COD                  VARCHAR(30)      comment '机构代码证',
   REG_ADDR                 VARCHAR(120)     comment '最长32个汉字，这就是营业执照上的官方隶属机构。',
   REG_ORG_NM               VARCHAR(64)      comment '商户注册隶属机构',
   CRP_NM                   VARCHAR(60)      comment '法人名称',
   CRP_ID_TYP               VARCHAR(3)       comment '法人证件类型',
   CRP_ID_NO                VARCHAR(30)      comment '法人证件号码',
   CRP_PHONE                VARCHAR(11)      comment '法人手机号码',
   CRP_LIC_IMG              VARCHAR(128)     comment '法人证件扫描件',
   PRIN_NM                  VARCHAR(60)      comment '负责人名称',
   BUS_ADDR                 VARCHAR(120)     comment '营业地址',
   BUS_ZIP                  VARCHAR(6)       comment '营业地邮政编码',
   HOL_BUS_FLG              VARCHAR(01)      comment 'N：不营业
Y：营业',
   BUS_STR_HOUR             VARCHAR(20)      comment '选择营业时间？开关门时间？',
   BUS_END_HOUR             VARCHAR(20)      comment '营业截止时间',
   TAX_CERT_ID              VARCHAR(20)      comment '税务证明',
   ICP_ID                   VARCHAR(60)      comment 'Y：是 N：不是',
   AIC_MEM_FLG              VARCHAR(1)       comment '1 国营
2 股份制
3 集体
4 中外合资、合作
5 外商独资
6 私营合伙
7 私营独资
8 个体
9 其他',
   MERC_ATTR                VARCHAR(2)       comment '商户性质',
   REG_CAP_AMT              NUMERIC(13,2)    comment '1：自有
2：租用',
   BUS_ULD_ATTR             VARCHAR(1)       comment '数字，最长10位，单位(平米)',
   BUS_AREA                 VARCHAR(10)      comment '1 商业区
2 工业区
3 住宅区',
   MGT_SECT                 VARCHAR(1)       comment '1 城区
2 郊区
3 边远地区',
   MGT_RGN                  VARCHAR(1)       comment '经营区域',
   ORG_RGN_SCP              VARCHAR(30)      comment '分支机构区域范围',
   ORG_NUM                  NUMERIC(6)       comment '分支机构数量',
   STAF_NUM                 NUMERIC(6)       comment '员工人数',
   OPN_BUS_DT               VARCHAR(08)      comment '开业日期',
   MERC_POP                 VARCHAR(8)       comment '商户知名度',
   MGT_SCP                  VARCHAR(60)      comment '经营范围(主业)',
   NMGT_SCP                 VARCHAR(60)      comment '经营范围(副业)',
   BUS_LIC_VLD              VARCHAR(8)       comment '营业执照有效日期',
   BUS_LIC_EXP              VARCHAR(8)       comment '营业执照失效日期',
   PER_CRT_VLD              VARCHAR(8)       comment '法人代表证件生效日期',
   PER_CRT_EXP              VARCHAR(8)       comment '法人代表证件失效日期',
   CRT_OPER_ID              VARCHAR(20)      NOT NULL  comment '创建人',
   CRT_TIME                 DATETIME         NOT NULL  comment '创建时间',
   MDF_OPER_ID              VARCHAR(20)      comment '修改人',
   MDF_TIME                 DATETIME         comment '修改时间',
   PRIMARY KEY (URM_ID)
);

alter table T_URM_BUSI comment '营业信息表';

