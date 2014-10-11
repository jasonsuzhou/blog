My Blog

[![Build Status](http://121.42.11.139/jenkins/job/blog/)](http://121.42.11.139/jenkins/job/blog/)
---
My personal blog.

Using jquery, jsp, springmvc, springjdbctemplate, mysql

- Configure the data source JNDI in your tomcat context.xml
```
		<Resource name="jndi/jasonblog"   
            auth="Container"   
            type="javax.sql.DataSource"   
            driverClassName="com.mysql.jdbc.Driver"   
            url="jdbc:mysql://localhost:3306/jasonblog"   
            username="${username}>"   
            password="${password}"   
            maxActive="20"   
            maxIdle="10"   
            maxWait="10000"/>
```