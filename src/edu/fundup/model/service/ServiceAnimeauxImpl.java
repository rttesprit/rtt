package edu.fundup.model.service;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import edu.fundup.model.iservice.IServiceanimeaux;
import edu.fundup.utils.DataSource;


public class ServiceAnimeauxImpl implements IServiceanimeaux {
	Connection conn = DataSource.getInstance().getConnection();
	Statement stmt;

	@Override
	public void create(Animeaux animeaux) throws DataBaseException {
		// TODO Auto-generated method stub
		try {
			long timeBefor = 0;
			long timeAfter = 0 ;
			System.out.println(DataSource.getInstance());
			timeBefor = System.currentTimeMillis();
			Connection conn = DataSource.getInstance().getConnection();
	        timeAfter = System.currentTimeMillis();
	        System.out.println(timeAfter - timeBefor);
	        
			String req = "INSERT INTO `animal` (`nom`, `etat`, `declaree`, `montant`, `iduser`, `photo`, `video`, `date_creation`, `idcat`,`titre`,`location`,`goal`,`fundtype`,`endfunddate`,`desc`,`sms`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			System.out.println(animeaux.toString());	
			
			java.util.Date utilDate = new java.util.Date();
		    Date sqlDate = new Date(utilDate.getTime()+utilDate.getHours());
		   // Date sqlDate2 = new Date(animeaux.getEndFundDate().getTime()+utilDate.getHours());
		    
		    
			PreparedStatement st = conn.prepareStatement(req);
			st.setString(1, animeaux.getNom());
			st.setString(2, animeaux.getEtat());
			st.setInt(3,1);
			st.setDouble(4, animeaux.getMontant());
			st.setInt(5,animeaux.getIduser());
			st.setString(6,animeaux.getPhoto());
			st.setString(7,animeaux.getVideo());
			st.setDate(8,sqlDate);
			st.setInt(9,animeaux.getIdcat());
			st.setString(10,animeaux.getTitre());
			st.setString(11,animeaux.getLocation());
			st.setInt(12,animeaux.getGoal());
			st.setString(13,animeaux.getFundtype());
			st.setString(14,animeaux.getEndFundDate());
			st.setString(15,animeaux.getDescription());
			st.setBoolean(16,animeaux.isSms());
			
			/*st.setInt(1, animeaux.getIduser());
			st.setInt(2, animeaux.getIdcat());*/
 			System.out.println(st.executeUpdate());
		} catch (Exception ex) { 
            throw new DataBaseException(ex.getMessage());
	}
		
	}



	@Override
	public ArrayList<Animeaux> getAll() throws SQLException{
		// TODO Auto-generated method stub
		long timeBefor = 0;
		long timeAfter = 0 ;
		System.out.println(DataSource.getInstance());
		timeBefor = System.currentTimeMillis();
		Connection conn = DataSource.getInstance().getConnection();
        timeAfter = System.currentTimeMillis();
        
		String req = "select * from `animal` ";
		ArrayList<Animeaux> valeur = new ArrayList<>();
		
		try {
			PreparedStatement st = conn.prepareStatement(req);
			ResultSet resultSet1 = st.executeQuery();
			while (resultSet1.next()) {
				Animeaux a = new Animeaux();
				a.setIdamin(resultSet1.getInt("idamin"));
				a.setNom(resultSet1.getString("nom"));
				a.setEtat(resultSet1.getString("etat"));
				a.setMontant(resultSet1.getDouble("montant"));
				a.setIduser(resultSet1.getInt("iduser"));
				a.setPhoto(resultSet1.getString("photo"));
				a.setVideo(resultSet1.getString("video"));
				a.setIdcat(resultSet1.getInt("idcat"));
				a.setTitre(resultSet1.getString("titre"));
				a.setLocation(resultSet1.getString("location"));
				a.setGoal(resultSet1.getInt("goal"));
				a.setFundtype(resultSet1.getString("fundtype"));
				a.setEndFundDate(resultSet1.getString("endfunddate"));
				a.setDescription(resultSet1.getString("desc"));
				a.setSms(resultSet1.getBoolean("sms"));
				valeur.add(a);
			  }
			} catch (SQLException e) {
				e.printStackTrace();
	            throw new SQLException(e.getMessage());
			}
			
     
		return valeur;
	}

