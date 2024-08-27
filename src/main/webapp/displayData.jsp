<%@ page import="java.sql.*, javax.sql.*, jakarta.servlet.*, jakarta.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Patient Records</title>
</head>
<body>
    <h2>Patient Records</h2>

    <%
        String url = "jdbc:mysql://localhost:3306/hospital";
        String user = "user";
        String password = "user";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM vital");

            if (rs != null) {
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Patient Name</th><th>Temperature</th><th>Heart Rate</th><th>SPo2</th><th>Blood Pressure</th><th>Respiration</th></tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("patient_name") + "</td>");
                    out.println("<td>" + rs.getString("temperature") + "</td>");
                    out.println("<td>" + rs.getString("heart_rate") + "</td>");
                    out.println("<td>" + rs.getString("spo2") + "</td>");
                    out.println("<td>" + rs.getString("blood_pressure") + "</td>");
                    out.println("<td>" + rs.getString("respiration") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    %>

</body>
</html>
