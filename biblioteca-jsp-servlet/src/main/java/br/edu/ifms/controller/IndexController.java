package br.edu.ifms.controller;

import java.io.IOException;


import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.controller.util.DataManipulation;
import br.edu.ifms.dao.util.Conexao;
import br.edu.ifms.model.User;
import br.edu.ifms.dao.UserDAO;


import java.util.Date;



@WebServlet("/public")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDAO userDAO;

    public IndexController() {
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
			case "new":
				newUser(request, response);
				break;
			case "insert":
				saveUser(request, response);
				break;
				
			}
		}catch(Exception ex) {
			throw new ServletException(ex);
		}
		
	}
	
	private void newUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("public/public-new-user.jsp");
		dispatcher.forward(request, response);
	}
	
	private void saveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String name= request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String login = request.getParameter("login");
		String birth = request.getParameter("birth");
		
		DataManipulation dateManipulation = new DataManipulation();
		Date dateBirth = dateManipulation.convertStringData(birth);
		
		User user = new User(name, cpf, dateBirth, email, password, login, null);
		
		User userSave = userDAO.insertUser(user); //catch the user saved
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("public/public-new-user.jsp");
		request.setAttribute("message", "User registered successfully");
		dispatcher.forward(request, response);

			
	}
	
//	private void editUser(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("public/public-new-user.jsp");
//		dispatcher.forward(request, response);
//	}
	

}
