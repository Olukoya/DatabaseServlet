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
 
@WebServlet("/Details")
public class Details extends HttpServlet  
{
	static String id, fn,ln,sa1,sa2,cc,cs,cpc,pn1,pn2,cl,ce,cust="";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");        
          
        String singleID = req.getParameter("customerid");
        try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             Connection con=DriverManager.getConnection("jdbc:oracle:thin:testuser/password@localhost","testdb","password");
             Statement st=con.createStatement();
             System.out.println("connection established successfully...!!");     
 
             ResultSet rs=st.executeQuery("select * from demo_customers where CUSTOMER_ID = "+singleID);
 
             cust+= "<table border=1>";
             cust+= "<tr><th>Customer ID</th>"
             		+ "<th>First Name</th>"
             		+ "<th>Last Name</th>"
             		+ "<th>Street Add1</th>"
             		+ "<th>Street Add2</th>"
             		+ "<th>City</th>"
             		+ "<th>State</th>"
             		+ "<th>Postal Code</th>"
             		+ "<th>Phone 1</th>"
             		+ "<th>Phone 2</th>"
             		+ "<th>Credit Limit</th>"
             		+ "<th>Email</th>"
             		+ "</tr>";
                 while(rs.next())
                 {
                    id = rs.getString("CUSTOMER_ID");
                    fn = rs.getString("CUST_FIRST_NAME");
                    ln = rs.getString("CUST_LAST_NAME");
                    sa1 = rs.getString("CUST_STREET_ADDRESS1");
                    sa2 = rs.getString("CUST_STREET_ADDRESS2");
                    cc = rs.getString("CUST_CITY");
                    cs = rs.getString("CUST_STATE");
                    cpc = rs.getString("CUST_POSTAL_CODE");
                    pn1 = rs.getString("PHONE_NUMBER1");
                    pn2 = rs.getString("PHONE_NUMBER2");
                    cl = rs.getString("CREDIT_LIMIT");
                    ce = rs.getString("CUST_EMAIL");
                    
                    
                    cust+= "<tr><td>"+id
                    		+"</td><td>"+fn
                    		+"</td><td>"+ln
                    		+"</td><td>"+sa1
                    		+"</td><td>"+sa2
                    		+"</td><td>"+cc
                    		+"</td><td>"+cs
                    		+"</td><td>"+cpc
                    		+"</td><td>"+pn1
                    		+"</td><td>"+pn2
                    		+"</td><td>"+cl
                    		+"</td><td>"+ce
                    		+"</td></tr>";
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