package uk.org.kelsohighschool.ps.sunnyDaleSchool.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.org.kelsohighschool.ps.sunnyDaleSchool.db.*;

/**
 * Servlet implementation class FacultiesServlet
 */
@WebServlet(description = "Sunny Dale School Faculties", urlPatterns = { "/" })
public class FacultiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacultiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {  
			DatabaseConnection dc = new DatabaseConnection("jdbc:postgresql://localhost:5432/", "postgres", "peter"); 
			ArrayList<Faculty> faculties = dc.getFaculties();  
			request.setAttribute("faculties", faculties);
			dc.close();
		} 
		
		catch (Exception e) { 
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		getServletContext().getRequestDispatcher("/jsp/faculties. jsp").forward(request,response);
	}

}
