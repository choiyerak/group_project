package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.utility.DBClose;
import net.utility.DBOpen;

public class UserDAO {
	private DBOpen dbopen = null;
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private StringBuilder sql = null;
	
	public UserDAO() {
		dbopen = new DBOpen();
	}
	
	public int create(UserDTO udto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" INSERT INTO chatUser ( id, passwd, nick ) ");
			sql.append(" VALUES(?, ?, ?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, udto.getId());
			pstmt.setString(2, udto.getPasswd());
			pstmt.setString(3, udto.getNick());
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("회원 입력 실패");
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);
		}
		return cnt;
	} // create() ends 
	
	public int login(UserDTO udto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			
			sql.append(" SELECT COUNT(*) AS cnt ");
			sql.append(" FROM chatUser ");
			sql.append(" WHERE id = ? AND passwd = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, udto.getId());
			pstmt.setString(2, udto.getPasswd());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println("회원 확인 실패" + e);
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return cnt;
	}
}
