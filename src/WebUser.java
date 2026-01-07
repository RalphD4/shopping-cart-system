
public class WebUser {
	private String login_id;
	private String password;
	private UserState state;
	
	//setters;
	public void set_login(String username) {
		this.login_id = username;
	}
	
	public void set_password(String user_pass) {
		this.password = user_pass;
	}
	
	public void set_state(UserState x) {
		this.state = x;
	}
	
	//getters;
	public String get_login() {
		return this.login_id;
	}
	
	public String get_password() {
		return this.password;
	}
	
	public UserState get_state() {
		return this.state;
	}

}
