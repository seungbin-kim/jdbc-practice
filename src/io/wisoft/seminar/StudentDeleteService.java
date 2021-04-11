package io.wisoft.seminar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDeleteService {

  public void deleteStudentNo(String studentNo) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "DELETE FROM STUDENT WHERE NO = ?";
      pstmt = conn.prepareStatement(query);

      pstmt.setInt(1, Integer.parseInt(studentNo));

      int retValue = pstmt.executeUpdate();
      conn.commit();

      System.out.println(retValue + "건의 사항이 처리되었습니다.");
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

  public void deleteStudentNoMultiBatch(Student[] students) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "DELETE FROM STUDENT WHERE NO = ?";
      pstmt = conn.prepareStatement(query);

      for (int i = 0; i < students.length; i++) {
        if (students[i].getNo() == null) {
          break;
        }

        pstmt.setInt(1, Integer.parseInt(students[i].getNo()));
        pstmt.addBatch();
        pstmt.clearParameters();
      }

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
