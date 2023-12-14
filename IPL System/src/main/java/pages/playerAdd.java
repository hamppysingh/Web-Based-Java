package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PlayerDaoImpl;
import dao.PlayerDaoImpl.*;
import dao.TeamDaoImpl;
import dao.TeamDaoImpl.*;
import pojos.Player;
import pojos.Team;

/**
 * Servlet implementation class playerAdd
 */
@WebServlet("/add_player")
public class playerAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
      PlayerDaoImpl playerDao;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
   			playerDao = new PlayerDaoImpl();
   		} catch (Exception e) {
   			// centralized err handling in servlets :
   			// How to tell WC that init has failed n not to continue
   			// with the service phase : simply throw ServletException
   			// to the WC
   			// ServletException(String mesg,Throwable rootCause)
   			throw new ServletException("err in init of " + getClass(), e);
   		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
 		   playerDao.cleanUp();
 	   }catch (Exception e) {
			System.out.println("err in destroy of " + getClass() + " " + e);
			// how to inform the WC ?
			// throw new RuntimeException("err in destroy of "+getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter pw =response.getWriter()) {
			HttpSession hs=request.getSession();
			
			TeamDaoImpl teamDao=(TeamDaoImpl)hs.getAttribute("team_Dao");
			PlayerDaoImpl playerDao=(PlayerDaoImpl)hs.getAttribute("player_Dao");
			

			
			String teamChosen=request.getParameter("abbrevation");
			String name=request.getParameter("nm");
			String[] fName=name.split(" ");
			
			Date date=Date.valueOf(request.getParameter("dob"));
			double batting_avg=Double.parseDouble(request.getParameter("avg"));
			int wicket_taken=Integer.parseInt(request.getParameter("wickets"));
			
			long age=Period.between(LocalDate.now(),date.toLocalDate()).toTotalMonths()/12;
			
			for(Team team:teamDao.getAllTeamDetails()) {
				if(teamChosen.equals(team.getAbbreviation())) {
					
				      System.out.println("In validate");
						if(team.getMaxAge()>=age && team.getMinBattingAvg()<=batting_avg
								|| team.getMinWicketsTaken()<=wicket_taken) {
							System.out.println("Adding Player");
							int id=team.getTeamId();
							Player player=new Player(fName[0],fName[1],date,batting_avg,wicket_taken,id);
					        String s = playerDao.addPlayerToTeam(player,id);
					        pw.print("<h5>"+s+"</h5");
						}
				   }
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
