package io.wisoft.seminar.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question13 {

  public void update(String empRcode, double empSal, String empName) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "UPDATE employee SET emp_rcode = ?, emp_sal = emp_sal * ? WHERE emp_name = ?";
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, empRcode);
      pstmt.setDouble(2, empSal);
      pstmt.setString(3, empName);

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
