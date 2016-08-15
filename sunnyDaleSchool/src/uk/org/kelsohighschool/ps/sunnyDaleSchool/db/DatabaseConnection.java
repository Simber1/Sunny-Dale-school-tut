package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

import java.sql.*;
import java.util.ArrayList;
import org.postgresql.Driver;


public class DatabaseConnection {
	
	private static boolean testingMode = false; 
	
	public static void enableTesting() {
		testingMode = true;
	}
	
	private Connection conn;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement; 
	
	public DatabaseConnection(String url, String username, String password) throws SQLException {
		try {  
			DriverManager.getDriver(url);
		} 
		catch (SQLException e) {
			DriverManager.registerDriver(new Driver()); 
		} 
		conn = DriverManager.getConnection(url, username, password);
		if (testingMode) {
			conn.setAutoCommit(false);  
		}
	}
	
	public ArrayList<Faculty> getFaculties() throws SQLException, FacultyException { 
		statement = conn.createStatement();
		resultSet = statement.executeQuery("select * from faculties");
		ArrayList<Faculty> rtnVal = new ArrayList<Faculty>();
		while (resultSet.next()) {
			Faculty faculty = new Faculty(resultSet.getString(2), resultSet.getInt(1));
			rtnVal.add(faculty);
		} 
		return rtnVal;
	}
	
	public Teacher getTeacher(int teacherId) throws SQLException, TeacherException {  
		preparedStatement = conn.prepareStatement("select name,species,moralstate,stakings from teachers where uid=?");
		preparedStatement.setInt(1, teacherId);  
		resultSet = preparedStatement.executeQuery();  
		Teacher rtnVal = null; 
		if (resultSet.next()) {
			String name = resultSet.getString(1);
			Species species = Species.valueOf(resultSet.getString(2));
			MoralState ms = MoralState.valueOf(resultSet.getString(3));
			int stakings = resultSet.getInt(4);
			switch (species) {  
				case SLAYER:   rtnVal = new SlayerTeacher(name, teacherId, ms, stakings);
					break;
				case VAMPIRE:   rtnVal = new VampireTeacher(name, teacherId, ms);
					break;
				default:   rtnVal = new HumanTeacher(name, teacherId, ms);  
					break; 
			} 
		} 
		return rtnVal; 
	}
	
	private void addTeachersToFaculty(Faculty faculty) throws SQLException, TeacherException {
		preparedStatement = conn.prepareStatement("select * from teachers where faculty=?");
		preparedStatement.setInt(1, faculty.getUid()); 
		resultSet = preparedStatement.executeQuery();  
		while (resultSet.next()) {   
			int uid = resultSet.getInt(1);
			String name = resultSet.getString(2);  
			Species species = Species.valueOf(resultSet.getString(3)); 
			MoralState ms = MoralState.valueOf(resultSet.getString(4));
			int stakings = resultSet.getInt(5);  
			switch (species) { 
				case VAMPIRE:   
					faculty.addTeacher(new VampireTeacher(name, uid, ms));
					break;   
				case SLAYER:    
					faculty.addTeacher(new SlayerTeacher(name, uid, ms, stakings));
					break;  
				case HUMAN:   
					faculty.addTeacher(new HumanTeacher(name, uid, ms)); 
					break;  
			}  
		} 
	}
	
	public Faculty getFaculty(int facultyId) throws SQLException, FacultyException, TeacherException { 
		preparedStatement = conn.prepareStatement("select name from faculties where uid=?"); 
		preparedStatement.setInt(1, facultyId);
		resultSet = preparedStatement.executeQuery();
		Faculty rtnVal = null;
		if (resultSet.next()) {
			rtnVal = new Faculty(resultSet.getString(1), facultyId);
			addTeachersToFaculty(rtnVal);
		} 
		return rtnVal;
	} 
	
	private PreparedStatement slayerStatement(SlayerTeacher slayer, int facultyUid) throws SQLException {
		PreparedStatement rtnVal = conn.prepareStatement("insert into teachers (name,species,stakings,faculty) values (?,?::species_type,?,?)", Statement.RETURN_GENERATED_KEYS);
		rtnVal.setString(1, slayer.getName()); 
		rtnVal.setString(2,"SLAYER");
		rtnVal.setInt(3,slayer.getStakings()); 
		rtnVal.setInt(4, facultyUid);  
		return rtnVal;
	}
	
	private PreparedStatement otherStatement(Teacher teacher, int facultyUid) throws SQLException { 
		PreparedStatement rtnVal = conn.prepareStatement("insert into teachers (name,species,moralstate,faculty) values (?,?::species_type,?::moralstate_type,?)", Statement.RETURN_GENERATED_KEYS);
		rtnVal.setString(1, teacher.getName());
		if (teacher instanceof HumanTeacher) {
			rtnVal.setString(2, "HUMAN");
		} else {
			rtnVal.setString(2, "VAMPIRE");
		}
		rtnVal.setString(3, teacher.getMoralState().toString());
		rtnVal.setInt(4, facultyUid);  return rtnVal;
	}	
	
	
	public void addTeacher(Teacher teacher, int facultyUid) throws SQLException {
		if (teacher instanceof SlayerTeacher) { 
			SlayerTeacher slayer = (SlayerTeacher) teacher;
			preparedStatement = slayerStatement(slayer, facultyUid);
		} else {
			preparedStatement = otherStatement(teacher, facultyUid);
		} 
		preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		if (resultSet.next()) {
			teacher.setUid(resultSet.getInt(1));
		} 
	}
	
	
	public String GetVersion() throws SQLException { 
		statement = conn.createStatement();  
		resultSet = statement.executeQuery("show server_version");  
		String rtnVal = "";  
		if (resultSet.next()) {   
			rtnVal = resultSet.getString(1);  
		}  
		return rtnVal;
	}
	
	public void close() { 
		try {
			if (resultSet != null) {
				resultSet.close();
			}   
			if (statement != null) {
				statement.close();  
			}   
			if (conn != null) {
				conn.close();
			} 
			if ( preparedStatement != null ) { 
				preparedStatement.close(); 
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