	@Override
	public void delete(Animeaux animeaux) throws DataBaseException {
		// TODO Auto-generated method stub
		long timeBefor = 0;
		long timeAfter = 0 ;
		System.out.println(DataSource.getInstance());
		timeBefor = System.currentTimeMillis();
		Connection conn = DataSource.getInstance().getConnection();
        timeAfter = System.currentTimeMillis();
        
		try {
		String req = "DELETE FROM `animal` WHERE `animal`.`idamin`=?";
		PreparedStatement st = conn.prepareStatement(req);
		st.setInt(1, animeaux.getIdamin());
		st.executeUpdate();
		} catch (SQLException ex) {
            throw new DataBaseException(ex.getMessage());

		}
	}

	@Override
	public void update(Animeaux animeaux) throws DataBaseException {
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
		
			String req ="UPDATE `animal` SET nom=?, etat=?, declaree=?, montant=?, iduser=?, photo=?, video=?, date_creation=?, idcat=?, titre=?, location=?, goal=?, fundtype=?, endfunddate=?, desc=?, sms=?";
			PreparedStatement st = conn.prepareStatement(req);
			st.setString(1, animeaux.getNom());
			st.setString(2, animeaux.getEtat());
			st.setInt(3,1);
			st.setDouble(4, animeaux.getMontant());
			st.setInt(5,animeaux.getIduser());
			st.setString(6,animeaux.getPhoto());
			st.setString(7,animeaux.getVideo());
			st.setDate(8,sqlDate);
			st.setInt(9,animeaux.getIdcat());
			st.setString(10,animeaux.getTitre());
			st.setString(11,animeaux.getLocation());
			st.setInt(12,animeaux.getGoal());
			st.setString(13,animeaux.getFundtype());
			st.setString(14,animeaux.getEndFundDate());
			st.setString(15,animeaux.getDescription());
			st.setBoolean(16,animeaux.isSms());

			st.executeUpdate();			
			}
			catch (SQLException ex) {
				ex.printStackTrace();
	            throw new DataBaseException(ex.getMessage());

			}
	}

	@Override
	public Animeaux getById(int codeB) throws DataBaseException {
		
		long timeBefor = 0;
		long timeAfter = 0 ;
		System.out.println(DataSource.getInstance());
		timeBefor = System.currentTimeMillis();
		Connection conn = DataSource.getInstance().getConnection();
        timeAfter = System.currentTimeMillis();
        
		// TODO Auto-generated method stub
		String req = "select * from `animal` where idamin= ?";
		Animeaux a = new Animeaux();
		try {
			PreparedStatement st = conn.prepareStatement(req);
			st.setInt(1, codeB);
		ResultSet resultSet1 = st.executeQuery();
		while (resultSet1.next()) {
			a.setNom(resultSet1.getString("nom"));
			a.setEtat(resultSet1.getString("etat"));
			a.setMontant(resultSet1.getDouble("montant"));
			a.setIduser(resultSet1.getInt("iduser"));
			a.setPhoto(resultSet1.getString("photo"));
			a.setVideo(resultSet1.getString("video"));
			a.setIdcat(resultSet1.getInt("idcat"));
			a.setTitre(resultSet1.getString("titre"));
			a.setLocation(resultSet1.getString("location"));
			a.setGoal(resultSet1.getInt("goal"));
			a.setFundtype(resultSet1.getString("fundtype"));
			a.setEndFundDate(resultSet1.getString("endfunddate"));
			a.setDescription(resultSet1.getString("desc"));
			a.setSms(resultSet1.getBoolean("desc"));
		}	
		} catch (SQLException e) {
			e.printStackTrace();
            throw new DataBaseException(e.getMessage());
		}
		return a;
	}
	
	public  int getUserId(String username) {
		String req = "select * from `membre` where username= ?";
		int id = -1;
		try {
			PreparedStatement st = conn.prepareStatement(req);
			st.setString(1, username);
			ResultSet resultSet1 = st.executeQuery();
			resultSet1.next();
			id = resultSet1.getInt("id");
			}
		
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
 			}
		return id;

	}


 
}

