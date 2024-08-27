import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class SaveInfo
 */
public class SaveInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final String query = "INSERT INTO vital (patient_name, temperature, heart_rate, spo2, blood_pressure, respiration) " +
                                        "VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());

        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");
        pw.println("Patient Record System.");
        pw.print("<br>\n");
        pw.println("New Entry");
        pw.print("<br>\n");
        pw.println("---------");
        pw.print("<br>\n");
        String name = request.getParameter("name");
        String temp = request.getParameter("temp");
        String h_rate = request.getParameter("h_rate");
        String spo2 = request.getParameter("spo2");
        String bp = request.getParameter("bp");
        String resp = request.getParameter("resp");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Loading JDBC Driver
        } catch (ClassNotFoundException e) {
            pw.println("Cannot connect to database");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "user", "user"); // Database connection string
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, temp);
            ps.setString(3, h_rate);
            ps.setString(4, spo2);
            ps.setString(5, bp);
            ps.setString(6, resp);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                pw.print("<br>\n");
                pw.println("Records not saved");
                System.out.println("Records not saved");
            } else {
                pw.print("<br>\n");
                pw.println("Records saved");
                System.out.println("Records saved");
                request.getRequestDispatcher("/displayData.jsp").forward(request, response);
            }
        } catch (SQLException se) {
            pw.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            pw.println(e.getMessage());
            e.printStackTrace();
        }
        
        pw.print("<br>\n");
        pw.println("Patient Name: " + name);
        pw.print("<br>\n");
        pw.println("Temperature: " + temp);
        pw.print("<br>\n");
        pw.println("Heart Rate: " + h_rate);
        pw.print("<br>\n");
        pw.println("SPo2: " + spo2);
        pw.print("<br>\n");
        pw.println("Blood Pressure: " + bp);
        pw.print("<br>\n");
        pw.println("Respiration: " + resp);


        pw.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
