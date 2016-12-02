# excel2sql项目是根据excel中的内容生成建表语句 #


## 1. 编辑excel文件 ##
在sample.xlsx文件中，新建一个名称为员工表的sheet，它的内容如下：  
![](http://i.imgur.com/hLjn5Nz.png)

## 2. 配置excel2sql.properties ##

    \#直接执行脚本
    execute.script=true
    
    \#保存SQL脚本
    save.script=true
    script.filename=src/main/resources/generate/script.sql
    
    \#excel文件
    excel.filename=sample.xlsx
    sheet.names=员工表


## 3. 配置jdbc.properties ##
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:8066/test?characterEncoding=utf8&autoReconnect=true
    jdbc.username=root
    jdbc.password=123456

## 4.执行SqlGenRun.main() ##
程度会根据配置，是否保存生成的SQL脚本，是否直接执行脚本到数据库。


## 5. 查看执行结果 ##
A. 查看数据库表的变化  
![](http://i.imgur.com/jNdIA4r.png)

B. 查看控制台的输出

    /*==============================================================*/
    /* Table: T_STAFF   */
    /*==============================================================*/
    DROP TABLE IF EXISTS T_STAFF;
    create table T_STAFF  ( 
       STAFF_ID VARCHAR(32)  NOT NULL  comment 'UUID',
       STAFF_NAME   VARCHAR(16)  comment '员工姓名',
       PRIMARY KEY (STAFF_ID)
    );
    
    alter table T_STAFF comment '员工表';



