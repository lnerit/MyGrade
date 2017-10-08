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

	public static boolean isRecordExist(String sqlString,String... args) {
		try {
			if(c.isClosed()) {
				c=conn.dbConn();
			}
			String s =sqlString;
			PreparedStatement stmt=c.prepareStatement(s);
			for(int i=0;i<args.length-1;i++) {
				stmt.setString(i+1, args[i]);
			}
			ResultSet rs=stmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.print("Error occured...try again");
		}
		
		return false;
	}
	
    public static String ExecuteScalarQuery(String sqlString)
    {
       try{
            Statement statement=c.createStatement();
            ResultSet rs=statement.executeQuery(sqlString);
            String returnStr="";
            while(rs.next()){
                    returnStr = (rs.getString(1) == null) ? "" : rs.getString(1);
            }
            return returnStr;
        }
        catch (Exception s)
        {
           JOptionPane.showMessageDialog(null,s.getMessage());
        }
        
        return "";
    }
    public static void getSpecificStudentGrades(String studentId) {
    	try {
    	String s=" SELECT CourseCode,YearOfStudy as [Year],Semester,Grade FROM Grade WHERE StudentId=?";
    	String sname=" SELECT FirstName + ' ' + LastName + ' [ '+'"+studentId+"'+' ]' FROM Student WHERE StudentId='"+studentId+"'";
    	String studentName=ExecuteScalarQuery(sname);
    	
    	 String[] sx= {"Course Code","Year","Semester","Grade"};
    	StringBuilder sb=new StringBuilder();
    	sb.append("--------------------------------------------------------------------\n");
    	sb.append("Student Name: "+studentName+"\n");
    	 sb.append("--------------------------------------------------------------------\n");
    	 for(int x=0;x<4;x++) {
			 sb.append(String.format("| %-10s",sx[x]));
		 }
    	 sb.append("\n");
    	 sb.append("--------------------------------------------------------------------\n");
    	 
    	 PreparedStatement ps=c.prepareStatement(s);
    	 ps.setString(1,studentId);
    	 ResultSet rs=ps.executeQuery();
    	 while(rs.next()) {
				for(int i=1;i<=4;i++) {
					sb.append(String.format("| %-10s", rs.getString(i).trim()));
				}
				sb.append("\n");
			}
		 sb.append("--------------------------------------------------------------------\n");
		 System.out.print(sb.toString());
    	}catch(Exception e) {
    		System.out.print(e.getMessage());
    	}
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
