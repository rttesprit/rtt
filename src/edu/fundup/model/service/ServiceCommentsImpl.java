package edu.fundup.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Comments;
import edu.fundup.model.iservice.IServiceComment;
import edu.fundup.utils.DataSource;

public class ServiceCommentsImpl implements IServiceComment {
	Connection conn = DataSource.getInstance().getConnection();
	Statement stmt;

	@Override
	public void create(Comments comments) throws DataBaseException {
		// TODO Auto-generated method stub
		try {
			long timeBefor = 0;
			long timeAfter = 0 ;
			System.out.println(DataSource.getInstance());
			timeBefor = System.currentTimeMillis();
			Connection conn = DataSource.getInstance().getConnection();
	        timeAfter = System.currentTimeMillis();
	        System.out.println(timeAfter - timeBefor);
	        
			String req = "INSERT INTO `comments` (`userid`, `postid`, `username`, `date`, `like`, `postuserid`,`comment`) VALUES (?, ?, ?, ?, ?, ?, ?);";
			System.out.println(comments.toString());	
			
			java.util.Date utilDate = new java.util.Date();
		    Date sqlDate = new Date(utilDate.getTime()+utilDate.getHours());		    
			PreparedStatement st = conn.prepareStatement(req);
			st.setInt(1, 555);
			st.setInt(2, comments.getPostid());
			st.setString(3,comments.getUsername());
			st.setDate(4, sqlDate);
			st.setBoolean(5,comments.isLike());
			st.setInt(6,comments.getPostowner());		
			st.setString(7,comments.getComment());		
 			System.out.println(st.executeUpdate());
		} catch (Exception ex) { 
            throw new DataBaseException(ex.getMessage());
	}
		
	}



	@Override
	public ArrayList<Comments> getAll() throws SQLException{
		// TODO Auto-generated method stub
		long timeBefor = 0;
		long timeAfter = 0 ;
		System.out.println(DataSource.getInstance());
		timeBefor = System.currentTimeMillis();
		Connection conn = DataSource.getInstance().getConnection();
        timeAfter = System.currentTimeMillis();
        
		String req = "select * from `comments` ";
		ArrayList<Comments> valeur = new ArrayList<>();
		
		try {
			PreparedStatement st = conn.prepareStatement(req);
			ResultSet resultSet1 = st.executeQuery();
			while (resultSet1.next()) {
				Comments a = new Comments();
 				a.setComid(resultSet1.getInt("comid"));
				a.setUserid(resultSet1.getInt("userid"));
				a.setPostid(resultSet1.getInt("postid"));
				a.setUsername(resultSet1.getString("username"));
				a.setDateTime(String.valueOf(resultSet1.getDate("date")));
				a.setLike(resultSet1.getBoolean("like"));
				a.setPostowner(resultSet1.getInt("postuserid"));
				a.setComment(resultSet1.getString("comment"));
				valeur.add(a);
			  }
			} catch (SQLException e) {
				e.printStackTrace();
	            throw new SQLException(e.getMessage());
			}
			
     
		return valeur;
	}

	@Override
	public void delete(Comments comments) throws DataBaseException {
		// TODO Auto-generated method stub
		long timeBefor = 0;
		long timeAfter = 0 ;
		System.out.println(DataSource.getInstance());
		timeBefor = System.currentTimeMillis();
		Connection conn = DataSource.getInstance().getConnection();
        timeAfter = System.currentTimeMillis();
        
		try {
		String req = "DELETE FROM `comments` WHERE `comments`.`comid`=?";
		PreparedStatement st = conn.prepareStatement(req);
		st.setInt(1, comments.getComid());
		st.executeUpdate();
		} catch (SQLException ex) {
            throw new DataBaseException(ex.getMessage());

		}
	}

	@Override
	public void update(Comments comments) throws DataBaseException {
		// TODO Auto-generated method stub
		
		long timeBefor = 0;
		long timeAfter = 0 ;
		System.out.println(DataSource.getInstance());
		timeBefor = System.currentTimeMillis();
		Connection conn = DataSource.getInstance().getConnection();
        timeAfter = System.currentTimeMillis();
        
		java.util.Date utilDate = new java.util.Date();
	    Date sqlDate = new Date(utilDate.getTime()+utilDate.getHours());
	    //Date sqlDate2 = new Date(animeaux.getEndFundDate().getTime()+utilDate.getHours());
		try {

			String req ="UPDATE `comments` SET `userid`=?, `postid`=?, `username`=?, `date`=?, `like`=?, `postuserid`=? WHERE `comid`=?;";
			PreparedStatement st = conn.prepareStatement(req);
			st.setInt(1, comments.getUserid());
			st.setInt(2, comments.getPostid());
			st.setString(3, comments.getUsername());
			st.setDate(4,sqlDate);
			st.setBoolean(5,comments.isLike());
			st.setInt(6,comments.getPostowner());
			st.setInt(7,comments.getComid());
			st.executeUpdate();			
	}
			catch (SQLException ex) {
				ex.printStackTrace();
	            throw new DataBaseException(ex.getMessage());

			}
	}




 
}

