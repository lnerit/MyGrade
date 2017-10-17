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
	Major varchar(8) FOREIGN KEY REFERENCES Program(ProgramCode)
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
	PRIMARY KEY(CourseCode,Semester)
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
	CONSTRAINT FK_Student FOREIGN KEY(StudentId) REFERENCES Student(StudentId) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FK_CourseSemester FOREIGN KEY(CourseCode,Semester) REFERENCES SemesterCourse(CourseCode,Semester)
)


/**
 * Stored Procedures
 */
create procedure InsertStudent(
	@StudentId varchar(15),
	@FirstName varchar(50),
	@LastName varchar(50),
	@Major varchar(10),
	@option char(1)
)
AS
BEGIN
	IF @option='I'
		BEGIN
			IF EXISTS (SELECT * FROM Student WHERE StudentId=@StudentId)
				BEGIN
					RAISERROR('The STUDENT ID YOU ENTERED ALREADY EXISTS...PLEASE TRY A DIFFERENT ID',16,1);
					RETURN
				END
			ELSE
				BEGIN
					INSERT INTO Student VALUES(@StudentId,@FirstName,@LastName,@Major)
				END
	  END
	IF @option='U'
		BEGIN
			IF NOT EXISTS (SELECT * FROM Student WHERE StudentId=@StudentId)
				BEGIN
					RAISERROR('The STUDENT ID YOU ENTERED DOES NOT EXIST...PLEASE TRY A DIFFERENT ID',16,1);
					RETURN
				END
			ELSE
				BEGIN
					UPDATE Student SET FirstName=@FirstName,LastName=@LastName,Major=Major WHERE StudentId=@StudentId
				END
	  END
END

INSERT INTO Program VALUES('BSCS','Bachelor of Science in Computer Science','MSCS')

INSERT INTO  Courses('CS204','Database I',18,2)
INSERT INTO  Courses('BA131','Introduction to Business',	3,2)
INSERT INTO  Courses('CS101','Introduction to Computer Science I',16,1)
INSERT INTO  Courses('CS102','Introduction to Computer Science II',16,1)
INSERT INTO  Courses('CS103','Introduction to ICT I',12,1)
INSERT INTO  Courses('CS104','Introduction to ICT II',12,1)
INSERT INTO  Courses('CS105','Introduction to Programming I',12,	1)
INSERT INTO  Courses('CS106','Introduction to Programming II',12,1)
INSERT INTO  Courses('CS201','Programming in Java',	12,2)
INSERT INTO  Courses('CS202','Object Oriented Programming',12,2)
INSERT INTO  Courses('CS203','Operating System I',12,2)
INSERT INTO  Courses('CS204','xx',20,1)
INSERT INTO  Courses('CS205','Networking I',12,2)
INSERT INTO  Courses('CS206','Internet Programming I',12,2)
INSERT INTO  Courses('CS207','Mathematics for Computer Science',	12,2)
INSERT INTO  Courses('CS208','Systems Analysis and Design',12,2)
INSERT INTO  Courses('CS308','Computer Hardware',11,3)
INSERT INTO  Courses('CS401','Computer Science Project (A)',	18,4)
INSERT INTO  Courses('CS402','Computer Science Project (B)',	18,4)
INSERT INTO  Courses('CS411','Special Topics 1', 18,4)
INSERT INTO  Courses('CS412','Special Topics	18',4)
INSERT INTO  Courses('ED312','Microcomputer Interfacing ',6,3)
INSERT INTO  Courses('EE343','Computer Hardware',16,3)
INSERT INTO  Courses('IS212','System Design A',12,2)
INSERT INTO  Courses('IS315','Information Systems Design Project',18,3)
INSERT INTO  Courses('IS422','Information Systems Management	3',4)
INSERT INTO  Courses('IS462','UNIX Operating System',4,4)
INSERT INTO  Courses('LA101','English Grammer and Composition',12,1)
INSERT INTO  Courses('LA102','English Grammer and Composition II',12,1)
INSERT INTO  Courses('LA204','Communication in Workplace',2,2)
INSERT INTO  Courses('LA301','Advanced Language and Communication Skills',12,3)
INSERT INTO  Courses('MA167','Engineering Mathematics I',18,1)
INSERT INTO  Courses('MA168','Engineering Mathematics II',18,1)
INSERT INTO  Courses('MA262','Database Management',13,2)
INSERT INTO  Courses('MA263','TESTING',16,2)
INSERT INTO  Courses('MA361','Systems Programming with C++',22,3)
INSERT INTO  Courses('MA362','Operating Systems',16,3)
INSERT INTO  Courses('MA364','Advanced Statistics',20,3)
INSERT INTO  Courses('MA365','Numerical Methods',18,3)
INSERT INTO  Courses('MA366','Programming Languages',16,3)
INSERT INTO  Courses('MA367','Sampling and Survey Techniques',13,3)
INSERT INTO  Courses('MA369','Introduction to COBOL',13,3)
INSERT INTO  Courses('MA462','Optimization',15,4)
INSERT INTO  Courses('MA464','FORTRAN',12,4)
INSERT INTO  Courses('MA466','Desk Top Publishing',12,4)
INSERT INTO  Courses('MA468','Object Oriented Programming',12,4)
INSERT INTO  Courses('MA469','Software Engineering',	15,	4)
INSERT INTO  Courses('MA472','Real Time Programming',18,4)
INSERT INTO  Courses('MA474','Courseware Development',12,4)
INSERT INTO  Courses('MA476','Human Computer Interface',16,4)
INSERT INTO  Courses('MA478','Flow Through Networks',18,4)
INSERT INTO  Courses('MA480','Computer Aided Design',12,4)
INSERT INTO  Courses('MA481','Unix Management',	16,4)
INSERT INTO  Courses('MA482','Windows and HyperText',12,4)
INSERT INTO  Courses('MA483','Advanced Networking and Internet Applications',16,4)
INSERT INTO  Courses('MA484','Industrial Database and Oracle',15,4)
INSERT INTO  Courses('MA485','Principles of Distributed',15,4)
INSERT INTO  Courses('MA488','Analysis of Algorithms',15,4)
INSERT INTO  Courses('MA492','Microcomputer Hardware',13,4)
INSERT INTO  Courses('MA494','Programming in Java',12,4)
INSERT INTO  Courses('PH113','Physics for Computer Science I',15,1)
INSERT INTO  Courses('PH114','Physics for Computer Science II',16,1)

