/**
 * 
 */
package com.ssm.mf.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**   
 * @ClassName:  TestSql   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年3月23日 下午3:23:25     
 */
public class TestSql {
	public static void main(String [] args)

	 {

	  String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

	  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=DAZU";

	  String userName="sa";

	  String userPwd="123456";

	  try

	  {

	   Class.forName(driverName);

	   Connection dbConn =DriverManager.getConnection(dbURL,userName,userPwd);
	   String sql = "SELECT * FROM DEPARTMENT";
	   PreparedStatement ps = dbConn.prepareStatement(sql);
	   ResultSet rs = ps.executeQuery();
	   while(rs.next()) {
		   System.out.println(rs.getString(2));
	   }
	    System.out.println("连接数据库成功");

	  }

	  catch(Exception e)

	  {

	   e.printStackTrace();

	   System.out.print("连接失败");

	  }    

	 }


}
