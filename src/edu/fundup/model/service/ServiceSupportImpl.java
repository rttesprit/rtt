//package edu.fundup.model.service;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import edu.fundup.exception.DataBaseException;
//  import edu.fundup.model.entity.PostAnimalSupport;
//import edu.fundup.model.iservice.IServiceSupport;
//import edu.fundup.utils.DataSource;
//
//public class ServiceSupportImpl implements IServiceSupport {
//
//	@Override
//	public void create(PostAnimalSupport postAnimalSupport) throws DataBaseException {
//		// TODO Auto-generated method stub
//		try {
//			long timeBefor = 0;
//			long timeAfter = 0 ;
//			System.out.println(DataSource.getInstance());
//			timeBefor = System.currentTimeMillis();
//			Connection conn = DataSource.getInstance().getConnection();
//	        timeAfter = System.currentTimeMillis();
//	        System.out.println(timeAfter - timeBefor);
//	        
//			String req = "INSERT INTO `support` (`postid`, `memberid`, `postownerid`, `membername`, `supporterphoto`) VALUES (?, ?, ?, ?, ?);";			
//			java.util.Date utilDate = new java.util.Date();
//		    Date sqlDate = new Date(utilDate.getTime()+utilDate.getHours());		    
//			PreparedStatement st = conn.prepareStatement(req);
//		
//			//************************************************ Static ************************//
//			st.setInt(1,555 );
//			//************************************************ ****** ************************//
//
//			st.setInt(2, settings.getPostid());
//			st.setBoolean(3,settings.isCommentnotif());
//			st.setBoolean(4,settings.isReachsupporters());		
//			st.setInt(5,settings.getSupportgoal());		
//			st.setBoolean(6,settings.isVisiblePost());		
// 			System.out.println(st.executeUpdate());
//		} catch (Exception ex) { 
//            throw new DataBaseException(ex.getMessage());
//	}
//		
//	}
//
//
//
//	@Override
//	public ArrayList<PostAnimalSettings> getAll() throws SQLException{
//		// TODO Auto-generated method stub
//		long timeBefor = 0;
//		long timeAfter = 0 ;
//		System.out.println(DataSource.getInstance());
//		timeBefor = System.currentTimeMillis();
//		Connection conn = DataSource.getInstance().getConnection();
//        timeAfter = System.currentTimeMillis();
//        
//		String req = "select * from `settings`";
//		ArrayList<PostAnimalSettings> valeur = new ArrayList<>();
//		
//		try {
//			PreparedStatement st = conn.prepareStatement(req);
//			ResultSet resultSet1 = st.executeQuery();
//			while (resultSet1.next()) {
//				PostAnimalSettings a = new PostAnimalSettings();
//				a.setSettid(resultSet1.getInt("settid"));
//				a.setUserid(resultSet1.getInt("memberid"));
//				a.setPostid(resultSet1.getInt("postid"));
//				a.setCommentnotif(resultSet1.getBoolean("iscommentnotif"));
//				a.setReachsupporters(resultSet1.getBoolean("isreachsupporters"));
//				a.setsupportgoal(resultSet1.getInt("supportgoal"));
//				a.setisVisiblePost(resultSet1.getBoolean("isvisiblepost"));
//				valeur.add(a);
//			  }
//			} catch (SQLException e) {
//				e.printStackTrace();
//	            throw new SQLException(e.getMessage());
//			}
//			
//     
//		return valeur;
//	}
//
//	@Override
//	public void delete(PostAnimalSettings settings) throws DataBaseException {
//		// TODO Auto-generated method stub
//		long timeBefor = 0;
//		long timeAfter = 0 ;
//		System.out.println(DataSource.getInstance());
//		timeBefor = System.currentTimeMillis();
//		Connection conn = DataSource.getInstance().getConnection();
//        timeAfter = System.currentTimeMillis();
//        
//		try {
//		String req = "DELETE FROM `settings` WHERE `settings`.`settid`=?";
//		PreparedStatement st = conn.prepareStatement(req);
//		st.setInt(1, settings.getSettid());
//		st.executeUpdate();
//		} catch (SQLException ex) {
//            throw new DataBaseException(ex.getMessage());
//
//		}
//	}
//
//	@Override
//	public void update(PostAnimalSettings settings) throws DataBaseException {
//		// TODO Auto-generated method stub
//		
//		long timeBefor = 0;
//		long timeAfter = 0 ;
//		System.out.println(DataSource.getInstance());
//		timeBefor = System.currentTimeMillis();
//		Connection conn = DataSource.getInstance().getConnection();
//        timeAfter = System.currentTimeMillis();
//        
//		java.util.Date utilDate = new java.util.Date();
//	    Date sqlDate = new Date(utilDate.getTime()+utilDate.getHours());
//	    //Date sqlDate2 = new Date(animeaux.getEndFundDate().getTime()+utilDate.getHours());
//		try {
//
//			String req ="UPDATE `settings` SET `memberid`=?, `postid`=?, `iscommentnotif`=?, `isreachsupporters`=?, `supportgoal`=?, `isvisiblepost`=? WHERE `settid`=?;";
//			PreparedStatement st = conn.prepareStatement(req);
//			st.setInt(1, settings.getMemberid());
//			st.setInt(2, settings.getPostid());
//			st.setBoolean(3, settings.isCommentnotif());
//			st.setBoolean(4,settings.isReachsupporters());
//			st.setInt(5,settings.getSupportgoal());
//			st.setBoolean(6,settings.isVisiblePost());
//			st.setInt(7,settings.getSettid());
//			st.executeUpdate();			
//	}
//			catch (SQLException ex) {
//				ex.printStackTrace();
//	            throw new DataBaseException(ex.getMessage());
//
//			}
//	}
//	
//	@Override
//	public PostAnimalSettings getByPostId(Animeaux anim){
//		long timeBefor = 0;
//		long timeAfter = 0 ;
//		System.out.println(DataSource.getInstance());
//		timeBefor = System.currentTimeMillis();
//		Connection conn = DataSource.getInstance().getConnection();
//        timeAfter = System.currentTimeMillis();
//        
//		// TODO Auto-generated method stub
//		String req = "SELECT * FROM `settings` WHERE `postid`= ?";
//		PostAnimalSettings a = new PostAnimalSettings();
//		try {
//			PreparedStatement st = conn.prepareStatement(req);
//			st.setInt(1, anim.getIdamin());
//		ResultSet resultSet1 = st.executeQuery();
//		while (resultSet1.next()) {
//			a.setSettid(resultSet1.getInt("settid"));
//			a.setUserid(resultSet1.getInt("memberid"));
//			a.setPostid(resultSet1.getInt("postid"));
//			a.setCommentnotif(resultSet1.getBoolean("iscommentnotif"));
//			a.setReachsupporters(resultSet1.getBoolean("isreachsupporters"));
//			a.setsupportgoal(resultSet1.getInt("supportgoal"));
//			a.setisVisiblePost(resultSet1.getBoolean("isvisiblepost"));
//		}	
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return a;
//	}
//}
