package io.wisoft.seminar.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question14 {

  public void insert(String empCode, String empName, String empMgt, int empSal, String empRcode) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "INSERT INTO employee VALUES (?, ?, ?, ?, ?)";
      pstmt = conn.prepareStatement(query);

      pstmt.setString(1, empCode);
      pstmt.setString(2, empName);
      pstmt.setString(3, empMgt);
      pstmt.setInt(4, empSal);
      pstmt.setString(5, empRcode);

      pstmt.addBatch();
      pstmt.clearParameters();

      int[] retValue = pstmt.executeBatch();
      conn.commit();
      System.out.println(retValue.length + "건의 사항이 처리되었습니다.");
    } catch (SQLException sqex) {
      try {
        if (conn != null) {
          conn.rollback();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

      System.out.println("SQLException: " + sqex.getMessage());
      System.out.println("SQLState: " + sqex.getSQLState());
    } finally {
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
