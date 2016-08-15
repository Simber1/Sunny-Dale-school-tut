package uk.org.kelsohighschool.ps.sunnyDaleSchool.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.DatabaseConnection;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.SlayerTeacher;
import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.Teacher;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet(description = "Sunny Dale School Teacher Details", urlPatterns = { "/teacher" })
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private String url = "jdbc:postgresql://localhost:5432/";  
	private String userName = "postgres";  
	private String password = "peter";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int uid = Integer.parseInt(request.getParameter("uid"));
			DatabaseConnection dc = new DatabaseConnection(url, userName, password); 
			Teacher teacher = dc.getTeacher(uid);
			request.setAttribute("teacher", teacher);
			dc.close();
			boolean isSlayer = teacher instanceof SlayerTeacher;
			request.setAttribute("isSlayer", isSlayer);
			getServletContext().getRequestDispatcher("/jsp/teacher.jsp").forward(request, response); 
			request.setAttribute("uid", uid);
			//response.getWriter().append("Served at: ").append(request.getContextPath());
		} 
		catch (Exception e) {
			e.printStackTrace(); 
			getServletContext().getRequestDispatcher("/jsp/error.jsp" ).forward(request, response);
		}
	}

}
