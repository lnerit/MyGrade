package mygrade;
import java.sql.*;
public class SemesterCourse {

	private String CourseCode="";
	private int Semester=0;
	dbConnection dbCon=new dbConnection();
	Connection c=dbCon.dbConn();
	public SemesterCourse() {
		// TODO Auto-generated constructor stub
	}
	public SemesterCourse(String CourseCode,int Semester) {
		this.CourseCode=CourseCode;
		this.Semester=Semester;
	}
	
	private void InsertIntoSemesterCourse(String cCode,int Semester) {
		String s="INSERT INTO SemesterCourse VALUES(?,?)";
		try {
			PreparedStatement ps=c.prepareStatement(s);
			ps.setString(1, cCode);
			ps.setInt(2, Semester);
			int result=ps.executeUpdate();
			if(result>0) {
				System.out.print("Record inserted successfully");
			}else {
				System.out.print("Insert operation fail...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
