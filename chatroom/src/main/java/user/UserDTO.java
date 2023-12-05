package user;

public class UserDTO {
	private String id;
	private String passwd;
	private String nick;
	
	public UserDTO() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", passwd=" + passwd + ", nick=" + nick + "]";
	}
}
