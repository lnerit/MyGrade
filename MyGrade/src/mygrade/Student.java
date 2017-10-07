/*
 * Author: Lenz L Nerit
 * Date:07/10/2017
 * Module: This class handles database operations for STUDENT
 * Description: This is a real-time grade data logging app.
 * The back-end is MS SQL Server and the front-end using Java.
 * This is a class project for CS104 (2017 class).
 */
package mygrade;
import java.sql.*;
public class Student {
	//private int selectedOption;
	private String StudentId,FirstName,LastName,Major;
	dbConnection dbCon=new dbConnection();
	Connection c=dbCon.dbConn();
	public Student(String StudentId,String FirstName,String LastName,String Major) {
		//selectedOption=userSelectedOption;
		this.StudentId=StudentId;
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.Major=Major;
	}
	public void Actions(int userSelectedOption) {
		switch(userSelectedOption) {
		case 0:
			getStudentRecord();
			break;

		case 1:
			InsertStudentRecord(StudentId,FirstName,LastName,Major);
			break;
		case 2:
			UpdateStudentRecord(StudentId,FirstName,LastName,Major);
			break;
		case 3:
			DeleteStudentRecord(StudentId);
			break;
		}
		
	}
	private void InsertStudentRecord(String StudentId,String FirstName,String LastName,String Major) {
		try {
			String sqlString="INSERT INTO Student VALUES('"+StudentId+"','"+FirstName+"','"+LastName+"','"+Major+"')";
				Statement s=c.createStatement();
				boolean r=s.execute(sqlString);
				int status=s.getUpdateCount();
				if(status>0) {
					System.out.println("New Student Record inserted successfully...");
				}else {
					System.out.println("Insert operation unsuccessful...");
				}
			
			
			 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.out.println("Error occured while closing connection...");
				}
			}
		}
	}
	private void UpdateStudentRecord(String StudentId,String FirstName,String LastName,String Major) {
		try {
		
			String sqlString="UPDATE Student SET FirstName=?,LastName=?,Major=? WHERE StudentId=?";
			//if(c!=null) {
				PreparedStatement s=c.prepareStatement(sqlString,Statement.RETURN_GENERATED_KEYS);
				s.setString(4, StudentId);
				s.setString(1, FirstName);
				s.setString(2, LastName);
				s.setString(3, Major);
				s.executeUpdate();
				int r=s.getUpdateCount();
				if(r>0) {
				System.out.println("Student record UPDATE SUCCESSFULLY...");
				}else {
					System.out.println("Student Record UPDATE UNSUCCESSFUL");
				}
			//}
			 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.out.println("Error occured while closing connection...");
				}
			}
		}
	}
	private void DeleteStudentRecord(String StudentId) {
		try {
		
			String sqlString="DELETE FROM Student WHERE StudentId='"+StudentId+"'";
			if(c!=null) {
				Statement s=c.createStatement();
				boolean r=s.execute(sqlString);
				int status=s.getUpdateCount();
				if(status>0) {
					System.out.println("Student Record ["+StudentId +"] DELETED SUCCESSFULLY...");
				}else {
					System.out.println("DELETE OPERATION UNSUCCESSFUL...");
				}
				
			}
			 
		}catch(Exception e) {
			
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.out.println("Error occured while closing connection...");
				}
			}
		}
	}
	private void getStudentRecord() {
		String sqlStr="SELECT * FROM Student ORDER BY FirstName";
		
		Statement s;
		try {
			if(c.isClosed()) {
				c=dbCon.dbConn();
			}
			s = c.createStatement();
			ResultSet rs=s.executeQuery(sqlStr);
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			//System.out.println("Student ID\tFirstName\tLastName\tMajor");
			 //System.out.println("-----------------------------------------------------------");
			 String[] sx= {"Student ID","First Name","Last Name","Major"};
			 StringBuilder sb=new StringBuilder();
			 for(int x=0;x<4;x++) {
				 sb.append(String.format("| %-10s",sx[x]));
			 }
			 sb.append("\n");
			 sb.append("--------------------------------------------------------------------\n");
			while(rs.next()) {
				//System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));
				for(int i=1;i<=4;i++) {
					sb.append(String.format("| %-10s", rs.getString(i).trim()));
					//System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t\t\t"+rs.getString(3));
				}
				sb.append("\n");
			}
			 System.out.println(sb.toString());
			 System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (SQLException e) {
			System.out.print("Error occured while retrieving student records...Please try again!"+e.getMessage());
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					System.out.println("Error occured while closing connection...");
				}
			}
		}
		
	}
}
