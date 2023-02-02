package help.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import help.model.Help;
import help.model.HelpList;
import article.model.ModifyRequest;
import help.model.SearchHelp;
import help.model.WriterRequest;
import jdbc.JdbcUtil;

public class HelpDAO {
	//insert 기능 현재 로그인 정보를 가져와 입력해야함 .
	public void insert(Connection conn,WriterRequest writerReq)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into HELPBOARD(article_no,article_category,user_no,help_category) " + 
				"value(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, writerReq.getArticleNo()); 
			pstmt.setString(2, writerReq.getArticleCategory());
			pstmt.setInt(3, writerReq.getUserNo());
			pstmt.setString(4, writerReq.getHelpCategory());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public List<HelpList> select(Connection conn, int startRow,int size)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN helpboard h ON a.article_no=h.article_no " +
						"where a.isshow='Y' order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, articleCategory);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<HelpList> helpList = new ArrayList<>();
			while(rs.next()) {
				HelpList helpList1 = coverHelpList(rs);
				if(helpList != null) {
					helpList.add(helpList1);
				}
			}
			return helpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	public List<HelpList> search(Connection conn,SearchHelp search)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article a inner JOIN helpboard h ON a.article_no=h.article_no where a.isshow='Y' and h.help_category like ? and a.article_title like ? " + 
						 "order by a.article_no desc limit ?,? " ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search.getCategory()+"%");
			pstmt.setString(2, "%"+search.getInput()+"%");
			pstmt.setInt(3, search.getStartRow());
			pstmt.setInt(4, search.getSize());
			rs = pstmt.executeQuery();
			List<HelpList> helpList = new ArrayList<>();
			while(rs.next()) {
				HelpList helpList1 = coverHelpList(rs);
				if(helpList1 != null) {
					helpList.add(helpList1);
				}
			}
			return helpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public Help selectByNo(Connection conn,int articleNo)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from helpboard where article_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNo);
			rs = pstmt.executeQuery();
			Help help = null;
			
			if(rs.next()) {
				help = coverHelp(rs);
			}
			return help;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	public int selectCount(Connection conn)throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from article where isshow='Y' and article_category='help' ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("count(*)");
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public void update(Connection conn,ModifyRequest modReq,String modCategory)throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update helpboard set help_category=? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,modCategory);
			pstmt.setInt(2, modReq.getArticleNo());
			
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}

	private Help coverHelp(ResultSet rs) throws SQLException {
		return new Help(rs.getInt("article_no"),rs.getString("article_category"),rs.getInt("user_no"),
						rs.getString("help_category"));
	}
	private HelpList coverHelpList(ResultSet rs)throws SQLException {
		return new HelpList(rs.getInt("article_no"),rs.getString("article_category"),rs.getString("article_title"),rs.getString("user_name"),rs.getString("article_content"),rs.getTimestamp("article_credate"),
					rs.getTimestamp("article_update"),rs.getInt("article_readcnt"),rs.getString("isshow"),rs.getInt("user_no"),rs.getString("help_category"));
	}
}
