package mygrade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		//Call the database connection and check the status
		dbQueryFunctions q=new dbQueryFunctions();
		dbConnection c=new dbConnection();
		
		System.out.println("*******Please select and action from the menu*********");
		System.out.println("1==>Insert/Update/Delete Grade");
		System.out.println("2==>Insert/update/Delete Course");
		System.out.println("3==>Insert/Update/Delete Student");
		System.out.println("4==>Retrieve Grades");
		System.out.println("5==>Retrieve Courses");
		System.out.println("6==>Exit the program");
		System.out.println("******************************************************");
		String input="6";
		do {
			Scanner scanner=new Scanner(System.in);
			 input = scanner.nextLine();
			if(input.toString()!="") {
				int userInput=Integer.parseInt(input.toString());
				switch(userInput) {
				case 1:
					if (c.dbConn()!=null) {
						String s="SELECT * FROM Student";
						
						ResultSet rs=q.MyGradeRecords(s);
						try {
							while(rs.next()) {
								System.out.print("StudentId:"+rs.getString(1) + "\nFirst Name:"+rs.getString(2));
							}
						} catch (SQLException e) {
							System.out.print("Error occured while processing records...");
						}finally {
							try {
								c.dbConn().close();
							} catch (SQLException e) {
								System.out.print("Error occured while closing connection");
							}
						}
					}
					
					break;
				case 2: 
					System.out.print("Case 2 comes here");
					break;
				case 3:
					System.out.print("Case 3 comes here");
					break;
				case 4:
					System.out.print("Case 4 comes here");
						break;
				case 5:
					System.out.print("Case 5 comes here");
					break;
				case 6:
					System.out.print("Case 6 comes here");
					continue;
				}
			}
			
		}while(input.toString()!="6");

	}

}
