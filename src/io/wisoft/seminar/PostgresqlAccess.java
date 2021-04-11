package io.wisoft.seminar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlAccess {

  private static Connection conn = null;

  public void init() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Connection setConnection() {
    String url = "jdbc:postgresql://localhost:5432/exercise";
    // 아아디, 비밀번호는 실제론 별도의 파일에서 받아오는게 좋다.
    String username = "scott";
    String password = "tiger";

    try {
      conn = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;
  }

}
