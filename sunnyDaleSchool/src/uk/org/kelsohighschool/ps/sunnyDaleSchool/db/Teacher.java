package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

public interface Teacher {
	public String getName();
	public void setName(String name) throws TeacherException;
	public String getSpeciesName();
	public void setMoralState(MoralState ms);
	public MoralState getMoralState();
	public String getMoralStateDescription();
	public void setUid(int uid);
	public int getUid();

}
