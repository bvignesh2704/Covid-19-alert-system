import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HomePage extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        String cssTag="<link rel='stylesheet' type='text/css' href='style/style.css'>";
        out.println("<html>");
        out.println("<head><title>PMS DB</title>"+cssTag+"</head>");
        out.println("<body>");
        out.println("<div>");
		out.println("<h1>Patient Details</h1><br>");
        out.println("<br><a href=\"http://localhost:8080/hackathon/AddRecord.html\"> Add Record </a><br>");
        out.println("<br><a href=\"http://localhost:8080/hackathon/UpdateRecord.html\"> Update Record </a><br>");
        out.println("<br><a href=\"http://localhost:8080/hackathon/DeleteRecord.html\"> Delete Record </a><br>");
        out.println("<br> <a href=\"http://localhost:8080/hackathon/SearchRecord.html\"> Search Record </a><br>");
         out.println("<br> <a href=\"http://localhost:8080/hackathon/ViewRecord.html\"> View Record </a><br>");
        out.println("</div></body></html>");
	}
}
