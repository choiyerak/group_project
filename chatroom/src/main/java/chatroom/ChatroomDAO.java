package chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class ChatroomDAO {
	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;
	
	public ChatroomDAO() {
		dbopen = new DBOpen();
	}
	
	// 채팅방 생성 
	public int create(ChatroomDTO cdto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			sql.append(" INSERT INTO chatroom(c_no, chatroomno, chatroomname, chatroomowner, chatroommem) ");
			sql.append(" VALUES(c_seq.nextval, chatroom_seq.nextval, ?, ?, ?) ");
			System.out.println(sql.toString());
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cdto.getChatroomname());
			pstmt.setString(2, cdto.getChatroomowner());
			pstmt.setString(3, cdto.getChatroomowner());
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("채팅방 생성 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		} // t-c-f ends 
		return cnt;
	} // create() ends 
	
	// 채팅방 목록 
	public ArrayList<ChatroomDTO> chatroomList(){
		ArrayList<ChatroomDTO> clist = null;
		ChatroomDTO cdto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			sql.append(" SELECT a.chatroomno, a.chatroomname, a.chatroomowner, a.chatroommem, a.chatroomownernick ");
			sql.append(" FROM chatroom a  ");
			sql.append(" JOIN ( ");
			sql.append(" SELECT chatroomname, MIN(c_no) as min_c_no ");
			sql.append(" FROM chatroom ");
			sql.append(" GROUP BY chatroomname ");
			sql.append(" ) b ON a.chatroomname = b.chatroomname AND a.c_no = b.min_c_no");
			
			System.out.println(sql.toString());
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				clist = new ArrayList<ChatroomDTO>();
				do {
					cdto = new ChatroomDTO();
					
					cdto.setChatroomno(rs.getInt("chatroomno"));
					cdto.setChatroomname(rs.getString("chatroomname"));
					cdto.setChatroomowner(rs.getString("chatroomowner"));
					cdto.setChatroommem(rs.getString("chatroommem"));
					cdto.setChatroomownernick(rs.getString("chatroomownernick"));
					
					clist.add(cdto);
				} while(rs.next()); // do-while ends 반복 생성 
			} // if ends 다음 행 존재 여부 확인 
		} catch (Exception e) {
			System.out.println("채팅방 목록 생성 실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // t-c-f ends 
		return clist;
	} // chatroomList() ends 
	
	// 채팅방 가입 여부 확인 
	public int checkMem(ChatroomDTO cdto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			sql.append(" SELECT COUNT(*) AS cnt "); 
			sql.append(" FROM chatroom ");
			sql.append(" WHERE chatroomno = ? AND  chatroommem = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, cdto.getChatroomno());
			pstmt.setString(2, cdto.getChatroommem());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println("가입 여부 확인 실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return cnt;
	} // checkMem() ends 
	
	// 채팅방 정보 
	public ChatroomDTO checkRoomInfo(ChatroomDTO dto) {
		ChatroomDTO cdto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			sql.append(" SELECT chatroomno, chatroomname, chatroomowner, chatroomownernick ");
			sql.append(" FROM chatroom ");
			sql.append(" WHERE chatroomno = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getChatroomno());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cdto = new ChatroomDTO();
				do {
					cdto.setChatroomno(rs.getInt("chatroomno"));
					cdto.setChatroomname(rs.getString("chatroomname"));
					cdto.setChatroomowner(rs.getString("chatroomowner"));
					cdto.setChatroomownernick(rs.getString("chatroomownernick"));
				} while(rs.next()); // do-while ends 
			} // if ends 다음 행 존재 여부 확인 
		} catch (Exception e) {
			System.out.println("채팅방 정보 확인 실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		} // t-c-f ends 
		return cdto;
	} // chatRoomInfo() ends 
	
	// 채팅방 가입 
	public int chatroomJoin(ChatroomDTO cdto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			System.out.println(cdto);
			sql.append(" INSERT INTO chatroom(c_no, chatroomno, chatroomname, chatroomowner, chatroomownernick, chatroommem, chatroommemnick) ");
			sql.append(" VALUES(c_seq.nextval, ?, ?, ?, ?, ?, ?) ");
			System.out.println(sql.toString());
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, cdto.getChatroomno());
			pstmt.setString(2, cdto.getChatroomname());
			pstmt.setString(3, cdto.getChatroomowner());
			pstmt.setString(4, cdto.getChatroomownernick());
			pstmt.setString(5, cdto.getChatroommem());
			pstmt.setString(6, cdto.getChatroommemnick());
			
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("채팅방 가입 실패" + e);
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	} // chatroomJoin() ends 
	
	// 채팅방 가입자 수 
	public int getSigninUsersCount(int chatroomno) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			 
			sql.append("SELECT COUNT(chatroomno) AS cnt ");
			sql.append("FROM chatroom ");
			sql.append("WHERE chatroomno = ? "); 
			sql.append("GROUP BY chatroomno ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, chatroomno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println("가입자 수 확인 실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return cnt;
	} // getSigninUsersCount() ends 
}

