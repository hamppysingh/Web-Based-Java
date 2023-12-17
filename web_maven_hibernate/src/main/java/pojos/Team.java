package pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
/*
 * id , name, abbreviation,owner,max_age,batting_avg,wickets_taken	
 */
@Entity
@Table(name="teams")
public class Team extends BaseEntity{
	
	@Column(length=100,unique=true)
	private String name;
	
	@Column(length=10,unique=true)
	
	private String abbreviation;
	
	@Column(length=20)
	private String owner;
	
	@Column(name="max_age")
	private int maxAge;
	
	@Column(name="batting_avg")
	private double minBattingAvg;
	
	@Column(name="wickets_taken")
	private int minWicketsTaken;
	//Team 1-->*Player
	//Terms:one ,parent table ,inverse (Since no FK here)
	@OneToMany(mappedBy="myTeam")
	private List<Player> players=new ArrayList<>();
	public List<Player> getPlayers() {
		return players;
	}


	public void setPlayers(List<Player> players) {
		this.players = players;
	}


	//initialize collection based prop to an empty collection toavoid nullpinter exception
	public Team() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Team(String name, String abbreviation, String owner, int maxAge, double minBattingAvg,
			int minWicketsTaken) {
		super();
		
		this.name = name;
		this.abbreviation = abbreviation;
		this.owner = owner;
		this.maxAge = maxAge;
		this.minBattingAvg = minBattingAvg;
		this.minWicketsTaken = minWicketsTaken;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public double getMinBattingAvg() {
		return minBattingAvg;
	}
	public void setMinBattingAvg(double minBattingAvg) {
		this.minBattingAvg = minBattingAvg;
	}
	public int getMinWicketsTaken() {
		return minWicketsTaken;
	}
	public void setMinWicketsTaken(int minWicketsTaken) {
		this.minWicketsTaken = minWicketsTaken;
	}
	@Override
	public String toString() {
		return "Team [teamId=" + getId() + ", name=" + name + ", abbreviation=" + abbreviation + ", owner=" + owner
				+ ", maxAge=" + maxAge + ", minBattingAvg=" + minBattingAvg + ", minWicketsTaken=" + minWicketsTaken
				+ "]";
	}
	
}
