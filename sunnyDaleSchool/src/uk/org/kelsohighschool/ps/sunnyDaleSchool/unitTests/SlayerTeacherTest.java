/**
 * 
 */
package uk.org.kelsohighschool.ps.sunnyDaleSchool.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.MoralState;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.TeacherException;

/**
 * @author Peter
 *
 */
public class SlayerTeacherTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
 
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher#SlayerTeacher(java.lang.String, int, Moralstate, int)}.
	 * This is the normal name test
	 * @throws TeacherException
	 */
	
	@Test 
	public void constructorNormal() throws TeacherException { 
		 SlayerTeacher slayer = new SlayerTeacher("Vlad", 1, MoralState.GOOD, 1); 
		 assertEquals("Vlad",slayer.getName()); 
	}
	
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher#SlayerTeacher(java.lang.String, int, Moralstate, int)}.
	 * This is the extreme short name test
	 * @throws TeacherException
	 */
	
	@Test 
	public void constructorExtreme1() throws TeacherException { 
		 SlayerTeacher slayer = new SlayerTeacher("V", 1, MoralState.GOOD, 1); 
		 assertEquals("V",slayer.getName()); 
	}
	
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher#SlayerTeacher(java.lang.String, int, Moralstate, int)}.
	 * This is the extreme long name test
	 * @throws TeacherException
	 */
	
	@Test 
	public void constructorExtreme2() throws TeacherException { 
		 SlayerTeacher slayer = new SlayerTeacher("asdfrrtyuiqwertyuiopzxcvbnmghj32", 1, MoralState.GOOD, 1); 
		 assertEquals("asdfrrtyuiqwertyuiopzxcvbnmghj32",slayer.getName()); 
	}
	
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher#SlayerTeacher(java.lang.String, int, Moralstate, int)}.
	 * This is the Exceptional short name test
	 * @throws TeacherException
	 */
	
	@Test(expected=TeacherException.class)
	public void constructorExpectional1() throws TeacherException { 
		 @SuppressWarnings("unused")
		SlayerTeacher slayer = new SlayerTeacher("", 1, MoralState.GOOD, 1); 
	}
	
	/**
	 ** Test method for {@link uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher#SlayerTeacher(java.lang.String, int, Moralstate, int)}.
	 * This is the Exceptional long name test
	 * @throws TeacherException
	 */
	
	@Test (expected=TeacherException.class)
	public void constructorExpectional2() throws TeacherException { 
		 @SuppressWarnings("unused")
		SlayerTeacher slayer = new SlayerTeacher("asdfrrtyuiqwertyuiopzxcvbnmghjk33", 1, MoralState.GOOD, 1); 
	}
	
	//Testing MoralStateDescriptor
	
	@Test 
	public void testGetMoralStateDescription1() throws TeacherException {  
		SlayerTeacher slayer = new SlayerTeacher("Vlad", 1, MoralState.GOOD, 1); 
		assertEquals("Righter of Wrongs", slayer.getMoralStateDescription());
	} 
	
	@Test 
	public void testGetMoralStateDescription2() throws TeacherException { 
		SlayerTeacher slayer = new SlayerTeacher("Vlad", 1, MoralState.EVIL, 1); 
		assertEquals("Doer of Evil", slayer.getMoralStateDescription()); 
	} 
	
	@Test 
	public void testGetMoralStateDescription3() throws TeacherException { 
		SlayerTeacher slayer = new SlayerTeacher("Vlad", 1, MoralState.CONFUSED, 1);
		assertEquals("Depends On Who You Are", slayer.getMoralStateDescription());
	}

}
