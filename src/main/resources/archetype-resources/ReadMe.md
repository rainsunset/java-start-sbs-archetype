## com.rainsunset 项目Archetype脚手架 ##
### 数据库配置 ###
**mysql**
- pom
``` xml
<!-- MySql数据库驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.44</version>
</dependency>
```
- config -> datasource
``` yml
driver-class-name: com.mysql.jdbc.Driver
dialect: MYSQL
url: jdbc:mysql://localhost:3306/demo?allowMultiQueries=true
username: root
password: root
```

**sqlServer**
- pom
``` xml
<!--SqlServer 数据库驱动 for jdk8+ -->
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <version>7.0.0.jre8</version><!-- 注意jre版本 -->
</dependency>

```
- config -> datasource
``` yml
driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
url: jdbc:sqlserver://localhost:1433;DatabaseName=seerbigdata
username: sa
password : root
```

**Oracle**
- pom
``` xml
<!--oracle 驱动-->
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc6</artifactId>
    <version>11.2.0.4.0</version>
</dependency>
```
- yml
``` yml
driver-class-name: oracle.jdbc.OracleDriver
url: jdbc:oracle:thin:@172.20.170.20:1521/jsutf
username: unimax
password: unimax
```

### 事务配置 ###
 方案1.
[springboot mybatis 事务管理](https://www.cnblogs.com/kangoroo/p/8192503.html)
在Application启动类上添加注解：@EnableTransactionManagement
在对应Class或方法上添加注解:@Transactional
当方法抛出runtimeException时事务回滚

方案2.手动回滚，在需要回滚时增加：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();


### 需要查询总页码的分页配置 ###
- pom
``` xml
<!--分页-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.5</version>
</dependency>
```
- *ServiceImpl.java
``` java
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
......
@Override public PageInfo<Record> getUserRecordPageList(String userId, Integer page, Integer rows)
    throws Exception {
PageHelper.startPage(page, rows, true);
List<Record> recordList = null;
recordList = recordMapper.getRecordListByUserId(userId);
PageInfo<Record> pageInfo = new PageInfo<Record>(recordList);
return pageInfo;
    }
 ......
```

**结合[代码生成器使用](https://github.com/rainsunset/codeBuilder.git) 效果更佳**
