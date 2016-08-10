package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

public class SlayerTeacher extends HumanTeacher {
	
	int stakings;

	public SlayerTeacher(String name, int uid, MoralState ms, int stakings) throws TeacherException {
		super(name, uid, ms);
		setStakings(stakings);
	}

	public int getStakings() {
		return stakings;
	}

	public void setStakings(int stakings) {
		this.stakings = stakings;
	}
	

}
