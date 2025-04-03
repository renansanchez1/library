package br.edu.ifms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.dao.UserDAO;
import br.edu.ifms.model.User;


@WebServlet("/auth/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;

    public AdminController() {
        super();
    }
    
    public void init() {
    	userDAO = new UserDAO();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");	
		
		try {
			switch(action) {
			case "list":
				listUser(request, response);
				break;
				
			case "delete":
				deleteUser(request, response);
				
			case "update":
				editUser(request, response);
				
			}
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    long id = Long.parseLong(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String cpf = request.getParameter("cpf");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String login = request.getParameter("login");
	    boolean active = Boolean.parseBoolean(request.getParameter("active"));

	    // Verifica se a data foi enviada
	    String birthStr = request.getParameter("date_birth");
	    java.sql.Date birthDate = null;
	    if (birthStr != null && !birthStr.isEmpty()) {
	        try {
	            birthDate = java.sql.Date.valueOf(birthStr); // Formato esperado: "yyyy-MM-dd"
	        } catch (IllegalArgumentException e) {
	            System.out.println("Formato de data inválido: " + birthStr);
	        }
	    }

	    User user = new User(name, cpf, birthDate, email, password, login, active);
	    user.setId(id);

	    userDAO.editUser(user);

	    response.sendRedirect(request.getContextPath() + "/auth/admin?action=list");
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		User user = new User();
		user.setId(id);
		userDAO.deleteUser(user);
		
		//call the method 'listUser' passing the action = 'list' when the register is deleted'
		String path = request.getContextPath() + request.getServletPath() + "?action=list";
		response.sendRedirect(path);
		
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException{
		List<User> users = userDAO.listAllUsers();	
		
		request.setAttribute("listUsers", users); //set the array list how atribbute on request
		
		String path =  request.getServletPath() + "/admin-list-user.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		dispatcher.forward(request, response);

	}
	
	
	
	

}
