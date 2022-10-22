import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main4 {
  //updating data from db
  public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      //connecting to database
      connection = DB.getConnection();
      //creating a statement (Inserting a value)
      //overloading prepareStatement method
      statement = connection.prepareStatement("UPDATE seller "
              + "SET BaseSalary = BaseSalary + ? "  + "WHERE " + "(DepartmentId = ?)");
      statement.setDouble(1,200.00);
      statement.setInt(2, 2);
      //executing
      //updating database
      int rowsAffected = statement.executeUpdate();
      System.out.println("DONE! Rows affected: " + rowsAffected);
    } catch(SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      //closing pool
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }
}