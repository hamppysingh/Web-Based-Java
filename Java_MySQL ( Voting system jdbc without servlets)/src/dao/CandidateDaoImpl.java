package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pojos.Candidate;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.openConnection;

public class CandidateDaoImpl {
	private Connection cn;
	private PreparedStatement pst,pst2,pst3,pst4;
	public CandidateDaoImpl() throws SQLException {
		cn =openConnection();
		pst=cn.prepareStatement("select * from candidates");
		pst2=cn.prepareStatement("update candidates set votes=votes+1 where id=?");
		pst3=cn.prepareStatement("select * from candidates order by votes desc limit 2");
		pst4=cn.prepareStatement("select party,sum(votes) from candidates group by party");
	}
	public List<Candidate> getAllCandidates() throws SQLException{
		ResultSet rs=pst.executeQuery();
		List<Candidate> list=new ArrayList<>();
		while(rs.next())
		{
			list.add(new Candidate(rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		return list;
	}
	public List<Candidate> getTopCandidates() throws SQLException{
		ResultSet rs=pst3.executeQuery();
		List<Candidate> list=new ArrayList<>();
		while(rs.next())
		{
			list.add(new Candidate(rs.getString(2),rs.getString(3),rs.getInt(4)));
		}
		return list;
	}
	public Map<String,Integer> increasevote(int id) throws SQLException {
		pst2.setInt(1,id);
		pst2.executeUpdate();
		ResultSet rs=pst4.executeQuery();
		Map<String,Integer> mp=new HashMap<>();
		while(rs.next()) {
			mp.put(rs.getString(1),rs.getInt(2));
		}
		return mp;
	}
	public void cleanUp() throws SQLException {
		if (pst != null)
			pst.close();
		closeConnection();
		System.out.println("Candidate dao cleaned up....");
	}
}
