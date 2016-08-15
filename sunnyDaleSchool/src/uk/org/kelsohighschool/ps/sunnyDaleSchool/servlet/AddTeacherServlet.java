package uk.org.kelsohighschool.ps.sunnyDaleSchool.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTeacherServlet
 */
@WebServlet(description = "Add Sunny Dale School Teacher", urlPatterns = { "/addTeacher" })
public class AddTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/jsp/addTeacher.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			String name = request.getParameter("name"); 
			request.setAttribute("name", name); 
			getServletContext().getRequestDispatcher( "/jsp/addTeacherResults.jsp").forward(request, response); 
			}
		catch (Exception e) { 
			e.printStackTrace(); 
			getServletContext().getRequestDispatcher( "/jsp/error.jsp").forward(request, response);  
		}
	}

}
