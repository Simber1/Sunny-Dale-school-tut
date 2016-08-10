package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

public abstract class GenericTeacher implements Teacher {
	
	private MoralState ms;
	private String name;
	private int uid;
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) throws TeacherException {
		if (name.length() == 0) throw new TeacherException("Teacher name must not be an empty string");
		if (name.length() > 32) throw new TeacherException("Teacher name must be less than 32 chars long");
		this.name = name;

	}

	
	public void setMoralState(MoralState ms) {
		this.ms = ms;
	}

	
	public MoralState getMoralState() {
		// TODO Auto-generated method stub
		return ms;
	}

	
	public String getMoralStateDescription() {
		String rtnVal;
		switch (this.ms) {
			case GOOD:
				rtnVal = "Righter of Wrongs";
				break;
			case EVIL:
				rtnVal = "Doer of Evil";
				break;
			default:
				rtnVal = "Depends on Who You Are";
				break;
			
		}
		return rtnVal;
	}

	
	public void setUid(int uid) {
		this.uid = uid;

	}

	
	public int getUid() {
		// TODO Auto-generated method stub
		return 0;
	}

}
