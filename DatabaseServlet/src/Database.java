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
	static String id, fn,ln,cust="";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");        
          
 
        try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             Connection con=DriverManager.getConnection("jdbc:oracle:thin:testuser/password@localhost","testdb","password");
             Statement st=con.createStatement();
             System.out.println("connection established successfully...!!");     
 
             ResultSet rs=st.executeQuery("select * from demo_customers");
 
             cust+= "<table border=1>";
             cust+= "<tr><th>First Name</th><th>Last Name</th><th>Customer ID</th></tr>";
                 while(rs.next())
                 {
                    id = rs.getString("CUSTOMER_ID");
                    fn = rs.getString("CUST_FIRST_NAME");
                    ln = rs.getString("CUST_LAST_NAME");
                    
                    
                    cust+= "<tr><td>" +fn +"</td><td>"+ln+"</td><td>"
                    		+ " <a href =" + "\"Details?customerid=" +id+"\"" + ">" + id +"</a>"+"</td></tr>";
                 }
                 con.close();
      
        }
        catch (Exception e){
            e.printStackTrace();
        }
 
        req.setAttribute("message",cust);
		getServletContext().getRequestDispatcher("/DatabaseOutput.jsp").forward(req, res);
		
		}
	
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
    }
}