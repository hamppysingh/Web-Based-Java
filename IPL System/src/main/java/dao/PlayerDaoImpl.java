package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.*;

import pojos.Player;

public class PlayerDaoImpl {
	private Connection cn;
	private PreparedStatement pst,pst2;
	public PlayerDaoImpl() throws SQLException {
		cn=openConnection();
		pst=cn.prepareStatement("insert into players values(default,?,?,?,?,?,?)");
		pst2=cn.prepareStatement("select name from teams where team_id=?");
	}	
	public String addPlayerToTeam(Player newPlayer,int teamId) throws SQLException{
		pst.setString(1,newPlayer.getFirstName());
		pst.setString(2,newPlayer.getLastName());
		pst.setDate(3,newPlayer.getDob());
		pst.setDouble(4,newPlayer.getBattingAvg());
		pst.setInt(5,newPlayer.getWicketsTaken());
		pst.setInt(6,teamId);
		int rowupdate=pst.executeUpdate();
		if (rowupdate==1)
		{	
			pst2.setInt(1,teamId);
			ResultSet rst=pst2.executeQuery();
			if(rst.next())
				return " Congratulations,"+newPlayer.getFirstName()+" "+newPlayer.getLastName()+" Welcome to "+rst.getString(1)+"....";
		}
		return "Player's Detail Could Not Be Inserted....";	
	}
	public void cleanUp() throws SQLException {
		if(cn!=null)
			cn.close();
	}
}
