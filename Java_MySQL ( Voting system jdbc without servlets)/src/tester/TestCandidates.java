package tester;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.CandidateDaoImpl;
import pojos.Candidate;



public class TestCandidates {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create user dao instance
			CandidateDaoImpl candidate=new CandidateDaoImpl();
			List<Candidate> list=new ArrayList<>(candidate.getAllCandidates());
			System.out.println(list);
			System.out.println();
			System.out.println("Enter the candidate Id : ");
			int a=sc.nextInt();
			Map<String,Integer> m=new HashMap<>(candidate.increasevote(a));
			for(Map.Entry<String,Integer> p:m.entrySet())
			{
				System.out.println(p.getKey()+" : " +p.getValue());
			}
			List<Candidate> list1=new ArrayList<>(candidate.getTopCandidates());
			System.out.println(list1);
			// Clean up
			candidate.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}