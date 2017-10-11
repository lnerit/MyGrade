/*
 * Author: Lenz L Nerit
 * Date:07/10/2017
 * Module: This class handles database operations for COURSEs
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

public class Course {
	private String CourseCode="",CourseName="";
	private int Points=0,Level=0;

	dbConnection dbCon=new dbConnection();
	Connection c=dbCon.dbConn();
	
	public Course(String CourseCode,String CourseName,int Points,int Level) {
		this.CourseCode=CourseCode;
		this.CourseName=CourseName;
		this.Points=Points;
		this.Level=Level;
	}
	
		public void Actions(int userSelectedOption) {
			switch(userSelectedOption) {
			case 0:
				getCourseRecord();
				break;

			case 1:
				InsertCourseRecord(CourseCode,CourseName,Points,Level);
				break;
			case 2:
				UpdateCourseRecord(CourseCode,CourseName,Points,Level);
				break;
			case 3:
				DeleteCourseRecord(CourseCode);
				break;
			}
			
		}
		private void InsertCourseRecord(String CourseCode,String CourseName,int Points,int Level) {
			try {
				String sqlString="INSERT INTO Courses VALUES(?,?,?,?)";
					PreparedStatement s=c.prepareStatement(sqlString);
					s.setString(1, CourseCode);
					s.setString(2, CourseName);
					s.setInt(3, Points);
					s.setInt(4, Level);
					int r=s.executeUpdate();
					int status=s.getUpdateCount();
					if(status>0) {
						System.out.println("New Course Record inserted successfully...");
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
		private void UpdateCourseRecord(String CourseCode,String CourseName,int Points, int Level) {
			try {
			
				String sqlString="UPDATE Courses SET CourseName=?,Points=?,OfferedToLevelOfStudy=? WHERE CourseCode=?";
				//if(c!=null) {
					PreparedStatement s=c.prepareStatement(sqlString,Statement.RETURN_GENERATED_KEYS);
					s.setString(4, CourseCode);
					s.setString(1, CourseName);
					s.setInt(2, Points);
					s.setInt(3, Level);
					s.executeUpdate();
					int r=s.getUpdateCount();
					if(r>0) {
					System.out.println("Course record UPDATE SUCCESSFULLY...");
					}else {
						System.out.println("Course Record UPDATE UNSUCCESSFUL");
					}
					s.close();
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
		private void DeleteCourseRecord(String CourseCode) {
			try {
			
				String sqlString="DELETE FROM Courses WHERE CourseCode=?";
				if(c!=null) {
					PreparedStatement s=c.prepareStatement(sqlString);
					s.setString(1, CourseCode);
					boolean r=s.execute();
					int status=s.getUpdateCount();
					if(status>0) {
						System.out.println("Course Record ["+CourseCode +"] DELETED SUCCESSFULLY...");
					}else {
						System.out.println("DELETE OPERATION UNSUCCESSFUL...");
					}
					s.close();
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
		
		private void getCourseRecord() {
			String sqlStr="SELECT * FROM Courses ORDER BY CourseCode ASC";
			
			Statement s;
			try {
				if(c.isClosed()) {
					c=dbCon.dbConn();
				}
				s = c.createStatement();
				ResultSet rs=s.executeQuery(sqlStr);
				
				 String[] sx= {"Course Code","Course Name","Points","Level Of Study |"};
				 StringBuilder sb=new StringBuilder();
				 sb.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
				 for(int x=0;x<4;x++) {
					 sb.append(String.format("| %-40s",sx[x]));
				 }
				 sb.append("\n");
				 sb.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
				while(rs.next()) {
					for(int i=1;i<=4;i++) {
						sb.append(String.format("| %-40s", rs.getString(i).trim()));
				
					}
					//sb.append(String.format("%-10s","|"));
					sb.append("\n");
					 sb.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
				
				}
				// sb.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
				 System.out.println(sb.toString()+"\n");
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


