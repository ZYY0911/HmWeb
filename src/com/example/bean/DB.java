package com.example.bean;

import java.sql.Connection;
import java.sql.DriverManager;

/**
* Create by 张瀛煜
* On 2020年12月7日 下午9:24:00
*/
public class DB {
    public   Connection conn = null;  
public void getConn() {

	try {  
		             //获取连接  
		                 String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //加载JDBC驱动   
		                 String dbURL = "jdbc:sqlserver://192.168.19.3:1433;DatabaseName=HKManager";  //连接服务器和数据库sample
		                 //运行SQL语句  
		                 String userName = "sa";  //默认用户名 
		                 String userPwd = "1234";  
		                 Class.forName(driverName);
		                 conn = DriverManager.getConnection(dbURL, userName, userPwd);
		                 if(conn!=null)
		                 {
		                 System.out.println("Connection Successful!");  //如果连接成功 控制台输出
		                 }
		                 else{
		                 
		                        System.out.println("Connection fail!");  
		                 }
	}catch (Exception e) {
		// TODO: handle exception
	}
}
}

