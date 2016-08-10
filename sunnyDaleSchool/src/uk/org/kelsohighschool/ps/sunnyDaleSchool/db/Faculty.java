package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

import java.util.ArrayList;

public class Faculty {
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the faculty
	 * @param name
	 * @throws FacultyException if the length of name is zero or greater than 32
	 */
	public void setName(String name)  throws FacultyException {
		if (name.length() == 0) throw new FacultyException("Faculty name must not be an empty string");
		if (name.length() > 32) throw new FacultyException("Faculty name must be less than 32 chars long");
		this.name = name;
	}
	
	/**
	 * 
	 * @return uid 
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * 
	 * @param uid
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	//Constuctor
	public Faculty(String name, int uid) throws FacultyException {
		setName(name);
		setUid(uid);
		teachers = new ArrayList<Teacher>();
	}
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}
	private String name;
	private int uid;
	private ArrayList<Teacher>teachers;

}
