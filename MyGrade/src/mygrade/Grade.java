/*
 * Author: Lenz L Nerit
 * Date:07/10/2017
 * Module: This class handles database operations for GRADES 
 * Description: This is a real-time grade data logging app.
 * The back-end is MS SQL Server and the front-end using Java.
 * This is a class project for CS104 (2017 class).
 */
package mygrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Grade {

	private String StudentId="",CourseCode="",Grade="F";
	private int Year=0,Semester=0;
	
	private int Points=0,Level=0;
	dbConnection dbCon=new dbConnection();
	Connection c=dbCon.dbConn();
	
	public Grade(String StudentId,String CourseCode,int Year,int Semester,String Grade) {
		this.StudentId=StudentId;
		this.CourseCode=CourseCode;
		this.Year=Year;
		this.Semester=Semester;
		this.Grade=Grade;
	}
	
		public void Actions(int userSelectedOption) {
			switch(userSelectedOption) {
			case 0:
				getGradeRecord();
				break;

			case 1:
				InsertGradeRecord(StudentId,CourseCode,Year,Semester,Grade);
				break;
			case 2:
				UpdateGradeRecord(StudentId,CourseCode,Year,Semester,Grade);
				break;
			case 3:
				DeleteGradeRecord(StudentId,CourseCode);
				break;
			}
		}
		
		private void InsertGradeRecord(String StudentId,String CourseCode,int Year,int Semester,String Grade) {
			try {
				String sqlString="INSERT INTO Grade VALUES(?,?,?,?,?)";
					PreparedStatement s=c.prepareStatement(sqlString);
					s.setString(1, StudentId);
					s.setString(2, CourseCode);
					s.setInt(3, Year);
					s.setInt(4, Semester);
					s.setString(5, Grade);
					boolean r=s.execute();
					int status=s.getUpdateCount();
					if(status>0) {
						System.out.println("New Grade Record inserted successfully...");
					}else {
						System.out.println("INSERT OPERATION UNSUCCESSFUL...");
					}
					s.close();
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
		
		private void UpdateGradeRecord(String StudentId,String CourseCode,int Year,int Semester,String Grade) {
			try {
				String sqlString="UPDATE Grade SET YearOfStudy=?,Semester=?,Grade=? WHERE CourseCode=? AND StudentId=?";
				
					PreparedStatement s=c.prepareStatement(sqlString,Statement.RETURN_GENERATED_KEYS);
					s.setString(5, StudentId);
					s.setString(4, CourseCode);
					s.setInt(1, Year);
					s.setInt(2, Semester);
					s.setString(3, Grade);
					s.executeUpdate();
					int r=s.getUpdateCount();
					if(r>0) {
					System.out.println("Grade record UPDATE SUCCESSFULLY...");
					}else {
						System.out.println("Grade Record UPDATE UNSUCCESSFUL");
					}
					s.close();
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
		
		private void DeleteGradeRecord(String StudentId,String CourseCode) {
			try {
				String sqlString="DELETE FROM Grade WHERE StudentId=? AND CourseCode=?";
				if(c!=null) {
					PreparedStatement s=c.prepareStatement(sqlString);
					s.setString(1, StudentId);
					s.setString(2, CourseCode);
					int r=s.executeUpdate();
					int status=s.getUpdateCount();
					if(status>0) {
						System.out.println("Grade Record ["+StudentId+"-->"+CourseCode +"] DELETED SUCCESSFULLY...");
					}else {
						System.out.println("DELETE OPERATION UNSUCCESSFUL...");
					}
					s.close();
				}
			}catch(Exception e) {
				System.out.println("An error occured while deleting record..."+ e.getMessage());
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
		
		private void getGradeRecord() {
			String sqlStr="SELECT * FROM Grade ORDER BY YearOfStudy DESC,Semester ASC";
			
			Statement s;
			try {
				if(c.isClosed()) {
					c=dbCon.dbConn();
				}
				s = c.createStatement();
				ResultSet rs=s.executeQuery(sqlStr);
				
				 String[] sx= {"Student ID","Course Code","Year","Semester","Grade"};
				 StringBuilder sb=new StringBuilder();
				 sb.append("--------------------------------------------------------------------\n");
				 for(int x=0;x<5;x++) {
					 sb.append(String.format("| %-10s",sx[x]));
				 }
				 
				 sb.append("\n");
				 sb.append("--------------------------------------------------------------------\n");
				while(rs.next()) {
					for(int i=1;i<=5;i++) {
						sb.append(String.format("| %-10s", rs.getString(i).trim()));
					}
					sb.append("\n");
				}
				 sb.append("--------------------------------------------------------------------\n");
				System.out.println(sb.toString());
				rs.close();
				s.close();
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
