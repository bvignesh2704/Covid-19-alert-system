import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class AddRecord extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String cssTag="<link rel='stylesheet' type='text/css' href='style/style2.css'>";
    out.println("<html>");
    out.println("<head><title>PMS Create</title>"+cssTag+"</head>");
    out.println("<body>");
    out.println("<div class=\"center\">");
			out.println("<h1> <Center> Create Status <center> </h1><br>");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hack","root","2727");
			Statement stmt = conn.createStatement();
      String name = request.getParameter("name");
			String id = request.getParameter("id");
			String email = request.getParameter("email");
      String street = request.getParameter("street");
			String area = request.getParameter("area");
			String pin = request.getParameter("pin");
			String city = request.getParameter("city");

			String sql = "INSERT INTO datatable VALUES (\""+ id +"\",\""+ name +"\",\"" + email + "\",\""+street+"\",\""+area+"\",\""+pin+"\",\""+ city+"\");";
			stmt.executeUpdate(sql);
			out.println("<form method=\"POST\" action=\"ViewDB\"><h1><center>Record Created</center></h1><br><br> <h1><center><input type=\"submit\" value=\"View\"></center></h1>");
			out.println("</div></body></html>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
