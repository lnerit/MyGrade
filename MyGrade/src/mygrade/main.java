/*
 * Author: Lenz L Nerit
 * Date:07/10/2017
 * Description: This is a real-time grade data logging app.
 * The back-end is MS SQL Server and the front-end using Java.
 * This is a class project for CS104 (2017 class).
 */
package mygrade;

import java.util.Scanner;

public class main {
	
	
	private static void ActionPrompts() {
		System.out.println("|***************MAIN MENU*************|");
		System.out.println("|-------------------------------------|");
		System.out.println("| 1 ==>  STUDENT INFORMATION          |");
		System.out.println("| 2 ==>  COURSE INFORMATION           |");
		System.out.println("| 3 ==>  STUDENT GRADE INFORMATION    |");
		System.out.println("| 4 ==>  RETRIEVE STUDENT GRADES      |");
		System.out.println("| 5 ==>  HELP                         |");
		System.out.println("| 6 ==>  Exit the program             |");
		System.out.println("**************************************");
		System.out.print("Enter input: ");
		
	}
	private static void ActionPromptStudent() {
		System.out.println("|*******STUDENT RECORDS MENU*********|");
		System.out.println("|------------------------------------|");
		System.out.println("| 0 ==> VIEW ALL STUDENT RECORDS     |");
		System.out.println("| 1 ==> INSERT NEW STUDENT RECORD    |");
		System.out.println("| 2 ==> UPDATE STUDENT RECORD        |");
		System.out.println("| 3 ==> DELETE STUDENT RECORD        |");
		System.out.println("| 4 ==> EXIT TO MAIN MENU            |");
		System.out.println("**************************************");
		System.out.print("Enter input: ");
	
	}
	private static void ActionPromptCourse() {
		
		
		System.out.println("|*******COURSE RECORDS MENU*********|");
		System.out.println("|------------------------------------|");
		System.out.println("| 0 ==> VIEW ALL COURSE RECORDS     |");
		System.out.println("| 1 ==> INSERT NEW COURSE RECORD    |");
		System.out.println("| 2 ==> UPDATE COURSE RECORD        |");
		System.out.println("| 3 ==> DELETE COURSE RECORD        |");
		System.out.println("| 4 ==> EXIT TO MAIN MENU            |");
		System.out.println("**************************************");
		System.out.print("Enter input: ");
		
	}
	
	private static void ActionPromptGrade() {
		
		System.out.println("|*******STUDENT-GRADE RECORDS MENU*********|");
		System.out.println("|------------------------------------|");
		System.out.println("| 0 ==> VIEW ALL GRADE RECORDS       |");
		System.out.println("| 1 ==> INSERT NEW  GRADE RECORD     |");
		System.out.println("| 2 ==> UPDATE GRADE RECORD          |");
		System.out.println("| 3 ==> DELETE GRADE RECORD          |");
		System.out.println("| 4 ==> EXIT TO MAIN MENU            |");
		System.out.println("**************************************");
		System.out.print("Enter input: ");
	}
	
private static void ActionPromptProgram() {
		
		System.out.println("|*******PROGRAM RECORDS MENU*********|");
		System.out.println("|------------------------------------|");
		System.out.println("| 0 ==> VIEW ALL PROGAM RECORDS       |");
		System.out.println("| 1 ==> INSERT NEW  PROGRAM RECORD     |");
		System.out.println("| 2 ==> UPDATE PROGRAM RECORD          |");
		System.out.println("| 3 ==> DELETE PROGRAM RECORD          |");
		System.out.println("| 4 ==> EXIT TO MAIN MENU            |");
		System.out.println("**************************************");
		System.out.print("Enter input: ");
	}
	static String sidPrepareStatement="SELECT COUNT(*) FROM Student WHERE StudentId=?";
	static String courseCodePrepareStatement="SELECT COUNT(*) FROM Courses WHERE CourseCode=?";
	static String programCodePreparedStatment="SELECT COUNT(*) FROM Program WHERE ProgramCode=?";
	
	static String sqluString="UPDATE Student SET FirstName=?,LastName=?,Major=? WHERE StudentId=?";
	static String sqliString="INSERT INTO Student VALUES(?,?,?,?)";
	String sqlString="DELETE FROM Student WHERE StudentId=?";
	
