/**
 * 
 */
package uk.org.kelsohighschool.ps.sunnyDaleSchool.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.FacultyException;

/**
 * @author Peter
 *
 */
public class FacultyTest {

	/**
	 * Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty#Faculty(java.lang.String, int)}.
	 * This is the normal test
	 * @throws FacultyException
	 */
	@Test
	public void constructorNormal() throws FacultyException {
		Faculty faculty = new Faculty("Vlad",1);
	}
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty#Faculty(java.lang.String, int)}.
	 * This is the extreme short name test
	 * @throws FacultyException
	 */
	@Test 
	public void constructorExtreme1() throws FacultyException {
		Faculty faculty = new Faculty("V",1);
	}
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty#Faculty(java.lang.String, int)}.
	 * This is the extreme long name test
	 * @throws FacultyException
	 */
	@Test 
	public void constructorExtreme2() throws FacultyException {
		Faculty faculty = new Faculty("nmvbhgnjmghvbcfjnmkyhjbgknuvmy32",1);
	}
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty#Faculty(java.lang.String, int)}.
	 * This is the exceptional empty name test
	 * @throws FacultyException
	 */
	@Test 
	public void constructorExceptional1() throws FacultyException {
		Faculty faculty = new Faculty("",1);
	}
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty#Faculty(java.lang.String, int)}.
	 * This is the exceptional long name test
	 * @throws FacultyException
	 */
	@Test 
	public void constructorExceptional2() throws FacultyException {
		Faculty faculty = new Faculty("nmvbhgnjmghvbcfjnmkyhjbgknuvmyi33",1);
	}
}
