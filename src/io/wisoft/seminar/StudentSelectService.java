package io.wisoft.seminar;

import java.sql.*;

// 원래는 인터페이스
public class StudentSelectService {

  public void getStudentAll() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = PostgresqlAccess.setConnection();

      // SELECT이면 할 필요 없음
      conn.setAutoCommit(false);

      String query = "SELECT * FROM STUDENT";
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        // 인덱스보단 컬럼 이름을 쓰는걸 추천
        System.out.print("[학번] " + rs.getString("no") + " || ");
        System.out.print("[이름] " + rs.getString("name") + " || ");
        System.out.println("[생일] " + rs.getString("birthday"));
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

  // 학번은 계산하는데 사용안함. 그래서 문자가 맞음
  public void getStudentByNo(String studentNo) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "SELECT * FROM STUDENT WHERE NO = ?";
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, Integer.parseInt(studentNo));
      rs = pstmt.executeQuery();

      while (rs.next()) {
        System.out.print("[학번] " + rs.getString("no") + " || ");
        System.out.print("[이름] " + rs.getString("name") + " || ");
        System.out.println("[생일] " + rs.getString("birthday"));
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

  public void getStudentByName(String studentName) {

  }

  public void getStudentByBirthday(String studentBirthday) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = PostgresqlAccess.setConnection();
      conn.setAutoCommit(false);

      String query = "SELECT * FROM STUDENT WHERE BIRTHDAY = ?";
      pstmt = conn.prepareStatement(query);
      pstmt.setDate(1, Date.valueOf(studentBirthday));
      rs = pstmt.executeQuery();

      while (rs.next()) {
        System.out.print("[학번] " + rs.getString("no") + " || ");
        System.out.print("[이름] " + rs.getString("name") + " || ");
        System.out.println("[생일] " + rs.getString("birthday"));
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
