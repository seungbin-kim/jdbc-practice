package io.wisoft.seminar.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question10 {

  public void getEmployee(int empAvgSal) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "SELECT er.emp_rname, ROUND(AVG(e.emp_sal), 0), min(e.emp_sal), max(e.emp_sal)\n" +
          "FROM employee e, emp_role er\n" +
          "WHERE er.emp_rcode = e.emp_rcode\n" +
          "GROUP BY er.emp_rname\n" +
          "HAVING AVG(e.emp_sal) > ?";

      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, empAvgSal);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        System.out.print("[직급] " + rs.getString("emp_rname") + " || ");
        System.out.print("[평균 급여액] " + rs.getString("round") + " || ");
        System.out.print("[최소 급여액] " + rs.getString("min") + " || ");
        System.out.println("[최대 급여액] " + rs.getString("max"));
      }
    } catch (SQLException sqex) {
      System.out.println("SQLException: " + sqex.getMessage());
      System.out.println("SQLState: " + sqex.getSQLState());
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      if (pstmt != null) {
        try {
          pstmt.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      if (conn != null) {
        try {
          conn.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

}
