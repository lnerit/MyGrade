create Database MyGrades;
use MyGrades;

CREATE TABLE Program(
	ProgramCode varchar(8) PRIMARY KEY,
	ProgramName varchar(50),
	DepartmentName varchar(50)
)
Create Table Student(
	StudentId varchar(10) PRIMARY KEY,
	FirstName varchar(50),
	LastName varchar(50),
	Major varchar(8) FOREIGN KEY REFERENCES Program(ProgramCode),
)

create table Courses(
	CourseCode varchar(10),
	CourseName varchar(50),
	Points int,
	OfferedToLevelOfStudy int, /*We need to know which course is offered to which level*/
	CONSTRAINT PK_Course PRIMARY KEY (CourseCode),
	CONSTRAINT chkPoints CHECK (Points>0 AND Points <120)
);

/*
We need to know which semester a course is offered
*/
CREATE TABLE SemesterCourse(
	CourseCode varchar(10) FOREIGN KEY REFERENCES Courses(CourseCode) ON DELETE CASCADE ON UPDATE CASCADE,
	Semester int,
	PRIMARY KEY(CourseCode,Semester),
	
);

/*
	A student can have at most one grade per semester for course that he/she studied in a  year
*/
CREATE TABLE Grade(
	StudentId varchar(10),
	CourseCode varchar(10),
	YearOfStudy int,
	Semester int,
	Grade char(2),
	PRIMARY KEY(StudentId,CourseCode,YearOfStudy,Semester),
	CONSTRAINT FK_Course FOREIGN KEY(CourseCode) REFERENCES Courses(CourseCode) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FK_Student FOREIGN KEY(StudentId) REFERENCES Student(StudentId) ON UPDATE CASCADE ON DELETE CASCADE	
)
