package br.edu.ifms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.dao.util.Conexao;
import br.edu.ifms.model.User;

public class UserDAO {
	
	private Connection connection;
	
	private void conect() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = Conexao.getConexao();
		}
	}

	private void desconect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public User insertUser(User user) throws SQLException {
		String sql = "INSERT INTO usuario (name, cpf, date_birth, email, password, login, active)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";		    
		
		conect();

		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, user.getName());
		statement.setString(2, user.getCpf());
		Date nascimento = new Date(user.getBirth().getTime());
		statement.setDate(3, nascimento);
		statement.setString(4, user.getEmail());
		statement.setString(5, user.getPassword());
		statement.setString(6, user.getLogin());
		statement.setBoolean(7, user.isActive());
		
		statement.executeUpdate();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		long id = 0;
		if(resultSet.next()) //has any return of register?
			id = resultSet.getInt("id");
		statement.close();

		desconect();
		
		user.setId(id);
		return user;
	}
	
	public List<User> listAllUsers() throws SQLException {
		
		List<User> listUsers = new ArrayList<User>();

		String sql = "SELECT * FROM usuario";

		conect();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			long id = resultSet.getLong("id");
			String nome = resultSet.getString("name");
			String cpf = resultSet.getString("cpf");
			Date birth = new Date(resultSet.getDate("date_birth").getTime());
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String login = resultSet.getString("login");
			boolean active = resultSet.getBoolean("active");

			User user = new User(nome, cpf, birth, email, password, login, active);
			user.setId(id);
			listUsers.add(user);
		}
		resultSet.close();
		statement.close();

		desconect();

		return listUsers;
	}


}
