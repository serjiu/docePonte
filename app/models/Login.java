package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

public class Login extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Constraints.Required
	@Constraints.Email
	@Constraints.MinLength(7)
	public String email;
	@Constraints.Required
	public String password;

	public String validate() {
		if (User.authenticate(email, password) == null) {
			return "Invalid user or password";
		}
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}