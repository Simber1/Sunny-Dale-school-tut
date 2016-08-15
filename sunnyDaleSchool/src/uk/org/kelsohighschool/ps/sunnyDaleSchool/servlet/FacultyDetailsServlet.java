package uk.org.kelsohighschool.ps.sunnyDaleSchool.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.DatabaseConnection;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Faculty;

/**
 * Servlet implementation class FacultyDetailsServlet
 */
@WebServlet(description = "Sunny Dale School Faculty Details", urlPatterns = { "/faculty/*" })
public class FacultyDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String url = "jdbc:postgresql://localhost:5432/";  
	private String userName = "postgres";  
	private String password = "peter";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultyDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String thisPage = request.getRequestURI();
		 String urlParts[] = thisPage.split("/");
		 String lastPart = urlParts[urlParts.length - 1];
		 int facultyId = Integer.parseInt(lastPart);
		 request.setAttribute("facultyId", facultyId);
		 
		 try { 
			 DatabaseConnection dc = new DatabaseConnection(url,userName,password);
			 Faculty faculty = dc.getFaculty(facultyId); 
			 request.setAttribute("facultyName", faculty.getName()); 
			 request.setAttribute("teachers", faculty.getTeachers());
			 dc.close();
		 } 
		 
		 catch (Exception e) {
			 e.printStackTrace();
			 throw new ServletException(e); 
		 } 
		 
		getServletContext().getRequestDispatcher("/jsp/facultyDetails.jsp").forward(request,response);
	}

}
