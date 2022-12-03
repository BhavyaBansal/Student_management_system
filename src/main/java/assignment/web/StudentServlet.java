
package assignment.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import assignment.dao.StudentDAO;
import assignment.model.Student;

@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studao;
	
    public StudentServlet() {
        this.studao =new StudentDAO();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getServletPath();
		switch(action) {
		case"/new":
			shownewform(request, response);
			break;
			
		case"/insert":
			try {
				insertstudent(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/delete":
			try {
				deletestudent(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/edit":
			try {
				showEditform(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case"/update":
			try {
				updatestudent(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				liststudent(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	//list method (it will be shown by default).
	private void liststudent(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException,SQLException{
		List<Student> liststudent=studao.selectallstudent();
		request.setAttribute("liststudent", liststudent);
		RequestDispatcher rd=request.getRequestDispatcher("student-list.jsp");
		rd.forward(request, response);
		
	}
	//method for new form
	private void shownewform(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		RequestDispatcher rd=request.getRequestDispatcher("student-form.jsp");
		rd.forward(request, response);
		
	}
	//method for insert students
	private void insertstudent(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException, SQLException{
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		Student newstu=new Student(firstname,lastname,email);
		studao.insertstudent(newstu);
		response.sendRedirect("list");
	}
	//method for delete student
	private void deletestudent(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException, SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		studao.deletestudent(id, null);
		response.sendRedirect("list");
	}
	//method for update student form
	private void showEditform(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException,SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		Student existingstudent=studao.selectstudent(id);
		RequestDispatcher rd=request.getRequestDispatcher("student-form.jsp");
		request.setAttribute("student", existingstudent);
		rd.forward(request, response);
		
	}
	//method for updating student
	private void updatestudent(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException, SQLException{
		int id=Integer.parseInt(request.getParameter("id"));
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		Student enroll=new Student(id,firstname,lastname,email);
		studao.updatestudent(enroll);
		response.sendRedirect("list");
	}

}

