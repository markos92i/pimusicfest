package model;

public class User {
	private String _id;
	private String password;
	private String role; //admin, user
	public User() {}
	public User(String _id, String password, String role) {
		this._id = _id;
		this.password = password;
		this.role = role;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [_id=" + _id + ", password=" + password + ", role=" + role + "]";
	}
}
