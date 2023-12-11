package pojos;

public class Candidate {
	private static int inc=1;
	private int id;
	private String name;
	private String party;
	private int votes;
	public Candidate(String name, String party, int votes) {
		this.id=inc++;
		this.name = name;
		this.party = party;
		this.votes = votes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", party=" + party + ", votes=" + votes + "]\n";
	}
	
}
