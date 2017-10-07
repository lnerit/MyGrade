/*
 * Author: Lenz L Nerit
 * Date:07/10/2017
 * Description: This is a real-time grade data logging app.
 * The back-end is MS SQL Server and the front-end using Java.
 * This is a class project for CS104 (2017 class).
 */
package mygrade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {

	private static void ActionPrompts() {
		System.out.println("***************MAIN MENU******************************");
		System.out.println("1==>Insert/Update/Delete Student");
		System.out.println("2==>Insert/update/Delete Course");
		System.out.println("3==>Insert/Update/Delete Grade");
		System.out.println("4==>Retrieve Grades");
		System.out.println("5==>Retrieve Courses");
		System.out.println("6==>Exit the program");
		System.out.println("******************************************************");
	}
	private static void ActionPromptStudent() {
		System.out.println("*******STUDENT RECORDS MENU*********");
		System.out.println("0==>View All Records");
		System.out.println("1==>Insert new student record");
		System.out.println("2==>Update student record");
		System.out.println("3==>Delete student record");
		System.out.println("4==>Exit to main menu");
		System.out.println("******************************************************");
	}
	private static void ActionPromptCourse() {
		System.out.println("*******COURSE RECORDS MENU*********");
		System.out.println("0==>View All Course records");
		System.out.println("1==>Insert new course record");
		System.out.println("2==>Update Course record");
		System.out.println("3==>Delete Course record");
		System.out.println("4==>Exit to main menu");
		System.out.println("******************************************************");
	}
	
	private static void ActionPromptGrade() {
		System.out.println("*******GRADE RECORDS MENU*********");
		System.out.println("0==>View All Grade records");
		System.out.println("1==>Insert new Grade record");
		System.out.println("2==>Update Grade record");
		System.out.println("3==>Delete Grade record");
		System.out.println("4==>Exit to main menu");
		System.out.println("******************************************************");
	}
	private static boolean isDigit(String input) {
		if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6")) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		//Call the database connection and check the status
		dbQueryFunctions q=new dbQueryFunctions();
		dbConnection c=new dbConnection();
		ActionPrompts();

		String input="6";
		a:
		do {
		
			Scanner scanner=new Scanner(System.in);
			 input = scanner.nextLine();
			 
			 if(!isDigit(input.toString())){
				 System.out.println("PLEASE ENTER ONE OF THE FOLLOWING OPTIONS ONLY [1,2,3,4,5,6]");
				 continue a;
			 }
			 
			if(input.toString()!="") {
				int userInput=Integer.parseInt(input.toString());
				switch(userInput) {
				case 1:
						String sInput="4";
						b:
						do {
							ActionPromptStudent();
							
							 @SuppressWarnings("resource")
							Scanner gradeOption=new Scanner(System.in);
							 sInput = gradeOption.nextLine();
							 
							 if(!isDigit(sInput)){
								 System.out.println("PLEASE ENTER ONE OF THE FOLLOWING OPTIONS ONLY [0,1,2,3,4]");
								 continue b;
							 }
							 
							 if(!sInput.equals("") && !sInput.equals("4")) {
								
								 switch(Integer.parseInt(sInput)) {
								 case 0: //for viewing records
									
									 Student student=new Student("","","","");
									 student.Actions(0);
									
									 break;
								 case 1://Insert new student record
									 String studentid="",firstname="",lastname="",major="";
									 //Take inputs from user for input into student stable
									 System.out.print("Enter Student ID:");
									 Scanner sStdId=new Scanner(System.in);
									 studentid=sStdId.nextLine();
									 System.out.print("Enter FirstName:");
									 Scanner sFirstName=new Scanner(System.in);
									 firstname=sFirstName.nextLine();
									 System.out.print("Enter Last Name:");
									 Scanner sLastName=new Scanner(System.in);
									 lastname=sLastName.nextLine();
									 System.out.print("Enter Major Code:");
									 Scanner sMajor=new Scanner(System.in);
									 major=sMajor.next();
									 
									 if(studentid.equals("") || firstname.equals("") || lastname.equals("") || major.equals("") ) {
										 System.out.println("All input details are required.Please try again...");
										 break;
									 }else {
										 Student studentInsert=new Student(studentid,firstname,lastname,major);
										 studentInsert.Actions(1);
										 studentInsert.Actions(0);
									 }
									 break;
								 case 2://update student record
									 String ustudentid="",ufirstname="",ulastname="",umajor="";
									 //Take inputs from user for input into student stable
									 System.out.print("Enter Student ID (Update):");
									 Scanner uStdId=new Scanner(System.in);
									 ustudentid=uStdId.nextLine();
									 System.out.print("Enter New FirstName:");
									 Scanner uFirstName=new Scanner(System.in);
									 ufirstname=uFirstName.nextLine();
									 System.out.print("Enter New Last Name:");
									 Scanner uLastName=new Scanner(System.in);
									 ulastname=uLastName.nextLine();
									 System.out.print("Enter New Major Code:");
									 Scanner uMajor=new Scanner(System.in);
									 umajor=uMajor.next();
									 
									 if(ustudentid.equals("") || ufirstname.equals("") || ulastname.equals("") || umajor.equals("") ) {
										 System.out.println("All input details are required.Please try again...");
										 break;
									 }else {
										 Student studentupdate=new Student(ustudentid,ufirstname,ulastname,umajor);
										 studentupdate.Actions(2);
										 studentupdate.Actions(0);
									 }
									 break;
								 case 3://Delete student record
										 String dStdid="";
										 System.out.print("Enter Student ID:");
										 Scanner dStdId=new Scanner(System.in);
										 dStdid=dStdId.nextLine();
										 
										 if(dStdid.equals("")) {
											 System.out.println("Please enter a student Id...");
											 break;
										 }else {
											 Student deletestudent=new Student(dStdid,"","","");
											 deletestudent.Actions(3);
											 deletestudent.Actions(0);
										 }
									 break;
								 }
							 }
						}while(!sInput.equals("4"));
						ActionPrompts();
					break;
				case 2: 
					String cInput="4";
					c:
					do {

						ActionPromptCourse();
						 
						 Scanner courseOption=new Scanner(System.in);
						 cInput = courseOption.nextLine();
						 
						 if(!isDigit(cInput)){
							 System.out.println("PLEASE ENTER ONE OF THE FOLLOWING OPTIONS ONLY [0,1,2,3,4]");
							 continue c;
						 }
						 
						 if(!cInput.equals("") && !cInput.equals("4")) {
							
							 switch(Integer.parseInt(cInput)) {
							 case 0: //for viewing records
								
								 Course course=new Course("","",0,0);
								 course.Actions(0);
								 break;
							 case 1://Insert new student record
								 String coursecode="",coursename="",points="",level="";
								 //Take inputs from user for input into student stable
								 System.out.print("Enter Course Code:");
								 Scanner scoursecode=new Scanner(System.in);
								 coursecode=scoursecode.nextLine();
								 System.out.print("Enter Course Name:");
								 Scanner sCourseName=new Scanner(System.in);
								 coursename=sCourseName.nextLine();
								 System.out.print("Enter Points:");
								 Scanner sPoints=new Scanner(System.in);
								 points=sPoints.next();
								 
								 System.out.print("Offered To [Level of Study]:");
								 Scanner sLevel=new Scanner(System.in);
								 level=sLevel.next();
								 
								 if(coursecode.equals("") ||coursename.equals("") || points.equals("") || level.equals("") ) {
									 System.out.println("All input details are required.Please try again...");
									 break;
								 }else {
									 Course scourse=new Course(coursecode,coursename,Integer.parseInt(points),Integer.parseInt(level));
									 scourse.Actions(1);
									 scourse.Actions(0);
								 }
								 break;
							 case 2://update student record
								 String ucoursecode="",ucoursename="",upoints="",ulevel="";
								 //Take inputs from user for input into student stable
								 System.out.print("Enter Course Code:");
								 Scanner uCoursecode=new Scanner(System.in);
								 ucoursecode=uCoursecode.nextLine();
								 System.out.print("Enter New Course Name:");
								 Scanner uCourseName=new Scanner(System.in);
								 ucoursename=uCourseName.nextLine();
								 System.out.print("Enter New Points:");
								 Scanner uPoints=new Scanner(System.in);
								 upoints=uPoints.next();
								 System.out.print("Offered To [Level of Study]:");
								 
								 Scanner uLevel=new Scanner(System.in);
								 ulevel=uLevel.next();
								 
								 if(ucoursecode.equals("") ||ucoursename.equals("") || upoints.equals("") || ulevel.equals("") ) {
									 System.out.println("All input details are required.Please try again...");
									 break;
								 }else {
									 Course ucourse=new Course(ucoursecode,ucoursename,Integer.parseInt(upoints),Integer.parseInt(ulevel));
									 ucourse.Actions(2);
									 ucourse.Actions(0);
								 }
								 break;
							 case 3://Delete student record
									 String dcourseCode="";
									 System.out.print("Enter Course Code [for DELETE]:");
									 Scanner dCourseCode=new Scanner(System.in);
									 dcourseCode=dCourseCode.nextLine();
									 if(dcourseCode.equals("")) {
										 System.out.println("Please enter a course code to remove the course record...");
										 break;
									 }else {
										 Course deleteCourse=new Course(dcourseCode,"",0,0);
										 deleteCourse.Actions(3);
										 deleteCourse.Actions(0);
									 }
								 break;
							 }
						 }
					}while(!cInput.equals("4"));
					ActionPrompts();
					break;
				case 3:
					String gInput="4";
					d:
					do {
						/*
						 * These are options to select for inserting,updating,deleting and retrieving records in the student table
						 * */
						ActionPromptGrade();
						 
						 Scanner gradeOption=new Scanner(System.in);
						 gInput = gradeOption.nextLine();
						 
						 if(!isDigit(gInput)){
							 System.out.println("PLEASE ENTER ONE OF THE FOLLOWING OPTIONS ONLY [0,1,2,3,4]");
							 continue d;
						 }
						 
						 if(!gInput.equals("") && !gInput.equals("4")) {
							
							 switch(Integer.parseInt(gInput)) {
							 case 0: //for viewing records
								
								 Grade grade=new Grade("","",0,0,"");
								 grade.Actions(0);
								 break;
							 case 1://Insert new student record
								 String gstudentid="",gcoursecode="",gyear="",gsemester,ggrade="";
								 //Take inputs from user for input into student stable
								 System.out.print("Enter Student ID:");
								 Scanner gStudentId=new Scanner(System.in);
								 gstudentid=gStudentId.nextLine();
								 
								 System.out.print("Enter Course Code:");
								 Scanner gCourseCode=new Scanner(System.in);
								 gcoursecode=gCourseCode.nextLine();
								 
								 System.out.print("Enter Academic Year:");
								 Scanner gYear=new Scanner(System.in);
								 gyear=gYear.next();
								 
								 System.out.print("Semester:");
								 Scanner gSemester=new Scanner(System.in);
								 gsemester=gSemester.next();
								 
								 System.out.print("Grade Scored:");
								 Scanner gGrade=new Scanner(System.in);
								 ggrade=gGrade.next();
								 
								 if(gstudentid.equals("") ||gcoursecode.equals("") || gyear.equals("") || gsemester.equals("") || ggrade.equals("") ) {
									 System.out.println("All input details are required.Please try again...");
									 break;
								 }else {
									 Grade graderecord=new Grade(gstudentid,gcoursecode,Integer.parseInt(gyear),Integer.parseInt(gsemester),ggrade);
									 graderecord.Actions(1);
									 graderecord.Actions(0);
								 }
								 break;
							 case 2://update student record
								 String ugstudentid="",ugcoursecode="",ugyear="",ugsemester,uggrade="";
								 //Take inputs from user for input into student stable
								 System.out.print("Enter Student ID:");
								 Scanner ugStudentId=new Scanner(System.in);
								 gstudentid=ugStudentId.nextLine();
								 
								 System.out.print("Enter Course Code:");
								 Scanner ugCourseCode=new Scanner(System.in);
								 gcoursecode=ugCourseCode.nextLine();
								 
								 System.out.print("Enter Academic Year:");
								 Scanner ugYear=new Scanner(System.in);
								 gyear=ugYear.next();
								 
								 System.out.print("Semester:");
								 Scanner ugSemester=new Scanner(System.in);
								 gsemester=ugSemester.next();
								 
								 System.out.print("Grade Scored:");
								 Scanner ugGrade=new Scanner(System.in);
								 ggrade=ugGrade.next();
								 
								 if(gstudentid.equals("") ||gcoursecode.equals("") || gyear.equals("") || gsemester.equals("") || ggrade.equals("") ) {
									 System.out.println("All input details are required.Please try again...");
									 break;
								 }else {
									 Grade graderecord=new Grade(gstudentid,gcoursecode,Integer.parseInt(gyear),Integer.parseInt(gsemester),ggrade);
									 graderecord.Actions(2);
									 graderecord.Actions(0);
								 }
								 break;
							 case 3://Delete student record
									 String dgstudentId="",dgcoursecode="";
									 System.out.print("Enter Student ID:");
									 Scanner dgStudentId=new Scanner(System.in);
									 dgstudentId=dgStudentId.nextLine();
									 
									 System.out.print("Enter Course Code [for DELETE]:");
									 Scanner dgCourseCode=new Scanner(System.in);
									 dgcoursecode=dgCourseCode.nextLine();
									 
									 if(dgcoursecode.equals("") || dgstudentId.equals("")) {
										 System.out.println("Please enter a course code to remove the course record...");
										 break;
									 }else {
										 Grade deleteGrade=new Grade(dgstudentId,dgcoursecode,0,0,"");
										 deleteGrade.Actions(3);
										 deleteGrade.Actions(0);
									 }
								 break;
							 }
						 }
					}while(!gInput.equals("4"));
					ActionPrompts();
					break;
				case 4:
					System.out.print("Case 4 comes here");
						break;
				case 5:
					System.out.print("Case 5 comes here");
					break;
				case 6:
					System.exit(0);
					System.out.print("Case 6 comes here");
					//6continue;
				}
			}
			
		}while(input.toString()!="6");

	}

}
