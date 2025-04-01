package br.edu.ifms.model;

import java.util.Date;

public class User {
	
	private long id;	
	private String name;
	private String cpf;
	private Date dateBirth;
	private String email;
	private String login;
	private String password;
	private Boolean active;
	
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String name, String cpf, Date dateBirth, String email, String login, String password, Boolean active) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.dateBirth = dateBirth;
		this.email = email;
		this.login = login;
		this.password = password;
		this.active = active;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDateBirth() { 
	    return dateBirth;
	}
	public void setBirth(Date birth) {
		this.dateBirth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
	    return Boolean.TRUE.equals(active); // return false if active == null
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", cpf=" + cpf + ", dateBirth=" + dateBirth + ", email=" + email + ", login="
				+ login + ", password=" + password + ", active=" + active + "]";
	}
	
	
	

}
