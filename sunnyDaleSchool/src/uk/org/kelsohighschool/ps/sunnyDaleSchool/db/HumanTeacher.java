package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

public class HumanTeacher extends GenericTeacher {

	public HumanTeacher(String name, int uid, MoralState ms) throws TeacherException {
		super(name, uid, ms);
	}

	public String getSpeciesName() {
		// TODO Auto-generated method stub
		return "Human";
	}

}
