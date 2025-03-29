package br.edu.ifms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
		if(resultSet.next()) //houve algum retorno no ID?
			id = resultSet.getInt("id");
		statement.close();

		desconect();
		
		user.setId(id);
		return user;
	}

}
