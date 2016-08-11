/**
 * 
 */
package uk.org.kelsohighschool.ps.sunnyDaleSchool.unitTests;

import static org.junit.Assert.*;

import java.sql.*;

import org.junit.Test;

import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.DatabaseConnection;

/**
 * @author Peter
 *
 */
public class DatabaseConnectionTest {

	
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

}