	private static boolean isDigit(String input) {
		if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("0")) {
			return true;
		}
		return false;
	}
	
	private static boolean isGradeValid(String input) {
		if(input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D") || input.equals("E") || input.equals("A")) {
			return true;
		}
		return false;
	}
	

     
	public static void main(String[] args) {

		ActionPrompts();
		String input="6";
		a:
		do {
			//Take input for MAIN MENU options
			Scanner scanner=new Scanner(System.in);
			 input = scanner.nextLine();
			 //If non of the numbers other than the options given a entered then,the user is prompted to enter again.
			 if(!isDigit(input.toString())){
				 System.out.println("PLEASE ENTER ONE OF THE FOLLOWING OPTIONS ONLY [1,2,3,4,5,6]");
				 continue a;
			 }
			 
			if(input.toString()!="") {
				int userInput=Integer.parseInt(input.toString());
				switch(userInput) {
				case 1: //For selecting student records
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
									 while(true) {
										 major=sMajor.nextLine();
										 if(dbQueryFunctions.isRecordExist(programCodePreparedStatment,major)) {
												break;
											}else {
												System.out.println("The Program Code you entered does not exists...Please check [Eg. BSCS] and retry!");
											}
										 
										 }
									 
									 
									 if(studentid.equals("") || firstname.equals("") || lastname.equals("") || major.equals("") ) {
										 System.out.println("All input details are required.Please try again...");
										 break;
									 }else {
										 Student studentInsert=new Student(studentid,firstname,lastname,major);
										 studentInsert.Actions(1);
										 studentInsert.Actions(0);
										 //dbQueryFunctions.ExecuteNonQuerySql(sqliString, studentid,firstname,lastname,major);
										 //Student std=new Student(0);
										 //std.Actions(1);
										 //std.Actions(0);
										 
									 }
									 break;
								 case 2://update student record
									 String ustudentid="",ufirstname="",ulastname="",umajor="";
									 //Take inputs from user for input into student stable
									 
									 System.out.print("Enter Student ID (Update):");
									 Scanner uStdId=new Scanner(System.in);
									
									 while(true) {
										 ustudentid=uStdId.nextLine();
										 if(dbQueryFunctions.isRecordExist(sidPrepareStatement,ustudentid)) {
												break;
											}else {
												System.out.println("The student ID you entered does not exists...Please check and retry!");
											}
										 
										 }
									 
									 System.out.print("Enter New FirstName:");
									 Scanner uFirstName=new Scanner(System.in);
									 ufirstname=uFirstName.nextLine();
									 
									 System.out.print("Enter New Last Name:");
									 Scanner uLastName=new Scanner(System.in);
									 ulastname=uLastName.nextLine();
									 
									 System.out.print("Enter New Major Code:");
									 Scanner uMajor=new Scanner(System.in);
									 while(true) {
										 umajor=uMajor.next();
										 if(dbQueryFunctions.isRecordExist(programCodePreparedStatment,umajor)) {
												break;
											}else {
												System.out.println("The Program Code you entered does not exists...Please check and retry!");
											}	 
										 }
									
									 
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
										 while(true) {
											 dStdid=dStdId.nextLine();
											 if(dbQueryFunctions.isRecordExist(sidPrepareStatement,dStdid)) {
													break;
												}else {
													System.out.println("The student ID you entered does not exists...Please check and retry!");
												}
											 
											 }
										 
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
					
				case 2: //Case 2 handles Course records
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
							 case 1://Insert new course record
								 String coursecode="",coursename="",level="";
								 int points=0;
								 int gradelevel=0;
								 //Take inputs from user for input into course stable
								 System.out.print("Enter Course Code:");
								 Scanner scoursecode=new Scanner(System.in);
								 coursecode=scoursecode.nextLine();
								 
								 System.out.print("Enter Course Name:");
								 Scanner sCourseName=new Scanner(System.in);
								 coursename=sCourseName.nextLine();
								 
								
								 System.out.print("Enter Points:");
								 Scanner sPoints=new Scanner(System.in);
								 String s="";
								 while(true) {
									 try{
										  s=sPoints.next();
										 points=Integer.parseInt(s);
										 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin");
									 }
								 }
								 
								 System.out.print("Offered To [Level of Study]:");
								 Scanner sLevel=new Scanner(System.in);
								 while(true) {
									 try{
										 gradelevel=Integer.parseInt(sLevel.next());
										 level=Integer.toString(gradelevel);
										 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin");
									 }
								 }
								 
								 if(coursecode.equals("") ||coursename.equals("") || Integer.toString(points).equals("") || level.equals("") ) {
									 System.out.println("All input details are required.Please try again...");
									 break;
								 }else {
									 Course scourse=new Course(coursecode,coursename,points,Integer.parseInt(level));
									 scourse.Actions(1);
									 scourse.Actions(0);
								 }
								 break;
								 
							 case 2://update course record
								 String ucoursecode="",ucoursename="",upoints="",ulevel="";
								 //Take inputs from user for input into student stable
								 System.out.print("Enter Course Code:");
								 Scanner uCoursecode=new Scanner(System.in);
								 while(true) {
									 ucoursecode=uCoursecode.nextLine();
									 if(dbQueryFunctions.isRecordExist(courseCodePrepareStatement,ucoursecode)) {
											break;
										}else {
											System.out.println("The Course Code you entered does not exists...Please check and retry!");
										}
									 
									 }
								 								
								 System.out.print("Enter New Course Name:");
								 Scanner uCourseName=new Scanner(System.in);
								 ucoursename=uCourseName.nextLine();
								 
								 System.out.print("Enter New Points:");
								 Scanner uPoints=new Scanner(System.in);
								 while(true) {
									 try{
										 int pts=Integer.parseInt(uPoints.next());
										 upoints=Integer.toString(pts);
										 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin");
									 }
								 }
								 System.out.print("Offered To [Level of Study]:");
								 
								 Scanner uLevel=new Scanner(System.in);
								 while(true) {
									 try{
										 int lvl=Integer.parseInt(uLevel.next());
										 ulevel=Integer.toString(lvl);
										 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin");
									 }
								 }
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
					
				case 3: //Case 3 handles GRADE record operations
					String gInput="4";
					d:
					do {
						
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
								 while(true) {
									 gstudentid=gStudentId.next();
								 if(dbQueryFunctions.isRecordExist(sidPrepareStatement,gstudentid)) {
										break;
									}else {
										System.out.println("The student ID you entered does not exists...Please check and retry!");
									}
								 
								 }
								 
								 System.out.print("Enter Course Code:");
								 Scanner gCourseCode=new Scanner(System.in);
								
								 while(true) {
									 gcoursecode=gCourseCode.next();
									 if(dbQueryFunctions.isRecordExist(courseCodePrepareStatement,gcoursecode)) {
											break;
										}else {
											System.out.println("The Course Code you entered does not exists...Please check and retry!");
										}
									 
									 }
								
								 System.out.print("Enter Academic Year:");
								 Scanner gYear=new Scanner(System.in);
								 while(true) {
									 try{
										 int gyr=Integer.parseInt(gYear.next());
										  gyear=Integer.toString(gyr);
											 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin: ");
									 }
								 }
								 
								 
								 System.out.print("Semester:");
								 Scanner gSemester=new Scanner(System.in);
								 while(true) {
									 try{
										 int gs=Integer.parseInt( gSemester.next());
										 gsemester=Integer.toString(gs);
											 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin: ");
									 }
								 }
								 /**
								  * We need to check whether the course code we are inserting is offered in the appropriate semester.
								  * That is, there is a high chance of semester 2 course code tagged to semester 1 for a Grade scored
								  * by a student so, we need to be accurate here.
								  * */
								
								 /*String csString="SELECT COUNT(*) FROM SemesterCourse WHERE Semester=? AND CourseCode=?";
								 
								 while(true) {
									 if(dbQueryFunctions.isRecordExist(csString,gsemester,gcoursecode)) {
											break;
										}else {
											System.out.println("The Course Code you entered is not offered in semester "+gsemester+"...Please check and retry!");
										continue d;
										}
								 }*/
								 
								 System.out.print("Grade Scored:");
								 Scanner gGrade=new Scanner(System.in);
								 while(true) {
									 ggrade=gGrade.next();
									 if(isGradeValid(ggrade)) {
									 break;
									 }else {
										 System.out.println("Please enter one of the following Grades:[A,B,C,D,E or F]"); 
									 }
								 }
								 
								 if(gstudentid.equals("") ||gcoursecode.equals("") || gyear.equals("") || gsemester.equals("") || ggrade.equals("") ) {
									 System.out.println("All input details are required.Please try again...");
									 break;
								 }else {
									 
									 Grade graderecord=new Grade(gstudentid,gcoursecode,Integer.parseInt(gyear),Integer.parseInt(gsemester),ggrade);
									 graderecord.Actions(1);
									 graderecord.Actions(0);
								 }
								 break;
							 case 2://update grade record
								 String ugstudentid="";
								 //Take inputs from user for input into student stable
								 System.out.print("Enter Student ID:");
								 Scanner ugStudentId=new Scanner(System.in);
								 while(true) {
									 ugstudentid=ugStudentId.next();
									 if(dbQueryFunctions.isRecordExist(sidPrepareStatement,ugstudentid)) {
											break;
										}else {
											System.out.println("The Student ID you entered does not exists...Please check and retry!");
										}
									 
									 }
								
								 System.out.print("Enter Course Code:");
								 Scanner ugCourseCode=new Scanner(System.in);
								 while(true) {
									 gcoursecode=ugCourseCode.nextLine();
									 if(dbQueryFunctions.isRecordExist(courseCodePrepareStatement,gcoursecode)) {
											break;
										}else {
											System.out.println("The Course Code you entered does not exists...Please check and retry!");
										}
									 
									 }
								
								 
								 System.out.print("Enter Academic Year:");
								 Scanner ugYear=new Scanner(System.in);
							
								 while(true) {
									 try{
										 int y=Integer.parseInt(ugYear.next());
										 gyear=Integer.toString(y);
											 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin: ");
									 }
								 }
								 
								 System.out.print("Semester:");
								 Scanner ugSemester=new Scanner(System.in);
								 while(true) {
									 try{
										 int y=Integer.parseInt(ugSemester.next());
										 gsemester=Integer.toString(y);
											 break;
									 }catch(Exception e) {
										 System.out.print("Please try agin: ");
									 }
								 }
								 //gsemester=ugSemester.next();
								 
								 System.out.print("Grade Scored:");
								 Scanner ugGrade=new Scanner(System.in);
								 ggrade=ugGrade.next();
								 
								 if(ugstudentid.equals("") ||gcoursecode.equals("") || gyear.equals("") || gsemester.equals("") || ggrade.equals("") ) {
									 System.out.println("All input details are required.Please try again...");
									 break;
								 }else {
									 Grade graderecord=new Grade(ugstudentid,gcoursecode,Integer.parseInt(gyear),Integer.parseInt(gsemester),ggrade);
									 graderecord.Actions(2);
									 graderecord.Actions(0);
								 }
								 break;
							 case 3://Delete student record
									 String dgstudentId="",dgcoursecode="";
									 
									 System.out.print("Enter Student ID:");
									 Scanner dgStudentId=new Scanner(System.in);
									 while(true) {
										 dgstudentId=dgStudentId.nextLine();
									 if(dbQueryFunctions.isRecordExist(sidPrepareStatement,dgstudentId)) {
											break;
										}else {
											System.out.println("The student ID you entered does not exists...Please check and retry!");
										}
									 
									 }
									 
									 System.out.print("Enter Course Code [for DELETE]:");
									 Scanner dgCourseCode=new Scanner(System.in);
									 
									 while(true) {
										 dgcoursecode=dgCourseCode.nextLine();
										 if(dbQueryFunctions.isRecordExist(courseCodePrepareStatement,dgcoursecode)) {
												break;
											}else {
												System.out.println("The Course Code you entered does not exists...Please check and retry!");
											}
										 
										 }
									
									 
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
					
				case 4: //Get Grades for a specific student
					String getGradesOption="4";
					do {
						//getSpecificStudentGrades
						 String dStdid="";
						 System.out.print("Enter Student ID:");
						 Scanner dStdId=new Scanner(System.in);
						 while(true) {
							 dStdid=dStdId.nextLine();
							 
							 if(dbQueryFunctions.isRecordExist(sidPrepareStatement,dStdid)) {
									break;
								}else {
									System.out.println("The student ID you entered does not exists...Please check and retry!");
								}
							 
							 }
						 if(dStdid.equals("")) {
							 System.out.println("All input details are required.Please try again...");
							 break;
						 }else {
							 dbQueryFunctions.getSpecificStudentGrades(dStdid);
							 ActionPrompts();
							 continue a;
						 }
					}while(!getGradesOption.equals("6"));
					
						break;
						
				case 5:
					String pInput="4";
					x:
					do {
					ActionPromptProgram();
					 
					 Scanner sProgram=new Scanner(System.in);
					 gInput = sProgram.nextLine();
					 
					 if(!isDigit(pInput)){
						 System.out.println("PLEASE ENTER ONE OF THE FOLLOWING OPTIONS ONLY [0,1,2,3,4]");
						 continue x;
					  }
					 switch(Integer.parseInt(pInput)) {
						 case 1:
							 
							 break;
						 case 2:
							 break;
						 case 3:
							 break;
						 case 4:
							 break;
					 }
					}
					 while(!pInput.equals("4"));
					break;
				case 6:
					System.out.println("THANK YOU FOR USING MyGrade App...BYE BYE!");
					System.out.println("");
					System.exit(0);

				}
			}
			
		}while(input.toString()!="6");

	}

}
