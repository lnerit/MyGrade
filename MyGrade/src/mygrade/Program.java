package mygrade;
import java.sql.*;

public class Program {

	private String ProgramCode="";
	private String ProgramName="";
	private String DepartmentName="";
	dbConnection dbCon=new dbConnection();
	Connection c=dbCon.dbConn();
	public Program() {
		// TODO Auto-generated constructor stub
	}
	public Program(String ProgramCode,String ProgramName,String DepartmentName) {
		this.ProgramCode=ProgramCode;
		this.ProgramName=ProgramName;
		this.DepartmentName=DepartmentName;
	}
	public void InputOption(int userInput) {
		switch(userInput) {
		case 0:
			//Read recaords
			InsertIntoProgram(ProgramCode,ProgramName,DepartmentName);
			break;
		case 1:
			//Insert records
			break;
		case 2:
			//Update records
			UpdateProgram(ProgramCode,ProgramName,DepartmentName);
			break;
		case 3:
			//Delete records
			DeleteProgram(ProgramCode);
			break;
		}
	}
	private void GetProgramRecords() {
		String sql="SELECT * FROM Program";
		Statement s;
		try {
			s = c.createStatement();
			ResultSet rs=s.executeQuery(sql);
			String[] sx= {"Program Code","Program Name","Deparment"};
			 StringBuilder sb=new StringBuilder();
			 sb.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
			 for(int x=0;x<3;x++) {
				 sb.append(String.format("| %-40s",sx[x]));
			 }
			 sb.append("\n");
			 sb.append("----------------------------------------------------------------------------------------------------------------------------------------------\n");
			while(rs.next()) {
				for(int i=1;i<=3;i++) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void DeleteProgram(String pCode) {
		String s="DELETE FROM Program WHERE ProgramCode=?";
		try {
			PreparedStatement ps=c.prepareStatement(s);
			ps.setString(1, pCode);
			int recordsaffected=ps.executeUpdate();
			if(recordsaffected>0) {
				System.out.print("Delete operation successful");
			}else {
				System.out.print("Delete operation unsuccessful");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void UpdateProgram(String pCode,String pName,String dName) {
		
		String s="UPDATE PROGRAM SET ProgramName=?,Department=? WHERE ProgramCode=?";
		try {
			PreparedStatement ps=c.prepareStatement(s);
			ps.setString(1, pName);
			ps.setString(2, dName);
			ps.setString(3, pCode);
			int recordsaffected=ps.executeUpdate();
			if(recordsaffected>0) {
				System.out.print("Update operation successful");
			}else {
				System.out.print("Update operation unsuccessful");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void InsertIntoProgram(String programcode,String programname,String departmentname) {
		try {
		if(c!=null) {
			String s="INSERT INTO Program VALUES(?,?,?)";
			PreparedStatement ps=c.prepareStatement(s);
			ps.setString(1, programcode);
			ps.setString(2, programname);
			ps.setString(3, departmentname);
			int result=ps.executeUpdate();
		}
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}finally {
			if(c!=null) {
				try {
				c.close();
				}catch(Exception ex) {
					System.out.print(ex.getMessage());
				}
			}
		}
	}

}
