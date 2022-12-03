package assignment.dao;

//This DAO class provides a CRUD database operations for the table students in the database.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import assignment.model.Student;

public class StudentDAO {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/studentmanagement?useUnicode=true&useJDBCComplaintTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername="root";
	private String jdbcPassword="";
	//defining all the queries that has to be used. 
	private static final String INSERT_STUDENTS_SQL="Insert into student"
			+ "(firstname,lastname,email)values" + "(?,?,?);"; 
	private static final String SELECT_STUDENT_BY_ID="select id,firstname"
			+ ",lastname,email from student where id =?;";
	private static final String SELECT_ALL_STUDENTS="select * from student;";
	private static final String DELETE_STUDENTS_SQL="delete from student"
			+ " where id=?;";
	private static final String UPDATE_STUDENT_SQL="update student set"
			+ " firstname=?,lastname=?,email=? where id=?;";
	//doing connection with database
	protected Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			System.out.println("Connection Established");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//create or insert student
	public void insertstudent(Student student)throws SQLException{
		try(Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(INSERT_STUDENTS_SQL);){
			ps.setString(1, student.getFirstname());
			ps.setString(2, student.getLastname());
			ps.setString(3, student.getEmail());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//update student
	public boolean updatestudent(Student student)throws SQLException{
		boolean rowupdated;
		try(Connection conn=getConnection();
				PreparedStatement ps=conn.prepareStatement(UPDATE_STUDENT_SQL);){
			ps.setString(1, student.getFirstname());
			ps.setString(2, student.getLastname());
			ps.setString(3, student.getEmail());
			ps.setInt(4, student.getId());
			rowupdated=ps.executeUpdate() > 0;
		}
		return rowupdated;
	}
	
	//select student by id
	public Student selectstudent(int id)throws SQLException{
		Student student=null;
		try(Connection conn=getConnection();
			PreparedStatement ps=conn.prepareStatement(SELECT_STUDENT_BY_ID);){
			ps.setInt(1, id);
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
			String firstname=rs.getString("firstname");
			String lastname=rs.getString("lastname");
			String email=rs.getString("email");
			student =new Student(id,firstname,lastname,email); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	//select all student
	public List<Student> selectallstudent()throws SQLException{
		List<Student> students=new ArrayList<>();
		try(Connection conn=getConnection();
				PreparedStatement ps=conn.prepareStatement(SELECT_ALL_STUDENTS);){
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
			int id=rs.getInt("id");
			String firstname=rs.getString("firstname");
			String lastname=rs.getString("lastname");
			String email=rs.getString("email");
			students.add(new Student(id,firstname,lastname,email)); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return students;
	}
	
	//delete student
	public boolean deletestudent(int id,java.awt.event.ActionEvent e)throws SQLException{
		int opt=JOptionPane.showConfirmDialog(null, "Are you sure u want to delete this field?", "Delete", JOptionPane.YES_NO_OPTION);
		if(opt==0) {
		boolean rowdeleted;
		try(Connection conn=getConnection();
				PreparedStatement ps=conn.prepareStatement(DELETE_STUDENTS_SQL);){
			ps.setInt(1, id);
			rowdeleted=ps.executeUpdate()>0;
		}
		return rowdeleted;
		}
		return false;
	}

}
