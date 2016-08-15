/**
 * 
 */
package uk.org.kelsohighschool.ps.sunnyDaleSchool.unitTests;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.DatabaseConnection;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.FacultyException;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.HumanTeacher;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.MoralState;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Teacher;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.TeacherException;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.VampireTeacher;

/**
 * @author Peter
 *
 */
public class DatabaseConnectionTest {
	
	@BeforeClass 
	public static void testingOn() {  
		DatabaseConnection.enableTesting(); 
	}
	
	private String url = "jdbc:postgresql://localhost:5432/";  
	private String userName = "postgres";  
	private String password = "peter";
	
	/**
	 * Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.DatabaseConnection#DatabaseConnection(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDatabaseConnection() throws SQLException {
		DatabaseConnection dc = new DatabaseConnection(url,userName,password);
		dc.close();
	}

	/**
	 * Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.DatabaseConnection#GetVersion()}.
	 */
	@Test
	public void testGetVersion() throws SQLException {
		DatabaseConnection dc = new DatabaseConnection(url,userName,password);
		assertEquals("9.5.3", dc.GetVersion());
		dc.close();
	}

	/**
	 * Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.DatabaseConnection#close()}.
	 */
	@Test(expected=SQLException.class)
	public void testClose() throws SQLException {
		DatabaseConnection dc = new DatabaseConnection(url,userName,password);
		dc.close();
		dc.GetVersion();
	}
	
	@Test public void testGetFaculties() throws SQLException, FacultyException { 
		DatabaseConnection dc = new DatabaseConnection(url, userName, password); 
		ArrayList<Faculty> faculties = dc.getFaculties();
		dc.close();
		assertEquals(4, faculties.size()); 
		assertEquals("Vampire Slaying", faculties.get(0).getName()); 
	}
	
	@Test public void testGetTeacher() throws SQLException, TeacherException { 
		DatabaseConnection dc = new DatabaseConnection(url, userName, password);
		Teacher teacher = dc.getTeacher(1);  
		Teacher teacher2 = dc.getTeacher(2); 
		Teacher teacher8 = dc.getTeacher(8); 
		dc.close(); 
		assertEquals("Miss Tara Maclay", teacher.getName());
		assertEquals(HumanTeacher.class, teacher.getClass());
		assertEquals(VampireTeacher.class, teacher2.getClass()); 
		assertEquals(SlayerTeacher.class, teacher8.getClass()); 
	} 
	
	@Test public void testGetTeacherFail() throws SQLException, TeacherException {
		DatabaseConnection dc = new DatabaseConnection(url, userName, password);
		Teacher teacher = dc.getTeacher(-1);
		dc.close(); 
		assertNull(teacher);
	}
	
	@Test public void testGetFaculty() throws SQLException, FacultyException, TeacherException { 
		DatabaseConnection dc = new DatabaseConnection(url, userName, password);
		Faculty faculty = dc.getFaculty(1);
		dc.close();
		assertEquals("Vampire Slaying", faculty.getName());
		assertEquals(3, faculty.getTeachers().size());
		assertEquals("Mr Angel", faculty.getTeachers().get(0).getName());
	} 
	
	
	@Test public void testGetFacultyFail() throws SQLException, FacultyException, TeacherException { 
		DatabaseConnection dc = new DatabaseConnection(url, userName, password);
		Faculty faculty = dc.getFaculty(-1);
		dc.close();
		assertNull(faculty); 
	}
	
	
	@Test public void testAddTeacher() throws SQLException, TeacherException, FacultyException { 
		DatabaseConnection dc = new DatabaseConnection(url, userName, password); 
		SlayerTeacher teacher = new SlayerTeacher("Vlad", 0, MoralState.GOOD, 10); 
		dc.addTeacher(teacher, 1);
		HumanTeacher teacher2 = new HumanTeacher("Vlad", 0, MoralState.GOOD); 
		dc.addTeacher(teacher2, 1); 
		VampireTeacher teacher3 = new VampireTeacher("Vlad", 0, MoralState.EVIL);
		dc.addTeacher(teacher3, 1); 
		Faculty faculty = dc.getFaculty(1); 
		dc.close(); 
		assertEquals(6, faculty.getTeachers().size());
	} 
	
	@Test(expected=SQLException.class) public void testAddTeacherFail() throws SQLException, TeacherException, FacultyException { 
		DatabaseConnection dc = new DatabaseConnection(url, userName, password); 
		SlayerTeacher teacher = new SlayerTeacher("Vlad", 0, MoralState.GOOD, 10); 
		dc.addTeacher(teacher, -1);
		dc.close(); 
	}

}
