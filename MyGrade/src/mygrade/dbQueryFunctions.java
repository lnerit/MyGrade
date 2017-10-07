/*
 * Author: Lenz L Nerit
 * Date:07/10/2017
 * Module: Query functions are defined in this class.
 * Description: This is a real-time grade data logging app.
 * The back-end is MS SQL Server and the front-end using Java.
 * This is a class project for CS104 (2017 class).
 */
package mygrade;

import java.sql.*;

import javax.swing.JOptionPane;

public class dbQueryFunctions {
	static dbConnection conn=new dbConnection();
	static Connection c=conn.dbConn();
	
    public String ExecuteScalarQuery(String sqlString)
    {
       try{
            Statement statement=c.createStatement();
            ResultSet rs=statement.executeQuery(sqlString);
                    //string returnStr = cmd.ExecuteScalar().ToString();
                    String returnStr = (rs.getString(1) == null) ? "" : rs.getString(1);
                    return returnStr;
            
        }
        catch (Exception s)
        {
           JOptionPane.showMessageDialog(null,s.getMessage());
        }
        
        return "";
    }
    public ResultSet MyGradeRecords(String sqlQuery) {
    	
		try {
			Statement s = c.createStatement();
			ResultSet rSet=s.executeQuery(sqlQuery);
			return rSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public void ExecuteNonQuerySql(String sqlString)
    {
        try
        {
           Statement statement=c.createStatement();
           statement.executeQuery(sqlString);  
           
           
        }
        catch (Exception s)
        {
        	JOptionPane.showMessageDialog(null,s.getMessage());
        }
       
    }
}
