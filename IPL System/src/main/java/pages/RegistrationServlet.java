package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PlayerDaoImpl;
import dao.TeamDaoImpl;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(urlPatterns = "/register", loadOnStartup = 1)
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private TeamDaoImpl teamDao;
       private PlayerDaoImpl playerDao;
       public void init() throws ServletException {
   		try {
   			teamDao = new TeamDaoImpl();
   			playerDao=new PlayerDaoImpl();
   		} catch (Exception e) {
   			// centralized err handling in servlets :
   			// How to tell WC that init has failed n not to continue
   			// with the service phase : simply throw ServletException
   			// to the WC
   			// ServletException(String mesg,Throwable rootCause)
   			throw new ServletException("err in init of " + getClass(), e);
   		}
   	}
       public void destroy() {
    	   try {
    		   teamDao.cleanUp();
    	   }catch (Exception e) {
   			System.out.println("err in destroy of " + getClass() + " " + e);
   			// how to inform the WC ?
   			// throw new RuntimeException("err in destroy of "+getClass(), e);
   		}
    	   
       }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		try(PrintWriter pw=response.getWriter()){
			HttpSession session=request.getSession();
			session.setAttribute("team_Dao",teamDao);
			session.setAttribute("player_Dao",playerDao);
			
			pw.print("<form method='post' action='add_player'>");
			pw.print("<h5> Choose a Team : <select name='abbrevation'>");
			for (String s : teamDao.getTeamsAbbreviations())
				pw.print("<h5><option value='" + s + "'</option>" + s + "</h5>");
			pw.print("</h5> </select>");
			pw.print("<h5>Player Name <input type='text' name='nm'/></h5>");
			pw.print("<h5>DoB <input type='date' name='dob'/></h5>");
			pw.print("<h5>Batting Avg <input type='number' name='avg'/></h5>");
			pw.print("<h5>Wickets Taken <input type='text' name='wickets'/></h5>");
			pw.print("<h5><input type='submit' value='Add New Player'/></h5>");
			pw.print("</form>");
		}catch(Exception E)
		{
			throw new ServletException("error in doPost of Registration!!"+getClass(),E);
		}
	}

}
