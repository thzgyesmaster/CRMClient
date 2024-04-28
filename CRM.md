# CRM客户关系管理

## 分析与设计

### 技术框架

1. view: html , css , js , jquery , bootstarp , jsp
2. controller: springmvc
3. service: javase
4. Mapper: mybatis
5. spring 整合

### 核心业务

#### 系统管理功能:

用户登录、登录验证、安全退出

#### 业务管理功能: 

市场活动、线索、客户和联系人、交易、统计图表

### 物理模型设计

#### crm表结构:

##### 系统管理

1. tbl_user 用户表
2. tbl_dic_type 数据字典类型表(下拉列表)
3. tbl_dic_value 数据字典值

##### 业务管理

1. tbl_activity 市场活动表

2. tbl_activity_remark 市场活动备注表

   

3. tbl_clue 线索表

4. tbl_clue_remark 线索备注表

   

5. tbl_customer 客户表

6. tbl_customer_remark 客户备注表 

   

7. tbl_contacts 联系人表

8. tbl_contacts_remark 联系人备注表

9. tbl_contact_activity_relation 联系人和市场活动关联关系表

   

10. tbl_tran 交易表

11. tbl_tran_remark 交易备注表

12. tbl_tran_history 交易历史表

##### 主要表字段
主键字段不用自增，使用UUID

## 代码实现

### 功能一：首页
IndexController(index) -> index.jsp -> UserController(toLogin) -> login.jsp

### 功能二：登录
