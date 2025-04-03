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

	private void disconnect() throws SQLException {
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
		Date nascimento = new Date(user.getDateBirth().getTime());
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

		disconnect();
		
		user.setId(id);
		return user;
	}
	
	public List<User> listAllUsers() throws SQLException {
	    List<User> users = new ArrayList<>();

	    String sql = "SELECT * FROM usuario";

	    conect();

	    Statement statement = connection.createStatement();
	    ResultSet resultSet = statement.executeQuery(sql);

	    while (resultSet.next()) {
	        User user = new User();
	        user.setId(resultSet.getLong("id"));
	        user.setName(resultSet.getString("name"));
	        user.setCpf(resultSet.getString("cpf"));
	        
	        // Verifica se a data de nascimento não é nula antes de atribuir
	        java.sql.Date sqlDate = resultSet.getDate("date_birth");
	        if (sqlDate != null) {
	            user.setBirth(new java.util.Date(sqlDate.getTime()));
	        } else {
	            user.setBirth(null); // Garante que não cause NullPointerException
	        }

	        user.setEmail(resultSet.getString("email"));
	        user.setPassword(resultSet.getString("password"));
	        user.setLogin(resultSet.getString("login"));
	        user.setActive(resultSet.getBoolean("active"));

	        users.add(user);
	    }

	    resultSet.close();
	    statement.close();
	    disconnect();

	    return users;
	}

	
	public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM usuario where id = ?";
        
        conect();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, user.getId());
         
        boolean disconnectLine = statement.executeUpdate() > 0; //return a int value if return > 0, the query was executed
        statement.close();
        
        disconnect();
        
        return disconnectLine;     
   }

	public boolean editUser(User user) throws SQLException {
	    String sql = "UPDATE usuario SET name = ?, cpf = ?, date_birth = ?, email = ?, password = ?, login = ?, active = ?"
	            + " WHERE id = ?";		    

	    conect();

	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.setString(1, user.getName());
	    statement.setString(2, user.getCpf());

	    // Verifica se a data de nascimento não é nula antes de setar no statement
	    if (user.getDateBirth() != null) {
	        statement.setDate(3, new java.sql.Date(user.getDateBirth().getTime()));
	    } else {
	        statement.setNull(3, java.sql.Types.DATE); // Define como NULL no banco de dados
	    }

	    statement.setString(4, user.getEmail());
	    statement.setString(5, user.getPassword());
	    statement.setString(6, user.getLogin());
	    statement.setBoolean(7, user.isActive());
	    statement.setLong(8, user.getId());

	    boolean linhaAlterada  = statement.executeUpdate() > 0;
	    statement.close();

	    disconnect();
	    return linhaAlterada;
	}



}
