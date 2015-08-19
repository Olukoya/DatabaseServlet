import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/Database")
public class Database extends HttpServlet  
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
        res.setContentType("text/html");        
          
 
        try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             Connection con=DriverManager.getConnection("jdbc:oracle:thin:testuser/password@localhost","testdb","password");
             Statement st=con.createStatement();
             System.out.println("connection established successfully...!!");     
 
             ResultSet rs=st.executeQuery("select * from demo_customers WHERE CUSTOMER_ID=2");
 
             pw.println("<table border=1>");
                 while(rs.next())
                 {
                     pw.println("<tr><td>"+rs.getString("CUST_LAST_NAME") +" "+rs.getString("CUST_FIRST_NAME")+"</td></tr>");
                 }
             pw.println("</table>");
             pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
 
		}
	
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
    }
}