INSERT INTO SemesterCourse VALUES('BA131',1)
INSERT INTO SemesterCourse VALUES('CS101',1)
INSERT INTO SemesterCourse VALUES('CS102',2)
INSERT INTO SemesterCourse VALUES('CS103',1)
INSERT INTO SemesterCourse VALUES('CS104',2)
INSERT INTO SemesterCourse VALUES('CS105',1)
INSERT INTO SemesterCourse VALUES('CS106',2)
INSERT INTO SemesterCourse VALUES('CS201',1)
INSERT INTO SemesterCourse VALUES('CS202',2)
INSERT INTO SemesterCourse VALUES('CS203',1)
INSERT INTO SemesterCourse VALUES('CS204',2)
INSERT INTO SemesterCourse VALUES('CS205',1)
INSERT INTO SemesterCourse VALUES('CS206',2)
INSERT INTO SemesterCourse VALUES('CS207',1)
INSERT INTO SemesterCourse VALUES('CS208',2)
INSERT INTO SemesterCourse VALUES('CS308',2)
INSERT INTO SemesterCourse VALUES('CS401',1)
INSERT INTO SemesterCourse VALUES('CS402',2)
INSERT INTO SemesterCourse VALUES('CS411',1)
INSERT INTO SemesterCourse VALUES('CS412',2)
INSERT INTO SemesterCourse VALUES('ED312',2)
INSERT INTO SemesterCourse VALUES('EE343',1)
INSERT INTO SemesterCourse VALUES('IS212',2)
INSERT INTO SemesterCourse VALUES('IS422',2)
INSERT INTO SemesterCourse VALUES('IS462',2)
INSERT INTO SemesterCourse VALUES('LA101',1)
INSERT INTO SemesterCourse VALUES('LA102',2)
INSERT INTO SemesterCourse VALUES('LA204',2)
INSERT INTO SemesterCourse VALUES('LA301',1)
INSERT INTO SemesterCourse VALUES('MA361',1)
INSERT INTO SemesterCourse VALUES('MA362',2)
INSERT INTO SemesterCourse VALUES('MA364',2)
INSERT INTO SemesterCourse VALUES('MA365',2)
INSERT INTO SemesterCourse VALUES('MA366',2)
INSERT INTO SemesterCourse VALUES('MA367',1)
INSERT INTO SemesterCourse VALUES('MA369',1)
INSERT INTO SemesterCourse VALUES('MA462',2)
INSERT INTO SemesterCourse VALUES('MA464',2)
INSERT INTO SemesterCourse VALUES('MA466',2)
INSERT INTO SemesterCourse VALUES('MA468',2)
INSERT INTO SemesterCourse VALUES('MA469',2)
INSERT INTO SemesterCourse VALUES('MA472',2)
INSERT INTO SemesterCourse VALUES('MA474',2)
INSERT INTO SemesterCourse VALUES('MA476',2)
INSERT INTO SemesterCourse VALUES('MA478',2)
INSERT INTO SemesterCourse VALUES('MA480',2)
INSERT INTO SemesterCourse VALUES('MA481',1)
INSERT INTO SemesterCourse VALUES('MA482',2)
INSERT INTO SemesterCourse VALUES('MA483',2)
INSERT INTO SemesterCourse VALUES('MA484',2)
INSERT INTO SemesterCourse VALUES('MA485',2)
INSERT INTO SemesterCourse VALUES('MA488',2)
INSERT INTO SemesterCourse VALUES('MA492',2)
INSERT INTO SemesterCourse VALUES('MA494',1)
INSERT INTO SemesterCourse VALUES('PH113',1)
INSERT INTO SemesterCourse VALUES('PH114',2)


