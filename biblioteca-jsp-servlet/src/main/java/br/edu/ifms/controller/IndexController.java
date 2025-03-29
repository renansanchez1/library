package br.edu.ifms.controller;

import java.io.IOException;


import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifms.controller.util.DataManipulation;
import br.edu.ifms.dao.util.Conexao;
import br.edu.ifms.model.User;

import java.util.Date;



@WebServlet("/public")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public IndexController() {
        super();
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
			throws ServletException, IOException {
		String name= request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		DataManipulation dateManipulation = new DataManipulation();
		Date dateBirth = dateManipulation.convertStringData(birth);
		
		User user = new User(name, cpf, dateBirth, email, login, password, null);
		
		System.out.println(user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("public/public-new-user.jsp");
		dispatcher.forward(request, response);
	}

}
