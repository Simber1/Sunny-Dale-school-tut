package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

public class VampireTeacher extends GenericTeacher {

	public VampireTeacher(String name, int uid, MoralState ms) throws TeacherException {
		super(name, uid, ms);
	}

	@Override
	public String getSpeciesName() {
		return "Vampire";
	}

}
