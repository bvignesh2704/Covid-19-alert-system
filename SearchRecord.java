import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

class Mailer{
    public static void send(String from,String password,String to,String sub,String msg){
          //Get properties object
          Properties props = new Properties();
          props.put("mail.smtp.host", "smtp.gmail.com");
          props.put("mail.smtp.socketFactory.port", "465");
          props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "465");
          //get Session
          Session session = Session.getDefaultInstance(props,
           new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(from,password);
           }
          });
          //compose message
          try {
           MimeMessage message = new MimeMessage(session);
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           message.setSubject(sub);
           message.setText(msg);
           //send message
           Transport.send(message);
           System.out.println("message sent successfully");
          } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}

public class SearchRecord extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String cssTag="<link rel='stylesheet' type='text/css' href='style/style2.css'>";
			String cssTag1="<link rel='stylesheet' type='text/css' href='style/style.css'>";
    out.println("<html>");
    out.println("<head><title>PMS Search</title>"+cssTag+cssTag1+"</head>");
    out.println("<body>");
    out.println("<div class=\"center\">");
			out.println("<h1> <Center> Search Status <center> </h1><br>");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hack","root","2727");
			Statement stmt = conn.createStatement();
      String sid = request.getParameter("uid");
			ResultSet rs = stmt.executeQuery("SELECT * FROM datatable where uniqueId=\""+sid+"\";");
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
			ArrayList<String> emailist=new ArrayList<String>();
			rs = stmt.executeQuery("SELECT * FROM datatable where street=\""+street+"\"AND area=\""+area+"\"AND pin=\""+pincode+"\"AND city=\""+city+"\";");
			out.println("<br><br><table id=\"patient\">");
			out.println("<tr> <th>Unique ID</th> <th>Name</th> <th>email</th> <th>street</th> <th>area</th><th>pin</th><th>City</th></tr>");
			while(rs.next()) {
					id = rs.getString("uniqueId");
					name = rs.getString("name");
					email = rs.getString("email");
					street = rs.getString("street");
					area = rs.getString("area");
					pincode = rs.getString("pin");
					city = rs.getString("city");
					emailist.add(email);
					out.println("<tr> <td>"+id+"</td>" + "<td>"+name+"</td>" + "<td>"+email+"</td>" + "<td>"+street+"</td>" + "<td>"+area+"</td>"+"<td>"+pincode+"</td>"+"<td>"+city+"</td>"+"</tr>");
			}
			out.println("</table>");
			out.println("<form method=\"POST\" action=\"HomePage\"><br> <input type=\"submit\" value=\"Home\">");
			//for(int i=0;i<emailist.size();i++){
				Mailer.send("t3stnew@gmail.com","trial_123","bvignesh2700@gmail.com","Covid Alert","A covid positive case has been reported in your street. Disclaimer:For project testing Dont worry!");

			//}
			out.println("</div></body></html>");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
