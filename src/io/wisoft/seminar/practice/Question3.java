package io.wisoft.seminar.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Question3 {

  public void getDrama(String drmPrd) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "SELECT drm_code, drm_name FROM drama WHERE drm_prd = ?";
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, drmPrd);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        System.out.print("[드라마 코드] " + rs.getString("drm_code") + " || ");
        System.out.println("[이름] " + rs.getString("drm_name"));
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
