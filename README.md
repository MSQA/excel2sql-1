# excel2sql项目是根据excel中的内容生成建表语句 #


## 1. 编辑excel文件 ##
在sample.xlsx文件中，新建一个名称为员工表的sheet，它的内容如下：  
![](http://i.imgur.com/hLjn5Nz.png)

## 2. 配置sample-app.properties ##

    #excel文件 
    excel.file=sample.xlsx
    sheet.names=员工表
    
    #保存SQL脚本
    script.save=true  
    script.file=src/main/resources/generate/script20161222.sql
    
    #直接执行脚本
    execute.sql=true


## 3. 配置sample-jdbc.properties ##
	jdbc.driver=com.mysql.jdbc.Driver
	jdbc.url=jdbc:mysql://localhost:3066/test?characterEncoding=utf8&autoReconnect=true
	jdbc.username=root
	jdbc.password=  

## 4.执行SqlGenRun.main() ##
程度会根据配置，是否保存生成的SQL脚本，是否直接执行脚本到数据库。
    
	public void main(String[] args) {		
		String[] files = new String[] { "conf/sample-jdbc.properties", "conf/sample-app.properties", };
		Context context = ContextParser.parseContext(files);
		new ExcelParser().execute(context);
	}


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


6. 不足之处，每次执行时它会删除库表，导致测试数据丢失。
7. 