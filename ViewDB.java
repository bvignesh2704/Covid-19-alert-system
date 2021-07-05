import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class ViewDB extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hack","root","2727");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM datatable");
			String cssTag="<link rel='stylesheet' type='text/css' href='style/style.css'>";
    out.println("<html>");
    out.println("<head><title>PMS DB</title>"+cssTag+"</head>");
    out.println("<body>");
    out.println("<div>");
	out.println("<h1> <Center> Complete Patient Database <center> </h1><br>");
	String id="",name="",email="",street="",area="",pincode="",city="";
	out.println("<table id=\"patient\">");
	out.println("<tr> <th>Unique ID</th> <th>Name</th> <th>email</th> <th>street</th> <th>area</th><th>pin</th><th>City</th></tr>");
	while(rs.next()) {
			id = rs.getString("uniqueId");
			name = rs.getString("name");
			email = rs.getString("email");
			street = rs.getString("street");
			area = rs.getString("area");
			pincode = rs.getString("pin");
			city = rs.getString("city");
			out.println("<tr> <td>"+id+"</td>" + "<td>"+name+"</td>" + "<td>"+email+"</td>" + "<td>"+street+"</td>" + "<td>"+area+"</td>"+"<td>"+pincode+"</td>"+"<td>"+city+"</td>"+"</tr>");
	}
	out.println("</table>");
			out.println("<form method=\"POST\" action=\"HomePage\"><br> <input type=\"submit\" value=\"Home\">");
		out.println("</div></body></html>");
		}

		catch (Exception e) {
e.printStackTrace();
		}
	}
}
