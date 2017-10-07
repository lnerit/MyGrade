/*
 * Author: Lenz L Nerit
 * Date:07/10/2017
 * Description: Database connection class
 */
package mygrade;
import java.sql.*;
import java.io.*;
public class dbConnection {
	
public Connection dbConn(){

		String dbConnectionString="jdbc:sqlserver://localhost;user=sa;password=root;database=MyGrades";

	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	} catch (ClassNotFoundException e1) {
		//e1.printStackTrace();
		System.out.print("Class Not Found...");
	}
	try {
		Connection conn=DriverManager.getConnection(dbConnectionString);
		if(conn!=null){
			return conn;
		}
	} catch (SQLException e) {
		//Catch exception here
	}
	return null;
}
}
