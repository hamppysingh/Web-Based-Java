package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import pojos.Candidate;

public interface CandidateDao {
	List<Candidate> getAllCandidates() throws SQLException;
	Map<String,Integer> increasevote(int id) throws SQLException;
	public List<Candidate> getTopCandidates() throws SQLException;
}
