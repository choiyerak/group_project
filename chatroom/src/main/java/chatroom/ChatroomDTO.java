package chatroom;

public class ChatroomDTO {
	private int c_no;
	private int chatroomno;
    private String chatroomname;
    private String chatroomowner;
    private String chatroommem;
    private String chatroomownernick;
    private String chatroommemnick;
    
    public ChatroomDTO() {}

    
	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	
	public int getChatroomno() {
		return chatroomno;
	}

	public void setChatroomno(int chatroomno) {
		this.chatroomno = chatroomno;
	}

	public String getChatroomname() {
		return chatroomname;
	}

	public void setChatroomname(String chatroomname) {
		this.chatroomname = chatroomname;
	}

	public String getChatroomowner() {
		return chatroomowner;
	}

	public void setChatroomowner(String chatroomowner) {
		this.chatroomowner = chatroomowner;
	}

	public String getChatroommem() {
		return chatroommem;
	}

	public void setChatroommem(String chatroommem) {
		this.chatroommem = chatroommem;
	}

	public String getChatroomownernick() {
		return chatroomownernick;
	}

	public void setChatroomownernick(String chatroomownernick) {
		this.chatroomownernick = chatroomownernick;
	}

	public String getChatroommemnick() {
		return chatroommemnick;
	}

	public void setChatroommemnick(String chatroommemnick) {
		this.chatroommemnick = chatroommemnick;
	}


	@Override
	public String toString() {
		return "chatroomDTO [c_no=" + c_no + ", chatroomno=" + chatroomno + ", chatroomname=" + chatroomname
				+ ", chatroomowner=" + chatroomowner + ", chatroommem=" + chatroommem + ", chatroomownernick="
				+ chatroomownernick + ", chatroommemnick=" + chatroommemnick + "]";
	}
	
}
